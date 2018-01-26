package com.jczc.operatorweb.controller.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.jczc.operatorweb.enums.PayMethodEnum;
import com.jczc.operatorweb.exception.DataException;
import com.jczc.operatorweb.model.AccountDetail;
import com.jczc.operatorweb.model.ChargedListInfo;
import com.jczc.operatorweb.model.ChargingFeeInfo;
import com.jczc.operatorweb.model.ChargingUser;
import com.jczc.operatorweb.model.UserAccount;
import com.jczc.operatorweb.service.ChargedListService;
import com.jczc.operatorweb.service.ChargingAccountService;
import com.jczc.operatorweb.service.UserService;
import com.jczc.operatorweb.util.DateUtil;
import com.jczc.operatorweb.util.ResponseModel;
import com.jczc.operatorweb.util.StringUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import retrofit2.http.Path;
@RestController
@RequestMapping("/account")
public class ChargingAccountController {
	private static Logger logger= LoggerFactory.getLogger(ChargingAccountController.class);
	@Autowired
	ChargingAccountService chargingAccountService;
	@Autowired
	ChargedListService chargedListService;
	@Autowired
	UserService userService;
    @RequestMapping("/user")
    public ResponseModel getUserChargingFeeHistory(HttpServletRequest request,
    		@RequestParam(value="startDate",required=false)String startDate,@RequestParam(value="endDate",required=false)String endDate,
    		@RequestParam(value="pageNum",required=false)Integer pageNum,@RequestParam(value="pageSize",required=false)Integer pageSize){
    	if(pageNum==null)
    		pageNum=1;
    	if(pageSize==null)
    		pageSize=10;
    	Date startTime=DateUtil.getFirtDate();
    	if(!StringUtil.isEmpty(startDate))
    		startTime=DateUtil.parseDateStr(startDate);
    	Date endTime=new Date();
    	if(!StringUtil.isEmpty(endDate))
    		endTime=DateUtil.parseDateStr(endDate);
    	ChargingUser curUser=(ChargingUser)request.getAttribute("curUser");
    	Integer userId=curUser.getId();
    	PageInfo<ChargingFeeInfo> page=chargingAccountService.getChargingFeeListByUserId(userId, startTime, endTime, pageNum, pageSize);
    	if(page.getList()==null||page.getList().size()==0)
    		return new ResponseModel<>(false,"没有找到用户"+userId+"的符合要求的充电记录",null);
    	else
    		return new ResponseModel<>(true,"找到的用户"+userId+"的充电记录",page);
    }
    @RequestMapping("/pile/{pileNo}")
    public ResponseModel getChargingFeeByPileNo(@PathVariable("pileNo")String pileNo,
    		@RequestParam(value="startDate",required=false)String startDate,@RequestParam(value="endDate",required=false)String endDate,
    		@RequestParam(value="pageNum",required=false)Integer pageNum,@RequestParam(value="pageSize",required=false)Integer pageSize){
    	if(pageNum==null)
    		pageNum=1;
    	if(pageSize==null)
    		pageSize=10;
    	Date startTime=DateUtil.getFirtDate();
    	if(!StringUtil.isEmpty(startDate))
    		startTime=DateUtil.parseDateStr(startDate);
    	Date endTime=new Date();
    	if(!StringUtil.isEmpty(endDate))
    		endTime=DateUtil.parseDateStr(endDate);
    	
    	PageInfo<ChargingFeeInfo> page=chargingAccountService.getChargingFeeListByPileNo(pileNo, startTime, endTime, pageNum, pageSize);
    	if(page.getList()==null||page.getList().size()==0)
    		return new ResponseModel<>(false,"没有找到桩"+pileNo+"的符合要求的充电记录",null);
    	else
    		return new ResponseModel<>(true,"找到的桩"+pileNo+"的充电记录",page);
    }
    @RequestMapping("/pay/{chargedListId}")//余额支付
    public ResponseModel payChargedList(HttpServletRequest request,@PathVariable("chargedListId")Long chargedListId){
    	ChargingFeeInfo chargingFeeInfo=chargingAccountService.getChargingFeeInfoByChargedListId(chargedListId);
    	if(chargingFeeInfo==null)
    		return new ResponseModel<>(false,"充电费用记录不存在",chargedListId);
    	if(chargingFeeInfo.getEndTime()==null)
    		return new ResponseModel<>(false,"充电还没结束",chargingFeeInfo);
    	ChargingUser curUser=(ChargingUser)request.getAttribute("curUser");
    	Integer userId=curUser.getId();
    	try {
			userService.payBill(userId, chargingFeeInfo.getAccountDetailId(),PayMethodEnum.BALANCE.getName(),"","");
		} catch (DataException e) {
			// TODO Auto-generated catch block
			logger.error("充电记录"+chargedListId+"的电费支付失败"+e.getResponse().getMessage());
			return new ResponseModel<>(false,"充电记录"+chargedListId+"的电费支付失败",e.getResponse().getMessage());
		}
    	chargingFeeInfo.setStatus(1);
    	return new ResponseModel<>(true,"余额成功支付充电记录"+chargedListId+"的电费",chargingFeeInfo);
    }
    @RequestMapping("/pay/wx/{chargedListId}")//微信支付
    public ResponseModel wxPayChargedList(HttpServletRequest request,@PathVariable("chargedListId")Long chargedListId,String wxOrderId,String wxUserId){
    	ChargingFeeInfo chargingFeeInfo=chargingAccountService.getChargingFeeInfoByChargedListId(chargedListId);
    	if(chargingFeeInfo==null)
    		return new ResponseModel<>(false,"充电费用记录不存在",chargedListId);
    	if(chargingFeeInfo.getEndTime()==null)
    		return new ResponseModel<>(false,"充电还没结束",chargingFeeInfo);
    	ChargingUser curUser=(ChargingUser)request.getAttribute("curUser");
    	Integer userId=curUser.getId();
    	try {
			userService.payBill(userId, chargingFeeInfo.getAccountDetailId(),PayMethodEnum.WX.getName(),wxOrderId,wxUserId);
		} catch (DataException e) {
			// TODO Auto-generated catch block
			logger.error("充电记录"+chargedListId+"的电费支付失败"+e.getResponse().getMessage());
			return new ResponseModel<>(false,"充电记录"+chargedListId+"的电费支付失败",e.getResponse().getMessage());
		}
    	chargingFeeInfo.setStatus(1);
    	return new ResponseModel<>(true,"微信成功支付充电记录"+chargedListId+"的电费",chargingFeeInfo);
    }
    @RequestMapping("/pay/ali/{chargedListId}")//支付宝支付
    public ResponseModel aliPayChargedList(HttpServletRequest request,@PathVariable("chargedListId")Long chargedListId,String aliOrderId,String aliUserId){
    	ChargingFeeInfo chargingFeeInfo=chargingAccountService.getChargingFeeInfoByChargedListId(chargedListId);
    	if(chargingFeeInfo==null)
    		return new ResponseModel<>(false,"充电费用记录不存在",chargedListId);
    	if(chargingFeeInfo.getEndTime()==null)
    		return new ResponseModel<>(false,"充电还没结束",chargingFeeInfo);
    	ChargingUser curUser=(ChargingUser)request.getAttribute("curUser");
    	Integer userId=curUser.getId();
    	try {
			userService.payBill(userId, chargingFeeInfo.getAccountDetailId(),PayMethodEnum.ALI.getName(),aliOrderId,aliUserId);
		} catch (DataException e) {
			// TODO Auto-generated catch block
			logger.error("充电记录"+chargedListId+"的电费支付失败"+e.getMessage());
			e.printStackTrace();
			return new ResponseModel<>(false,"充电记录"+chargedListId+"的电费支付失败",e.getResponse().getMessage());
		}
    	chargingFeeInfo.setStatus(1);
    	return new ResponseModel<>(true,"支付宝成功支付充电记录"+chargedListId+"的电费",chargingFeeInfo);
    }
    @RequestMapping("/recharge/wx")
    public ResponseModel rechargeWx(HttpServletRequest request,Double amount,String wxOrderId,String wxUserId){
    	ChargingUser curUser=(ChargingUser)request.getAttribute("curUser");
    	Integer userId=curUser.getId();
    	try {
			chargingAccountService.recharge(userId,amount,PayMethodEnum.WX.getName(),wxOrderId,wxUserId);
		} catch (DataException e) {
			logger.error("用户"+userId+"充值失败："+e.getMessage());
			return new ResponseModel<>(false,"用户"+userId+"充值失败",amount);
		}
    	return new ResponseModel<>(true,"用户"+userId+"充值成功",amount);
    }
    @RequestMapping("/recharge/ali")
    public ResponseModel rechargeAlipay(HttpServletRequest request,Double amount,String aliOrderId,String aliUserId){
    	ChargingUser curUser=(ChargingUser)request.getAttribute("curUser");
    	Integer userId=curUser.getId();
    	try {
			chargingAccountService.recharge(userId,amount,PayMethodEnum.ALI.getName(),aliOrderId,aliUserId);
		} catch (DataException e) {
			logger.error("用户"+userId+"充值失败："+e.getResponse().getMessage());
			return new ResponseModel<>(false,"用户"+userId+"充值失败",e.getResponse().getMessage());
		}
    	return new ResponseModel<>(true,"用户"+userId+"支付宝充值成功",amount);
    }
    @RequestMapping("/recharge/history")
    public ResponseModel rechargeHistory(HttpServletRequest request,
    		@RequestParam(value="startDate",required=false)String startDate,@RequestParam(value="endDate",required=false)String endDate,
    		@RequestParam(value="pageNum",required=false)Integer pageNum,@RequestParam(value="pageSize",required=false)Integer pageSize){
    	if(pageNum==null)
    		pageNum=1;
    	if(pageSize==null)
    		pageSize=10;
    	Date startTime=DateUtil.getFirtDate();
    	if(!StringUtil.isEmpty(startDate))
    		startTime=DateUtil.parseDateStr(startDate);
    	Date endTime=new Date();
    	if(!StringUtil.isEmpty(endDate))
    		endTime=DateUtil.parseDateStr(endDate);
    	ChargingUser curUser=(ChargingUser)request.getAttribute("curUser");
    	Integer userId=curUser.getId();
    	PageInfo<AccountDetail> page=chargingAccountService.rechargeHistory(userId,startTime,endTime,pageNum,pageSize);
		return new ResponseModel<>(true,"成功获取用户"+userId+"的电子钱包充值记录",page);
    	
    }
    /*账户相关*/
    @GetMapping("")
    public ResponseModel<UserAccount> getAccountInfo(HttpServletRequest request) {
    	ChargingUser curUser=(ChargingUser)request.getAttribute("curUser");
    	Integer userId=curUser.getId();
        return new ResponseModel<>(true, "用户账户信息", userService.getUserAccountByUserId(userId));
    }

