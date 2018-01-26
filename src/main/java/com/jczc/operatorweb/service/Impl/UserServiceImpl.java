package com.jczc.operatorweb.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jczc.operatorweb.dao.ChargingUserDao;
import com.jczc.operatorweb.exception.DataException;
import com.jczc.operatorweb.exception.DataExceptionEnum;
import com.jczc.operatorweb.model.AccountDetail;
import com.jczc.operatorweb.model.ChargedFeeList;
import com.jczc.operatorweb.model.ChargingUser;
import com.jczc.operatorweb.model.PileInfo;
import com.jczc.operatorweb.model.StationAndPosition;
import com.jczc.operatorweb.model.UserAccount;
import com.jczc.operatorweb.service.PileService;
import com.jczc.operatorweb.service.PileStationService;
import com.jczc.operatorweb.service.UserService;
import com.jczc.operatorweb.util.EncryptUtil;
import com.jczc.operatorweb.util.StringUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by hpx on 2017/10/23.
 */

@Service
public class UserServiceImpl implements UserService {
	private static Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private ChargingUserDao chargingUserDao;

    @Autowired
    private PileService pileService;

    @Autowired
    private PileStationService pileStationService;

    @Override
    public ChargingUser login(String mobile, String password) throws DataException {
    	if(StringUtil.isEmpty(mobile)||StringUtil.isEmpty(password))
    		throw new DataException(DataExceptionEnum.USER_IS_NOT_EXIST);
        ChargingUser chargingUser = chargingUserDao.getUserByMobile(mobile);
        if (chargingUser == null) {
            throw new DataException(DataExceptionEnum.USER_IS_NOT_EXIST);
        } else if (!EncryptUtil.encryptPassword(password).equals(chargingUser.getPassword())) {
            throw new DataException(DataExceptionEnum.PASSWORD_IS_ERROR);
        } else {
        	String uuid=EncryptUtil.createAccessToken(chargingUser);
    		chargingUser.setAccessToken(uuid);
    		chargingUserDao.freshAccessToken(chargingUser);
            return chargingUser;
        }

    }

	@Override
	public ChargingUser loginWithToken(String mobile, String password, String accessToken) throws DataException{
		ChargingUser chargingUser = chargingUserDao.getUserByMobile(mobile);
        if (chargingUser == null) {
            throw new DataException(DataExceptionEnum.USER_IS_NOT_EXIST);
        } else if (!EncryptUtil.encryptPassword(password).equals(chargingUser.getPassword())) {
            throw new DataException(DataExceptionEnum.PASSWORD_IS_ERROR);
        } else {
        	String token=chargingUser.getAccessToken();
        	if(token==null||token.equals(accessToken)){//初次登录或token验证通过，更新token返回客户端
        		String uuid=EncryptUtil.createAccessToken(chargingUser);
        		chargingUser.setAccessToken(uuid);
        		chargingUserDao.freshAccessToken(chargingUser);
        		return chargingUser;
        	}
        	else
        		throw new DataException(DataExceptionEnum.ACCESS_TOKEN_EEOR);
        }
	}
    @Override
    public ChargingUser register(String mobile, String password) throws DataException {
        ChargingUser chargingUser = chargingUserDao.getUserByMobile(mobile);
        if (chargingUser != null) {
            throw new DataException(DataExceptionEnum.USER_IS_EXIST);
        }
        String token=EncryptUtil.createAccessToken(chargingUser);
//        chargingUserDao.userRegister(mobile, PasswordUtil.encript(password));
        chargingUserDao.userRegisterWithToken(mobile, password,token);
        chargingUser = chargingUserDao.getUserByMobile(mobile);
        chargingUserDao.createUserAccount(chargingUser.getId());

        return chargingUser;
    }

    @Override
    public void editUserInfo(ChargingUser chargingUser) {
        chargingUserDao.editUserInfo(chargingUser);
    }

