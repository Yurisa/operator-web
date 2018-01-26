package com.jczc.operatorweb.controller.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.jczc.operatorweb.entity.ChargingPrice;
import com.jczc.operatorweb.model.ChargedListInfo;
import com.jczc.operatorweb.model.ChargingDataInfo;
import com.jczc.operatorweb.model.ChargingFeeInfo;
import com.jczc.operatorweb.model.ChargingUser;
import com.jczc.operatorweb.service.ChargedListService;
import com.jczc.operatorweb.service.ChargingAccountService;
import com.jczc.operatorweb.service.ChargingPriceService;
import com.jczc.operatorweb.util.DateUtil;
import com.jczc.operatorweb.util.ResponseModel;
import com.jczc.operatorweb.util.StringUtil;
import com.jiachang.jiachangwsclient.api.CommandHandler;
import com.jiachang.jiachangwsclient.model.CommandResult;


@RestController
@RequestMapping("charging")
public class ChargedListController {
	private static Logger logger= LoggerFactory.getLogger(ChargedListController.class);
	@Autowired
	ChargedListService  chargedListService;
	@Autowired
	ChargingAccountService chargingAccountService;
	@Autowired
	ChargingPriceService chargingPriceService;
    @RequestMapping("/start")
    public ResponseModel startCharging(HttpServletRequest request,ChargedListInfo chargedList){
    	ChargingUser curUser=(ChargingUser)request.getAttribute("curUser");
//    	Integer userId=chargedList.getChargingUserId();
    	Integer userId=curUser.getId();
//    	String carNo=chargedList.getVehicleNo();
    	String pileNo=chargedList.getChargingPileNo();
    	if(userId==null||pileNo==null)
    		return new ResponseModel<>(false, "信息不全，充电用户为空或充电桩为空",chargedList);
    	chargedList.setChargingUserId(userId);
    	chargedListService.startCharging(chargedList);
    	Optional<CommandResult> rst=CommandHandler.chargingControl(chargedList.getChargingPileNo(), 1);
        return new ResponseModel<>(rst.isPresent(), "",rst);
    }
    @RequestMapping("/end")
    public ResponseModel endCharging(ChargedListInfo chargedList){
    	String pileNo=chargedList.getChargingPileNo();
    	Date endTime=new Date();
    	chargedList=chargedListService.getPileCurrentChargedList(pileNo);
    	chargedList.setChargingEndTime(endTime);
    	//没有充电状态的记录或者记录中充电用户为空，则表示不是正在充电
    	if(chargedList==null||chargedList.getChargingUserId()==null)
    		return new ResponseModel<>(false, "该桩处于非充电状态",pileNo);
    	Optional<CommandResult> rst=CommandHandler.chargingControl(pileNo, 2);
    	if(rst.isPresent()){
    		int count=chargedListService.endCharging(pileNo,endTime);
    		if(count==0){
    			logger.error("停止充电操作失败："+pileNo);
    			return new ResponseModel<>(false, "停止充电操作失败",pileNo);
    		}
    		else{
    			logger.info("停止充电操作成功");
    			//结算电费
    			ChargingFeeInfo chargingFeeInfo=chargingAccountService.createChargingFee(chargedList);
    			if(chargingFeeInfo==null)
    				return new ResponseModel<>(false, "计费失败",chargedList);
    			else
    				return new ResponseModel<>(true, "计费成功",chargingFeeInfo);
    		}
    	}else{
    		logger.error("停止充电指令执行失败:"+pileNo);
    		return new ResponseModel<>(false, "停止充电指令执行失败",chargedList);
    	}
    }
//	public static void main(String[] args) {
//		System.out.println(parseDateStr("2017-12-18").toLocaleString());
//		System.out.println(parseDateStr("2017-12-18 10:10:10").toLocaleString());
//	}

