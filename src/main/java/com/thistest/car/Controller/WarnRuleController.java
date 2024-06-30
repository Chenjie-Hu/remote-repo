package com.thistest.car.Controller;

import com.thistest.car.Entity.VehicleInfo;
import com.thistest.car.Entity.WarnRule;
import com.thistest.car.Service.WarnRuleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/WarnRule")
public class WarnRuleController {

    @Autowired
    private WarnRuleService warnRuleService;

    @PostMapping("/insert")
    public String insertWarnRule(@RequestBody WarnRule warnRule) {
        warnRuleService.insertWarnRule(warnRule);
        return "插入数据成功";
    }

    @PutMapping("/update")
    public String updateWarnRule(@RequestBody WarnRule warnRule) {
        warnRuleService.updateWarnRule(warnRule);
        return "更新数据成功";

    }
    @GetMapping("{id}")
    public WarnRule  getWarnRule(@PathVariable Long id) {
        return  warnRuleService.getWarnRule(id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        boolean deleted = warnRuleService.deleteWarnRule(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

