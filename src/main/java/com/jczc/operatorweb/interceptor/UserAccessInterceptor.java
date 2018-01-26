package com.jczc.operatorweb.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.jczc.operatorweb.model.ChargingUser;
import com.jczc.operatorweb.service.UserService;
import com.jczc.operatorweb.util.ResponseModel;
@Component
public class UserAccessInterceptor implements HandlerInterceptor{
	private static Logger logger= LoggerFactory.getLogger(UserAccessInterceptor.class);
	@Autowired
	private UserService userService;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		logger.info("user access "+ request.getRequestURI());
		String token=request.getParameter("accessToken");
		response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
		if(token==null){
			ResponseModel res=new ResponseModel<>(false,"请带上accessToken访问"+request.getRequestURI(),null);
			response.getWriter().write(JSON.toJSONString(res));
			return false;
		}
		ChargingUser user=userService.getUserByAccessToken(token);
		if(user==null){
			ResponseModel res=new ResponseModel<>(false,"access_token不存在或过期",null);
			response.getWriter().write(JSON.toJSONString(res));
			return false;
		}
		logger.info("get charging user by accessToken:"+token+",user:"+JSON.toJSONString(user));
		request.setAttribute("curUser", user);
		return true;
	}
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
