package com.thistest.car.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.thistest.car.Entity.VehicleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface VehicleInfoMapper extends BaseMapper<VehicleInfo> {
    @Select("select * from vehicle_info where vin = #{vin}")
    VehicleInfo getByVin(Integer vin);
}