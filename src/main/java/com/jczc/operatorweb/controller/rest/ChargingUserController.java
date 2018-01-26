package com.jczc.operatorweb.controller.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;
import com.jczc.operatorweb.exception.DataException;
import com.jczc.operatorweb.model.AccountDetail;
import com.jczc.operatorweb.model.ChargingUser;
import com.jczc.operatorweb.model.UserAccount;
import com.jczc.operatorweb.model.VehicleInfo;
import com.jczc.operatorweb.service.UserService;
import com.jczc.operatorweb.service.VehicleService;
import com.jczc.operatorweb.util.EncryptUtil;
import com.jczc.operatorweb.util.ResponseModel;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hpx on 2017/10/23.
 */
@RestController
@RequestMapping("/user")
@Api("用户功能")
public class ChargingUserController {//将所有的用户id换成access_token
	private static Logger logger= LoggerFactory.getLogger(ChargingUserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private VehicleService vehicleService;

//    @Autowired
//    private VehicleService vehicleService;

    /*登录相关*/

    /**
     * 用户登陆->登陆用户的信息
     * 用户每次登录都带上access_token(第一次可以为null)，每次登录都会换一个新的token
     * @param mobile
     * @param password
     * @return
     * @throws DataException
     */
    @PostMapping("/login")
    @ApiOperation("用户登录")
    public ResponseModel login(@RequestParam @ApiParam("手机号码") String mobile, @RequestParam @ApiParam("用户密码") String password) throws DataException {
        ChargingUser chargingUser = userService.login(mobile, password);
//    	ChargingUser chargingUser = userService.loginWithToken(mobile, password,accessToken);
        if(chargingUser == null) 
        	return new ResponseModel<>(false, "登录失败", null);
        else{
        	return new ResponseModel<>(true, "登录成功", chargingUser);
        }
    }

    @PostMapping("/{id}/password")
    @ApiOperation("用户修改密码")
    public ResponseModel updatePassword(ChargingUser curUser, @RequestParam @ApiParam("用户密码") String password,String oldPassword) throws DataException {
        boolean rst =userService.updatePassword(curUser.getId(), password,oldPassword);
        return new ResponseModel<>(rst,rst?"修改密码成功":"用户不存在或原密码不正确", null);
    }
    @PostMapping("/changePwd")
    @ApiOperation("用户修改密码")
    public ResponseModel changePassword(HttpServletRequest request,@RequestParam @ApiParam("用户密码") String password,String oldPassword) throws DataException {
        ChargingUser curUser=(ChargingUser)request.getAttribute("curUser");
    	logger.info("用户修改密码："+JSON.toJSONString(curUser));
    	boolean rst =userService.updatePassword(curUser.getId(), password,oldPassword);
        return new ResponseModel<>(rst,rst?"修改密码成功":"用户不存在或原密码不正确", null);
    }

    /**
     * 注册->注册标识
     *
     * @param mobile
     * @param password
     * @return
     * @throws DataException
     */
    @PostMapping("/register")
    @ApiOperation("用户注册")
    public ResponseModel register(@RequestParam @ApiParam("手机号码") String mobile, @RequestParam @ApiParam("用户密码") String password) throws DataException {
        return new ResponseModel<>(true, "注册成功", userService.register(mobile, password));
    }

    @PostMapping("/edit")
    @ApiOperation("用户修改个人基本信息")
    public ResponseModel editUserInfo(HttpServletRequest request,@ModelAttribute ChargingUser chargingUser) {
    	ChargingUser curUser=(ChargingUser)request.getAttribute("curUser");
    	chargingUser.setId(curUser.getId());
        userService.editUserInfo(chargingUser);
        return new ResponseModel<>(true, "修改信息成功", null);
    }

    @PostMapping("/{id}")
    @ApiOperation("根据id获取用户个人信息")
    public ResponseModel getUserInfo(@ApiParam("用户id") @PathVariable("id") int id) throws DataException {
        ChargingUser chargingUser = userService.getUserInfo(id);
        return (chargingUser == null) ? new ResponseModel<>(false, "该用户不存在", null) : new ResponseModel<>(true, "获取成功", chargingUser);
    }

    @PostMapping("/search/wx/{wx}")
    @ApiOperation("根据微信号获取用户个人信息")
    public ResponseModel searchUserInfo(@PathVariable("wx") String wxpay) {
        ChargingUser chargingUser = userService.searchUserInfo(wxpay);
        return (chargingUser == null) ? new ResponseModel<>(false, "该用户不存在", null) : new ResponseModel<>(true, "获取成功", chargingUser);
    }

   

    /*电动汽车相关*/

    @PostMapping("/{userId}/vehicle")
    @ApiOperation("用户添加个人电动汽车信息")
    public ResponseModel addVehicleInfo(@ModelAttribute VehicleInfo vehicle, @ApiParam("用户id") @PathVariable("userId") int userId) throws DataException {
    	vehicle.setChargingUserId(userId);
    	logger.info("准备添加车辆："+JSON.toJSONString(vehicle));
    	vehicleService.addVehicleInfo(vehicle);
        return new ResponseModel<>(true, "车辆添加成功", vehicle);
    }

    @GetMapping("/{id}/vehicle")
    @ApiOperation("获取指定用户的所有电动汽车信息")
    public ResponseModel getUserVehicles(@PathVariable("id") int id) {
        return new ResponseModel<>(true, "获取用户电动汽车信息成功", vehicleService.findByUserId(id));
    }
    @GetMapping("/vehicle/{id}")
    @ApiOperation("获取电动汽车信息")
    public ResponseModel getVehicleById(@ApiParam("电动汽车ID") @PathVariable("id") int id) {
        VehicleInfo vehicle=vehicleService.getByVehicleId(id);
        if(vehicle!=null)
        	return new ResponseModel<>(true, "通过id获取电动车信息成功", vehicle);
        else
        	return new ResponseModel<>(false, "通过id获取电动车西溪失败", id);
    }
    @GetMapping("/vehicle/no/{plateNo}")
    @ApiOperation("获取电动汽车信息")
    public ResponseModel getVehicleByPlateNo(@ApiParam("电动汽车ID") @PathVariable("plateNo") String plateNo) {
        VehicleInfo vehicle=vehicleService.getByPlateNo(plateNo);
        if(vehicle!=null)
        	return new ResponseModel<>(true, "通过车牌获取电动车信息成功", vehicle);
        else
        	return new ResponseModel<>(false, "通过车牌获取电动车西溪失败", plateNo);
    }
    @PostMapping("/vehicle/{userId}")
    @ApiOperation("修改电动汽车信息")
    public ResponseModel updateVehicleInfo(@ApiParam("电动汽车ID") @PathVariable("userId") int userId, @ModelAttribute VehicleInfo vehicle) {
    	vehicle.setChargingUserId(userId);
    	logger.info("修改车辆信息："+JSON.toJSONString(vehicle));
        vehicleService.updata(vehicle);
        return new ResponseModel<>(true, "车辆信息修改成功", vehicle);
    }


    @DeleteMapping("/vehicle/{id}")
    @ApiOperation("删除用户电动汽车")
    public ResponseModel deleteVehicle(@ApiParam("电动汽车id") @PathVariable("id") int id) throws DataException {
        vehicleService.deleteVehicle(id);
        return new ResponseModel<>(true, "车辆删除成功", id);
    }
//    @PostMapping("")
//    @ApiOperation("用户扫码启动充电")
//    public ResponseModel startCharge(@ApiParam("用户id") @PathVariable("userId")int userId,
//    		@ApiParam("用户id") @PathVariable("pileNo")String pileNo) throws DataException{
//    	// 1、验证userId是否合法
//    	// 2、验证pileNo是否存在
//    	// 3、验证用户是否可以在充电桩上充电
//    	// 4、发送启动充电消息
//    	ChargingUser user=userService.getUserById(userId);
//    	
//    	return new ResponseModel<>(true,"",null);
//    }
//    @PostMapping("")
//    @ApiOperation("用户扫码停止充电")
//    public ResponseModel stopCharge(@ApiParam("用户id") @PathVariable("userId")int userId,@ApiParam("用户id") @PathVariable("pileNo")String pileNo){
//    	
//    	return new ResponseModel<>(true,"",null);
//    }
    //充电桩相关
    @GetMapping("/piles")
    @ApiOperation("获取用户周围的充电桩")
    public ResponseModel getPilesForDistance(@ApiParam("纬度") @RequestParam double lat,
                                         @ApiParam("经度") @RequestParam double lng,
                                         @ApiParam("获取方圆多少米内的充电桩") @RequestParam double distance) {
        return new ResponseModel<>(true, "用户周围充电桩信息", userService.getPilesForDistance(lat, lng, distance));
    }

    @GetMapping("/pileStations")
    @ApiOperation("获取用户周围的充电站信息")
    public ResponseModel getPileGroupForDistance(@ApiParam("纬度") @RequestParam double lat,
                                             @ApiParam("经度") @RequestParam double lng,
                                             @ApiParam("获取方圆多少米内的充电桩") @RequestParam double distance) {
        return new ResponseModel<>(true, "用户周围充电桩信息", userService.getPileStationsForDistance(lat, lng, distance));
    }

    @ApiOperation("充电桩开始充电")
    @PostMapping("{id}/pile/start")
    public ResponseModel pileStartCharging(@ApiParam("充电桩序列号") @RequestParam String identification, @ApiParam("用户id") @RequestParam int id) {
        return new ResponseModel();

    }

    @ApiOperation("充电桩结束充电")
    @PostMapping("{id}/pile/end")
    public ResponseModel pileEndCharging(@ApiParam("充电桩序列号") @RequestParam String identification, @ApiParam("用户id") @RequestParam int id) {
        return new ResponseModel();
    }

}
