package online.alphateam.api.server.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online.alphateam.api.server.param.LoginParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
	public Result<String> login(@RequestBody LoginParam loginParam){
		return systemService.login(loginParam);
	}
}
