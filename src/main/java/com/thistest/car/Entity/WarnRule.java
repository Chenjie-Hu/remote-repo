package com.thistest.car.Entity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("warn_rule")
public class WarnRule {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Integer ruleId;
    private String name;
    private String batteryType;
    private Double mxMiMin;
    private Double mxMiMax;
    private Integer mxMiLevel;
    private Double ixIiMin;
    private Double ixIiMax;
    private Integer ixIiLevel;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;  // 创建时间

    @TableField(fill = FieldFill.INSERT)
    private String createUser;  // 创建者

    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;  // 更新时间

    @TableField(fill = FieldFill.UPDATE)
    private String updateUser;  // 修改者
}
