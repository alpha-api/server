package online.alphateam.api.server.controller;

import online.alphateam.api.server.exception.ParamException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import online.alphateam.api.server.util.Result;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AllControllerAdvice {
	private static final Logger logger = LoggerFactory.getLogger(AllControllerAdvice.class);

    @ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public Result<Object> error(Exception e) {
    	logger.info("系统异常", e);
    	Result<Object> result=new Result<Object>();
    	result.error(e.getMessage());
		return result;       
    }

    @ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = ParamException.class)
    public Result<Object> handleParamException(ParamException e) {
    	logger.error("参数异常：", e);
		Result<Object> result=new Result<Object>();
		result.error(e.getMessage());
		return result;
	}

}
