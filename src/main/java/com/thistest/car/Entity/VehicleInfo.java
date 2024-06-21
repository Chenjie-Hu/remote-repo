package com.thistest.car.Entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("vehicle_info")
public class VehicleInfo {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String vid;
    private Integer vin;
    private String batteryType;
    private Double totalMileage;
    private Integer batteryHealth;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;  // 创建时间

    @TableField(fill = FieldFill.INSERT)
    private String createUser;  // 创建者

    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;  // 更新时间

    @TableField(fill = FieldFill.UPDATE)
    private String updateUser;  // 修改者
    // Getters and Setters
    // toString, constructors, etc.
}

