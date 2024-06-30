package com.thistest.car.Controller;

import com.thistest.car.mapper.VehicleInfoMapper;
import com.thistest.car.Entity.VehicleInfo;
import com.thistest.car.Service.VehicleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.List;
import org.springframework.http.ResponseEntity;


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
    @GetMapping("{id}")
    public VehicleInfo  getVehicleInfo(@PathVariable int id) {
       return  vehicleInfoService.getVehicleInfo(id);
    }
    @GetMapping("/vin{vin}")
    public VehicleInfo  getVehicleInfoByVin(@PathVariable int vin) {
        return  vehicleInfoService.getByVin(vin);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        boolean deleted = vehicleInfoService.deleteVehicle(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