    @RequestMapping("/history/pile/{pileNo}")
    public ResponseModel pileChargingHistory(@PathVariable("pileNo")String pileNo,
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
    	PageInfo<ChargedListInfo> page=chargedListService.getPileChargedList(pileNo, startTime, endTime, pageNum, pageSize);
    	if(page.getList()==null||page.getList().size()==0)
    		return new ResponseModel<>(false,"没有找到桩"+pileNo+"的符合要求的充电记录",null);
    	else
    		return new ResponseModel<>(true,"找到的桩"+pileNo+"的充电记录",page);
    }
    @RequestMapping("/history/user")
    public ResponseModel userChargingHistory(HttpServletRequest request,
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
    	PageInfo<ChargedListInfo> page=chargedListService.getUserChargedList(userId,startTime, endTime, pageNum, pageSize);
    	if(page.getList()==null||page.getList().size()==0)
    		return new ResponseModel<>(false,"没有找到用户"+userId+"的符合要求的充电记录",null);
    	else
    		return new ResponseModel<>(true,"找到的用户"+userId+"的充电记录",page);
    }
    @RequestMapping("/history/data/{chargedListId}")
    public ResponseModel chargingDataHistory(@PathVariable("chargedListId")Long chargedListId,
    		@RequestParam(value="pageNum",required=false)Integer pageNum,@RequestParam(value="pageSize",required=false)Integer pageSize){
    	if(pageNum==null)
    		pageNum=1;
    	if(pageSize==null)
    		pageSize=10;
    	ChargedListInfo chargedListInfo=chargedListService.getChargedList(chargedListId);
    	PageInfo<ChargingDataInfo> page=chargedListService.getHistoryChargingData(chargedListInfo, pageNum, pageSize);
    	if(page.getList()==null||page.getList().size()==0)
    		return new ResponseModel<>(false,"充电记录"+chargedListId+"没有充电数据",null);
    	else
    		return new ResponseModel<>(true,"充电记录"+chargedListId+"的充电数据",page);
    }
    
    @RequestMapping("/current/chargedList")
    public ResponseModel getCurrentChargedList(HttpServletRequest request){
    	ChargingUser curUser=(ChargingUser)request.getAttribute("curUser");
    	Integer userId=curUser.getId();
    	ChargedListInfo userCurChargedList=chargedListService.getUserCurrentChargedList(userId);
    	if(userCurChargedList!=null)
    		return new ResponseModel<>(true,"用户"+userId+"正在使用的充电记录",userCurChargedList);
    	else
    		return new ResponseModel<>(false,"用户"+userId+"没有正在使用的充电记录",null);
    }
    @RequestMapping("/current/data")
    public ResponseModel getCurrentChargingData(HttpServletRequest request){
    	ChargingUser curUser=(ChargingUser)request.getAttribute("curUser");
    	Integer userId=curUser.getId();
    	ChargedListInfo chargedListInfo=chargedListService.getUserCurrentChargedList(userId);
    	if(chargedListInfo==null){
    		return new ResponseModel<>(false,"用户当前没有在充电",userId);
    	}
//    	PageInfo<ChargingDataInfo> page=chargedListService.getHistoryChargingData(chargedListInfo, 1, 1);
//    	ChargingPrice price=chargingPriceService.getPileChargingPrice(chargedListInfo.getChargingPileNo());
    	
//    	if(page.getList()!=null&&page.getList().size()>0){
    	chargedListInfo.setChargingEndTime(new Date());
    	chargedListInfo.setStatus(0);//未付款
    	ChargingFeeInfo curChargingFee=chargingAccountService.getCurrentChargingFee(chargedListInfo);
    	if(curChargingFee!=null)
    		return new ResponseModel<>(true,"用户"+userId+"的当前充电数据",curChargingFee);
    	else
    		return new ResponseModel<>(false,"没有充电数据",null);
//    	}
    	
    }
    @RequestMapping("/price/{pileNo}")
    public ResponseModel getChargingPrice(@PathVariable("pileNo")String pileNo){
    	ChargingPrice price=chargingPriceService.getPileChargingPrice(pileNo);
    	if(price==null)
    		return new ResponseModel<>(false,"桩不存在或桩的电价还没有配置",pileNo);
    	else
    		return new ResponseModel<>(true,"桩"+pileNo+"的电价",price);
    }
}
