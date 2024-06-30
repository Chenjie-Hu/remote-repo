package com.thistest.car.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thistest.car.Entity.VehicleInfo;
import com.thistest.car.Entity.WarnRule;
import com.thistest.car.mapper.WarnRuleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.Date;

@Service
public class WarnRuleService extends ServiceImpl<WarnRuleMapper, WarnRule>{
    @Autowired
    private WarnRuleMapper warnRuleMapper;

    public List<WarnRule> getAllRules() {
        return warnRuleMapper.selectList(null);
    }

    public WarnRule getRuleById(Integer id) {
        return warnRuleMapper.selectById(id);
    }

    @Transactional
    public void insertWarnRule(WarnRule warnRule) {

        warnRule.setCreateTime(new Date());  // 自动填充 createTime
        warnRule.setCreateUser("admin");     // 自动填充 createUser

        warnRuleMapper.insert(warnRule);
    }
    @Transactional
    public void updateWarnRule(WarnRule warnRule) {
        warnRule.setUpdateTime(new Date());  // 自动填充 updateTime
        warnRule.setUpdateUser("admin");     // 自动填充 updateUser
        warnRuleMapper.updateById(warnRule);
    }
    @Transactional
    public WarnRule getWarnRule(Long id) {
        WarnRule warnRule = warnRuleMapper.selectById(id);
        return warnRule;
    }
    @Transactional
    public boolean deleteWarnRule(Long id) {
        return removeById(id);
    }

    public WarnRule getApplicableRule(double value, String batteryType, String type) {
        QueryWrapper<WarnRule> queryWrapper = new QueryWrapper<>();
        if (type.equals("mx_mi")) {
            queryWrapper.le("mx_mi_min", value).gt("mx_mi_max", value).eq("battery_type", batteryType);
        } else {
            queryWrapper.le("ix_ii_min", value).gt("ix_ii_max", value).eq("battery_type", batteryType);
        }
        return warnRuleMapper.selectOne(queryWrapper);
    }

}