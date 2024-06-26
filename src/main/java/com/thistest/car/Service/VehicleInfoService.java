package com.thistest.car.Service;
import com.thistest.car.mapper.VehicleInfoMapper;
import com.thistest.car.Entity.VehicleInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.Collection;

@Service
public class VehicleInfoService extends ServiceImpl<VehicleInfoMapper, VehicleInfo> {

    @Autowired
    private VehicleInfoMapper vehicleInfoMapper;

    @Transactional
    public VehicleInfo getByVin(Integer vin) {
        return vehicleInfoMapper.getByVin(vin);
    }

    @Transactional
    public void insertVehicleInfo(VehicleInfo vehicleInfo) {
        String randomPart = generateRandomString(8);
        vehicleInfo.setVid(Long.toString(System.currentTimeMillis()).substring(Long.toString(System.currentTimeMillis()).length()-8) + randomPart);  // 使用时间戳和随机字符串拼接作为vid
        vehicleInfo.setCreateTime(new Date());  // 自动填充 createTime
        vehicleInfo.setCreateUser("admin");     // 自动填充 createUser

        vehicleInfoMapper.insert(vehicleInfo);
    }
    private String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    @Transactional
    public void updateVehicleInfo(VehicleInfo vehicleInfo) {
        vehicleInfo.setUpdateTime(new Date());  // 自动填充 updateTime
        vehicleInfo.setUpdateUser("admin");     // 自动填充 updateUser
        vehicleInfoMapper.updateById(vehicleInfo);
    }
    @Transactional
    public VehicleInfo getVehicleInfo(Integer id) {
        VehicleInfo vehicleInfo = vehicleInfoMapper.selectById(id);
       return vehicleInfo;
    }
    @Transactional
    public boolean deleteVehicle(Long id) {
        return removeById(id);
    }

}

