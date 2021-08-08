package online.alphateam.api.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import online.alphateam.api.server.util.Result;

@Controller
@RequestMapping("/api/v1")
public class ApiController {
	
	@RequestMapping("/{module}/{api}")
	@ResponseBody
	public Result<Object> get(HttpServletRequest request,HttpServletResponse response,@PathVariable String module,@PathVariable String api){
		Result<Object> result=new Result<Object>();
		result.setMsg(request.getRequestURI());		
		result.setCopyright(request.getParameter("name"));
		System.out.println(module);
		return result;		
	}
}
