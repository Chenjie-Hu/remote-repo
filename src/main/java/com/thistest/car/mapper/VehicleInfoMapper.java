package com.thistest.car.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.thistest.car.Entity.VehicleInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VehicleInfoMapper extends BaseMapper<VehicleInfo> {
}