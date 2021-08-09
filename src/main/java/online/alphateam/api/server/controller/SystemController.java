package online.alphateam.api.server.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import online.alphateam.api.server.service.SystemService;
import online.alphateam.api.server.util.Result;

@RequestMapping("/sys")
@Controller
public class SystemController {
	private static final Logger logger = LoggerFactory.getLogger(SystemController.class);
	@Autowired 
	private SystemService systemService;
	@ResponseBody
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public Result<String> login(String userCode,String password,HttpServletRequest request,HttpServletResponse response){
		Result<String> result=new Result<String>();
		try {
			String token=systemService.login(userCode, password);
			result.success("登陆成功", token);
		} catch (Exception e) {
			logger.error("登陆失败", e);		
			result.error(e);			
		}		
		return result;
	}
}
