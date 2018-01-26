package com.jczc.operatorweb.service;


import java.util.Date;
import java.util.List;

import com.jczc.operatorweb.exception.DataException;
import com.jczc.operatorweb.model.AccountDetail;
import com.jczc.operatorweb.model.ChargedFeeList;
import com.jczc.operatorweb.model.ChargingUser;
import com.jczc.operatorweb.model.PileInfo;
import com.jczc.operatorweb.model.StationAndPosition;
import com.jczc.operatorweb.model.UserAccount;

/**
 * Created by hpx on 2017/10/23.
 */
public interface UserService {
	/**
	 * 获取用户信息
	 * @param userId 用户ID
	 * @return 对应的用户
	 */
	ChargingUser getUserById(int userId) throws DataException;
	ChargingUser getUserByAccessToken(String token) throws DataException;
	ChargingUser loginWithToken(String mobile, String password, String accessToken)throws DataException;
	
    /**
     * 登录验证
     *
     * @param mobile
     * @param password
     * @return
     * @throws DataException
     */
    ChargingUser login(String mobile, String password) throws DataException;

    /**
     * 注册
     *
     * @param mobile
     * @param password
     * @throws DataException
     */
    ChargingUser register(String mobile, String password) throws DataException;

    /**
     * 编辑/完善用户个人信息
     *
     * @param chargingUser
     */
    void editUserInfo(ChargingUser chargingUser);

    /**
     * 修改用户密码
     *
     * @param password
     * @param id
     */
    boolean updatePassword(int id, String password,String oldPassword);

    /**
     * 获取用户的账户信息
     *
     * @param id
     * @return
     */
    UserAccount getUserAccountByUserId(int id);

    /**
     * 充值
     *
     * @param balance
     * @param id
     */
    void recharge(double balance, int userId) throws DataException;

    /**
     * 获取指定用户的收支明细
     *
     * @param id
     * @param startTime
     * @param endTime   
     * @return
     */
    List<AccountDetail> getAccountDetail(int id, Date startTime, Date endTime);
    AccountDetail getAccountDetailById(Long accountDetailId);
    /**
     * 获取指定充电费用详情
     *
     * @param id
     * @return
     */
    List<ChargedFeeList> getChargeFeeDetail(Long accountDetailId);

    /**
     * 支付账单
     *
     * @param id
     * @param accountId
     */
    void payBill(int id, Long accountId,String payFrom,String payOrderId,String payUserId) throws DataException;

    /**
     * 完善用户账户信息
     *
     * @param userAccount
     */
    void editAccountInfo(UserAccount userAccount);

    /**
     * 根据id获取用户个人信息
     *
     * @param id
     * @return
     */
    ChargingUser getUserInfo(int id);

    /**
     * 根据微信号获取用户信息
     *
     * @param wxpay
     * @return
     */
    ChargingUser searchUserInfo(String wxpay);

    List<PileInfo> getPilesForDistance(double lat1, double lng1, double distance);

    List<StationAndPosition> getPileStationsForDistance(double lat1, double lng1, double distance);
	
}
