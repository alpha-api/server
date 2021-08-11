package online.alphateam.api.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import online.alphateam.api.server.util.Result;

@ControllerAdvice
public class AllControllerAdvice {
	private static final Logger logger = LoggerFactory.getLogger(AllControllerAdvice.class);
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result<Object> error(Exception e) {
    	logger.info("", e);
    	Result<Object> result=new Result<Object>();
    	result.error(e);    	
		return result;       
    }

}
