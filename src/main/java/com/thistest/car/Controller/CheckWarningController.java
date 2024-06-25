package com.thistest.car.Controller;

import com.google.gson.Gson;
import com.thistest.car.Entity.VehicleInfo;
import com.thistest.car.Entity.WarnRule;
import com.thistest.car.Service.VehicleInfoService;
import com.thistest.car.Service.WarnRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CheckWarningController {

    @Autowired
    private VehicleInfoService vehicleInfoService;

    @Autowired
    private WarnRuleService warnRuleService;

    @PostMapping("/warn")
    public ResponseEntity<Map<String, Object>> checkWarning(@RequestBody List<Map<String, Object>> requestBodies) {
        List<Map<String, Object>> data = new ArrayList<>();
        for (Map<String, Object> requestBody : requestBodies) {
            int carId = (int) requestBody.get("carId");
            Integer warnId = (Integer) requestBody.get("warnId"); // warnId 可能为null
            String signalJson = (String) requestBody.get("signal");

            // 解析 signal 字段中的 JSON 字符串
            Map<String, Object> signal = parseSignalJson(signalJson);

            VehicleInfo vehicleInfo = vehicleInfoService.getByVin(carId);
            if (vehicleInfo == null) {
                Map<String, Object> response = new HashMap<>();
                response.put("车架编号", carId);
                response.put("status", 404);
                response.put("msg", "Car not found");
                data.add(response);
                continue;
            }

            // 计算 mxMi 和 ixIi
            double mxMi = calculateDifference(signal, "Mx", "Mi");
            double ixIi = calculateDifference(signal, "Ix", "Ii");

            // 计算预警等级
            int mxMiLevel = calculateMxMiLevel(mxMi, vehicleInfo.getBatteryType());
            int ixIiLevel = calculateIxIiLevel(ixIi, vehicleInfo.getBatteryType());

            // 构造预警信息
            if (signal.containsKey("Mx") && signal.containsKey("Mi")){
                Map<String, Object> response = new LinkedHashMap<>();
                response.put("车架编号", carId);
                response.put("电池类型", vehicleInfo.getBatteryType());
                if(mxMiLevel==5) {
                    response.put("warnName", "不报警");
                }
                else{
                    response.put("warnName", "电压差报警");
                    response.put("warnLevel", mxMiLevel);
                }
                data.add(response);
            }
            if (signal.containsKey("Ix") && signal.containsKey("Ii")){
                Map<String, Object> response = new LinkedHashMap<>();
                response.put("车架编号", carId);
                response.put("电池类型", vehicleInfo.getBatteryType());
                if(ixIiLevel==5) {
                    response.put("warnName", "不报警");
                }
                else{
                    response.put("warnName", "电流差报警");
                    response.put("warnLevel", ixIiLevel);
                }
                data.add(response);
            }
        }

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("status", 200);
        result.put("msg", "ok");
        result.put("data", data);
        return ResponseEntity.ok(result);
    }

    // 解析 signal 字段中的 JSON 字符串
    private Map<String, Object> parseSignalJson(String signalJson) {
        Map<String, Object> signal = new HashMap<>();
        if (signalJson != null && !signalJson.isEmpty()) {
            signal = new Gson().fromJson(signalJson, Map.class);
        }
        return signal;
    }

    // 计算差值
    private double calculateDifference(Map<String, Object> signal, String key1, String key2) {
        if (signal.containsKey(key1) && signal.containsKey(key2)) {
            double value1 = (double) signal.get(key1);
            double value2 = (double) signal.get(key2);
            return value1 - value2;
        }
        return 0.0;
    }

    // 计算预警等级，电压预警规则
    private int calculateMxMiLevel(double mxMi, String batteryType) {
        WarnRule warnRule = warnRuleService.getApplicableRule(mxMi, batteryType, "mx_mi");
        return warnRule != null ? warnRule.getMxMiLevel() : 5; // 如果没有匹配规则，默认返回不报警 (warnLevel 5)
    }

    // 计算预警等级，电流预警规则
    private int calculateIxIiLevel(double ixIi, String batteryType) {
        WarnRule warnRule = warnRuleService.getApplicableRule(ixIi, batteryType, "ix_ii");
        return warnRule != null ? warnRule.getIxIiLevel() : 5; // 如果没有匹配规则，默认返回不报警 (warnLevel 5)
    }
}