    @PostMapping("")
    @ApiOperation("完善用户的账户信息")
    public ResponseModel<UserAccount> editAccountInfo(HttpServletRequest request,UserAccount userAccount) {
    	ChargingUser curUser=(ChargingUser)request.getAttribute("curUser");
    	Integer userId=curUser.getId();
    	userAccount.setChargingUserId(userId);
        userService.editAccountInfo(userAccount);
        return new ResponseModel<>(true, "修改账户信息成功", null);
    }


    /*账单相关*/

    /**
     * 获取指定账单明细
     *
     * @param id
     * @return
     */
    @RequestMapping("/detail/{accountDetailId}/feeList")
    @ApiOperation("获取指定账单费用流水的费用名字")
    public ResponseModel getChargeFeeDetail(HttpServletRequest request,@PathVariable("accountDetailId") @ApiParam("账户流水Id") Long accountDetailId) {
    	ChargingUser curUser=(ChargingUser)request.getAttribute("curUser");
    	Integer userId=curUser.getId();
    	AccountDetail detail=userService.getAccountDetailById(accountDetailId);
    	Long accountId=detail.getAccountId();
    	UserAccount account=userService.getUserAccountByUserId(userId);
    	if(account==null||account.getId()!=accountId)
    		return new ResponseModel<>(false, "账单流水"+accountDetailId+"不属于当前用户", userId);
    	else
    		return new ResponseModel<>(true, "账单流水"+accountDetailId+"的费用明细", userService.getChargeFeeDetail(accountDetailId));
    }

