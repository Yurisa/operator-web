package com.jczc.operatorweb.service;


import java.util.List;

import com.jczc.operatorweb.exception.DataException;
import com.jczc.operatorweb.model.VehicleInfo;

/**
 * Created by lwj on 2017/11/3.
 */
public interface VehicleService {

    /**
     * 添加个人汽车信息
     *
     * @param vehicleReData
     * @param id
     */
    void addVehicleInfo(VehicleInfo vehicleReData) throws DataException;

    /**
     * 删除指定汽车信息
     *
     * @param id
     */
    void deleteVehicle(int id) throws DataException;

    /**
     * 获取指定用户的汽车信息
     *
     * @param id
     * @return
     */
    List<VehicleInfo> findByUserId(int id);
    VehicleInfo getByVehicleId(int id);
    VehicleInfo getByPlateNo(String platNo);
    List<VehicleInfo> findByPlateNo(String platNo);

    /**
     * 更新汽车信息
     *
     * @param vehicleReData
     */
    void updata(VehicleInfo vehicleReData);
}