    @Override
    public boolean updatePassword(int id, String password,String oldPassword) {
    	
    	ChargingUser user=chargingUserDao.getUserById(id);
    	
    	if(user==null) {
    		logger.error("用户不存在");
    		return false;
    	}
    	String encPwd=EncryptUtil.encryptPassword(password);
    	String oldEncPwd=EncryptUtil.encryptPassword(oldPassword);
    	
    	if(!user.getPassword().equals(oldEncPwd)) {
    		logger.debug("db pwd:"+user.getPassword()+",old pwd:"+oldEncPwd);
    		logger.error("原密码不对");
    		return false;
    	}
        chargingUserDao.updatePassword(id, encPwd);
        logger.info("user "+id+"修改了密码");
        return true;
    }

    @Override
    public UserAccount getUserAccountByUserId(int id) {
        return chargingUserDao.getUserAccountByUserId(id);
    }

    @Override
    public void recharge(double balance, int userId) throws DataException {
        if (balance < 0) {
            throw new DataException(DataExceptionEnum.PARAMS_ERROR);
        }
        chargingUserDao.recharge(chargingUserDao.getBalanceById(userId) + balance, userId);
    }

    @Override
    public List<AccountDetail> getAccountDetail(int userId, Date startTime, Date endTime) {
        return chargingUserDao.getAccountDetail(userId, startTime, endTime);
    }
    @Override
    public AccountDetail getAccountDetailById(Long accountDetailId) {
        return chargingUserDao.getAccountDetailById(accountDetailId);
    }
    @Override
    public List<ChargedFeeList> getChargeFeeDetail(Long accountDetailId) {
        return chargingUserDao.getChargeFeeDetail(accountDetailId);
    }

    @Override
    public void payBill(int userId, Long accountDetailId,String payFrom,String payOrderId,String payUserId) throws DataException {
    	UserAccount account=chargingUserDao.getUserAccountByUserId(userId);
    	
    	double balance = account.getBalance();
        /*获取余额*/
    	AccountDetail accountDetail=chargingUserDao.getAccountDetailById(accountDetailId);
    	if(accountDetail.getPayStatus()==1)
    		throw new DataException(DataExceptionEnum.ALREADY_PAYED);
//        double reduceMoney = chargingUserDao.getAmountById(accountDetailId);
        if(!StringUtil.isEmpty(payOrderId))
        	chargingUserDao.payAccountDetail(accountDetailId,payFrom,payOrderId,payUserId,1);
        else if (balance < accountDetail.getAmount()) {
            throw new DataException(DataExceptionEnum.BALANCE);
        } else {
            /*支付费用*/
            chargingUserDao.recharge(balance - accountDetail.getAmount(), userId);
        /*修改账单状态*/
            chargingUserDao.payAccountDetail(accountDetailId,payFrom,null,null,1);
        }
    }


    @Override
    public void editAccountInfo(UserAccount userAccount) {
        chargingUserDao.editAccountInfo(userAccount);
    }

    @Override
    public ChargingUser getUserInfo(int id) {
        return chargingUserDao.getUserInfo(id);
    }

    @Override
    public ChargingUser searchUserInfo(String wxpay) {
        return chargingUserDao.searchUserInfo(wxpay);
    }

    @Override
    public List<PileInfo> getPilesForDistance(double lat, double lng, double distance) {
        return pileService.getPilesForDistance(lat, lng, distance);
    }

    @Override
    public List<StationAndPosition> getPileStationsForDistance(double lat, double lng, double distance) {
        return pileStationService.getPileStationsForDistance(lat, lng, distance);
    }

	@Override
	public ChargingUser getUserById(int userId) throws DataException {
		// TODO Auto-generated method stub
		return chargingUserDao.getUserById(userId);
	}
	@Override
	public ChargingUser getUserByAccessToken(String token) throws DataException {
		// TODO Auto-generated method stub
		if(token==null||token.trim().length()==0)
			throw new DataException(DataExceptionEnum.ACCESS_TOKEN_EEOR);
		return chargingUserDao.getUserByAccessToken(token);
	}
}
