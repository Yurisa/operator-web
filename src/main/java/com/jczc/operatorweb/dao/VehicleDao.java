package com.jczc.operatorweb.dao;

import com.jczc.operatorweb.model.VehicleInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by lwj on 2017/11/3.
 */

@Component
public interface VehicleDao {

    /**
     * 添加用户所属车辆
     *
     * @param vehicleReData
     * @param id
     */
    void save(VehicleInfo vehicle);


    /**
     * 验证该车辆的车牌是否已存在
     *
     * @param plateNo
     * @param id
     */
    String validateVehicle(VehicleInfo vehicle);

    /**
     * 删除指定汽车信息
     *
     * @param id
     */
    void delete(int id);

    /**
     * 获取指定用户汽车信息
     *
     * @param id
     * @return
     */
    List<VehicleInfo> findByUserId(int id);
    VehicleInfo getByVehicleId(int id);
    VehicleInfo getByPlateNo(String platNo);
    List<VehicleInfo> findByPlateNo(String platNo);
    
    /**
     * 更新用户汽车信息
     *
     * @param vehicleReData
     * @param id
     */
    void update(VehicleInfo vehicle);
}
