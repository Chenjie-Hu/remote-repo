package com.thistest.car.Controller;

import com.thistest.car.mapper.VehicleInfoMapper;
import com.thistest.car.Entity.VehicleInfo;
import com.thistest.car.Service.VehicleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicle")
public class VehicleInfoController {

    @Autowired

    private VehicleInfoService vehicleInfoService;

    @PostMapping("/insert")
    public String insertVehicleInfo(@RequestBody VehicleInfo vehicleInfo) {
        vehicleInfoService.insertVehicleInfo(vehicleInfo);
        return "插入数据成功";
    }

    @PutMapping("/update")
    public String updateVehicleInfo(@RequestBody VehicleInfo vehicleInfo) {
        vehicleInfoService.updateVehicleInfo(vehicleInfo);
        return "更新数据成功";
    }
}

