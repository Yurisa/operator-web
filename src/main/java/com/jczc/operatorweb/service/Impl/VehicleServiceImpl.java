package com.jczc.operatorweb.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jczc.operatorweb.dao.VehicleDao;
import com.jczc.operatorweb.exception.DataException;
import com.jczc.operatorweb.exception.DataExceptionEnum;
import com.jczc.operatorweb.model.VehicleInfo;
import com.jczc.operatorweb.service.VehicleService;

import java.util.List;

/**
 * Created by lwj on 2017/11/3.
 */

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleDao vehicleDao;


    @Override
    public void addVehicleInfo(VehicleInfo vehicleReData) throws DataException {
        String plateNo = vehicleDao.validateVehicle(vehicleReData);
        if (plateNo == null || plateNo.equals("")) {
            vehicleDao.save(vehicleReData);
        } else {
            throw new DataException(DataExceptionEnum.PLATE_NO_IS_EXIST);
        }
    }

    @Override
    public void deleteVehicle(int id) throws DataException {
        if (id < 0) {
            throw new DataException(DataExceptionEnum.PARAMS_ERROR);
        } else {
            vehicleDao.delete(id);
        }
    }

    @Override
    public List<VehicleInfo> findByUserId(int id) {
        return vehicleDao.findByUserId(id);
    }

    @Override
    public void updata(VehicleInfo vehicleReData) {
        vehicleDao.update(vehicleReData);
    }

	@Override
	public VehicleInfo getByVehicleId(int id) {
		return vehicleDao.getByVehicleId(id);
	}

	@Override
	public VehicleInfo getByPlateNo(String platNo) {
		return vehicleDao.getByPlateNo(platNo);
	}

	@Override
	public List<VehicleInfo> findByPlateNo(String platNo) {
		return vehicleDao.findByPlateNo(platNo);
	}

}
