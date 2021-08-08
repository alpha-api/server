package online.alphateam.api.server.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
/**
 * 
 * www.alphateam.online
 * @Description 
 * @date 2021-08-08 
 * @author 梁文正 liangzongc@gmail.com
 */
public interface ApiService {
	List<Map<String,Object>> list(HttpServletRequest request,String module,String api);
}
