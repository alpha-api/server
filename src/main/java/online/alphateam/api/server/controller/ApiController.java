package online.alphateam.api.server.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import online.alphateam.api.server.service.ApiService;
import online.alphateam.api.server.util.Pager;
import online.alphateam.api.server.util.Result;
/**
 * 
 * www.alphateam.online
 * @Description 
 * @date 2021-08-08 
 * @author Michael liangzongc@gmail.com
 */
@Controller
@RequestMapping("/api/v1")
public class ApiController {
	@Autowired
	ApiService apiService;
	
	@RequestMapping(value="/{module}/{api}",method = RequestMethod.GET)
	@ResponseBody
	public Result<Object> get(HttpServletRequest request,HttpServletResponse response,@PathVariable String module,@PathVariable String api){
		Result<Object> result=new Result<Object>();		
		String pageNo=request.getParameter("pageNo");	
		String pageSize=request.getParameter("pageSize");	
		//判断是否需要进行分页查询
		if( pageNo == null || "".equals(pageNo) ) {
			List<Map<String,Object>> list=apiService.list(request, module, api);	
			result.setData(list);
		}else {
			//分页查询
			Pager<Map<String, Object>> pager=apiService.pager(request, module, api,Integer.parseInt(pageNo),Integer.parseInt(pageSize));
			result.setData(pager);
		}
		result.success("查询成功");		
		return result;		
	}
	
	@RequestMapping(value="/{module}/{api}",method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> post(HttpServletRequest request,HttpServletResponse response,@PathVariable String module,@PathVariable String api,@RequestBody String payload){
		Result<Object> result=new Result<Object>();		
		
		return result;		
	}
	
	@RequestMapping(value="/{module}/{api}",method = RequestMethod.PUT)
	@ResponseBody
	public Result<Object> put(HttpServletRequest request,HttpServletResponse response,@PathVariable String module,@PathVariable String api,@RequestBody String payload){
		Result<Object> result=new Result<Object>();		
		
		return result;		
	}
	
	
	
	@RequestMapping(value="/{module}/{api}",method = RequestMethod.DELETE)
	@ResponseBody
	public Result<Object> delete(HttpServletRequest request,HttpServletResponse response,@PathVariable String module,@PathVariable String api,@RequestBody String payload){
		//delete-无效方法request.getParameter,可以使用payload		
		Result<Object> result=new Result<Object>();		
		System.out.println( request.getParameter("name") );
		return result;		
	}
}
