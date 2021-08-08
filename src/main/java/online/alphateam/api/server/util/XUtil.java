package online.alphateam.api.server.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
