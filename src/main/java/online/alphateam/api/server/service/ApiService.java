package online.alphateam.api.server.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import online.alphateam.api.server.util.Pager;
/**
 * 
 * www.alphateam.online
 * @Description 
 * @date 2021-08-08 
 * @author Michael liangzongc@gmail.com
 */
public interface ApiService {
	/**
	 * 普通查询
	 * @param request
	 * @param module
	 * @param api
	 * @return
	 * @date 2021-08-09 
	 * @author Michael liangzongc@gmail.com
	 */
	List<Map<String,Object>> list(HttpServletRequest request,String module,String api);
	/**
	 * 分页查询
	 * @param request
	 * @param module
	 * @param api
	 * @return
	 * @date 2021-08-09 
	 * @author Michael liangzongc@gmail.com
	 */
	Pager<Map<String, Object>> pager(HttpServletRequest request, String module, String api,Integer pageNo,Integer pageSize);
}