    /**
     * 用户账单流水查询
     *
     * @param id
     * @return
     */
    @RequestMapping("/detail")
    @ApiOperation("用户账单流水查询")
    public ResponseModel<List<AccountDetail>> accountDetail( HttpServletRequest request,
    		@ApiParam("开始时间") @RequestParam(required = false) String startTime, 
    		@ApiParam("结束时间") @RequestParam(required = false) String endTime) {
    	Date start=DateUtil.getFirtDate();
    	if(!StringUtil.isEmpty(startTime))
    		start=DateUtil.parseDateStr(startTime);
    	Date end=new Date();
    	if(!StringUtil.isEmpty(endTime))
    		end=DateUtil.parseDateStr(endTime);
    	ChargingUser curUser=(ChargingUser)request.getAttribute("curUser");
    	Integer userId=curUser.getId();
        return new ResponseModel<>(true, "用户的账单流水", userService.getAccountDetail(userId, start, end));

    }
    /**
     * 用户账单流水查询
     *
     * @param id
     * @return
     */
    @RequestMapping("/detail/{accountDetailId}")
    @ApiOperation("用户账单流水查询")
    public ResponseModel  accountDetailById( HttpServletRequest request,@PathVariable("accountDetailId")Long accountDetailId) {
    	ChargingUser curUser=(ChargingUser)request.getAttribute("curUser");
    	Integer userId=curUser.getId();
    	AccountDetail detail=userService.getAccountDetailById(accountDetailId);
    	Long accountId=detail.getAccountId();
    	UserAccount account=userService.getUserAccountByUserId(userId);
    	if(account==null||account.getId()!=accountId)
    		return new ResponseModel<>(false, "账单流水"+accountDetailId+"不属于当前用户", userId);
    	else
    		return new ResponseModel<>(true, "账单流水"+accountDetailId+"的费用明细", detail);

    }
}
