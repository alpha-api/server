package online.alphateam.api.server.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import online.alphateam.api.server.bean.param.LoginParam;
import online.alphateam.api.server.service.SystemService;
import online.alphateam.api.server.util.Result;

@RequestMapping("/sys")
@Controller
public class SystemController {
	private static final Logger logger = LoggerFactory.getLogger(SystemController.class);
	@Autowired 
	private SystemService systemService;
	@ResponseBody
	@PostMapping("/login")
	public Result<String> login(@RequestBody @Validated LoginParam loginParam){
		Result<String> result=new Result<String>();
		String token=systemService.login(loginParam);
		result.success("登陆成功", token);		
		return result;
	}
}
