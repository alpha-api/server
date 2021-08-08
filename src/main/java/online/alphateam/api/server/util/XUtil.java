package online.alphateam.api.server.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 
 * www.alphateam.online
 * @Description 
 * @date 2021-08-08 
 * @author 梁文正 liangzongc@gmail.com
 */
public class XUtil {	
	public static String toJSON(Object value) {
		String json=null;
		ObjectMapper objectMapper=new ObjectMapper();		
		try {
			json=objectMapper.writeValueAsString(value);
		} catch (JsonProcessingException e) {			
			e.printStackTrace();
			throw new RuntimeException("对象转JSON字符串失败");
		}		
		return json;		
	}
}
