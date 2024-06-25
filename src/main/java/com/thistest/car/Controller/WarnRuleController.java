package com.thistest.car.Controller;

import com.thistest.car.Entity.WarnRule;
import com.thistest.car.Service.WarnRuleService;

import org.springframework.beans.factory.annotation.Autowired;
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

}

