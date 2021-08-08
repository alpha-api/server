package online.alphateam.api.server.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import online.alphateam.api.server.service.ApiService;
import online.alphateam.api.server.util.Result;
/**
 * 
 * www.alphateam.online
 * @Description 
 * @date 2021-08-08 
 * @author 梁文正 liangzongc@gmail.com
 */
@Controller
@RequestMapping("/api/v1")
public class ApiController {
	@Autowired
	ApiService apiService;
	
	@RequestMapping("/{module}/{api}")
	@ResponseBody
	public Result<Object> get(HttpServletRequest request,HttpServletResponse response,@PathVariable String module,@PathVariable String api){
		Result<Object> result=new Result<Object>();		
		String pageNo=request.getParameter("pageNo");		
		try {
			//判断是否需要进行分页查询
			if( pageNo == null || "".equals(pageNo) ) {
				List<Map<String,Object>> list=apiService.list(request, module, api);	
				result.setData(list);
			}else {
				//分页查询
				
			}
			result.setStatus(1);
			result.setMsg("success");
		} catch (Exception e) {
			result.setStatus(0);
			result.setMsg(e.getMessage());
		}		
		return result;		
	}
}
