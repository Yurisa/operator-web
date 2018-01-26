package com.jczc.operatorweb.controller.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jczc.operatorweb.exception.DataException;
import com.jczc.operatorweb.util.ResponseModel;

@ControllerAdvice
public class DataExceptionAdvice {
	private static Logger logger= LoggerFactory.getLogger(DataExceptionAdvice.class);
	@ResponseBody
	@ExceptionHandler(DataException.class)  
    public ResponseModel handleDataException(DataException ex){
		logger.error("DataException ocurred:"+ex.getResponse().getMessage());
		ResponseModel res=new ResponseModel<>(false,ex.getResponse().getMessage(),null);
        return res;
    } 
	@ResponseBody
	@ExceptionHandler(MissingServletRequestParameterException.class)  
    public ResponseModel paramsBindException(MissingServletRequestParameterException ex){
		logger.error("MissingServletRequestParameterException ocurred,path:"+ex.getMessage());
		ResponseModel res=new ResponseModel<>(false,ex.getMessage(),null);
        return res;
    } 
	
}
