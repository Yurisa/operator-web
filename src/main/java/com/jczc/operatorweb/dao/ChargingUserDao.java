package com.jczc.operatorweb.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.jczc.operatorweb.entity.ChargingPrice;
import com.jczc.operatorweb.model.AccountDetail;
import com.jczc.operatorweb.model.ChargedFeeList;
import com.jczc.operatorweb.model.ChargingDataInfo;
import com.jczc.operatorweb.model.ChargingUser;
import com.jczc.operatorweb.model.UserAccount;

import java.util.Date;
import java.util.List;

/**
 * Created by lwj on 2017/10/25.
 */
@Component
public interface ChargingUserDao {
    /**
     * 根据手机号获取用户信息
     * @param mobile
     * @return
     */
    ChargingUser getUserByMobile(String mobile);

    /**
     * 用户注册
     * @param mobile
     * @param password
     */
    void userRegister(@Param("mobile") String mobile, @Param("password") String password);
    void userRegisterWithToken(@Param("mobile") String mobile, @Param("password") String password, @Param("token") String token);
    /**
     * 注册时创建用户账号
     * @param id
     */
    void createUserAccount(int id);

    /**
     * 用户修改密码
     * @param id
     * @param password
     */
    void updatePassword(@Param("userId") int id, @Param("password") String password);

    /**
     * 完善/修改用户个人信息
     * @param chargingUserReData
     */
    void editUserInfo(ChargingUser chargingUser);

    /**
     * 获取指定用户账户信息
     * @param id
     * @return
     */
    UserAccount getUserAccountByUserId(int id);

    /**
     * 获取用户余额
     * @param userId
     * @return
     */
    double getBalanceById(int userId);


    /**
     * 充值
     * @param balance
     */
    void recharge(@Param("balance") double balance,@Param("userId") int userId);

    /**
     * 获取指定用户指定时间段内的收支明细列表
     * @param id
     * @param startTime
     * @param endTime 
     * @return
     */
    List<AccountDetail>getAccountDetail(@Param("userId") int userId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);
    /**
     * 通过流水id获取账户流水
     * @param accountDetailId
     * @return
     */
    AccountDetail getAccountDetailById(@Param("accountDetailId")Long accountDetailId);
    /**
     * 获取指定充电费用详情
     * @param id
     * @return
     */
    List<ChargedFeeList> getChargeFeeDetail(@Param("accountDetailId")Long accountDetailId);

    /**
     * 获取指定账单的余额
     * @param id
     * @return
     */
    double getAmountById(Long id);

    /**
     * 修改账单状态->已付款
     * @param id
     */
    void payAccountDetail(@Param("accountDetailId")Long accountDetailId,@Param("payFrom")String payFrom,@Param("payOrderId")String payOrderId,@Param("payUserId")String payUserId,@Param("status")Integer status);

    /**
     * 完善/修改用户个人信息
     * @param accountReData
     */
    void editAccountInfo(UserAccount userAccount);

    /**
     * 获取用户个人信息
     * @param id
     * @return
     */
    ChargingUser getUserInfo(int id);

    /**
     * 根据微信号获取用户信息
     * @param wxpay
     * @return
     */
    ChargingUser searchUserInfo(String wxpay);

	/**
	 * 获取用户信息
	 * @param userId
	 * @return
	 */
	ChargingUser getUserById(int userId);
	
	/**
	 * 通过授权码找用户
	 * @param token
	 * @return
	 */
	ChargingUser getUserByAccessToken(String token);

	/**
	 * 更新用户登录授权码
	 * @param chargingUser
	 */
	void freshAccessToken(ChargingUser chargingUser);

	/**
	 * 创建用户账户收支详情
	 * @param detail
	 */
	Long createUserAccountDetail(AccountDetail detail);

	void createChargedFeeList(ChargedFeeList chargedFee);

	void setAccountDetail(AccountDetail detail);

}
