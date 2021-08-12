package online.alphateam.api.server.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 
 * www.alphateam.online
 * @Description 
 * @date 2021-08-08 
 * @author Michael liangzongc@gmail.com
 */
public class XUtil {	
	public static String toJSON(Object value) {
		String json=null;
		ObjectMapper objectMapper=new ObjectMapper();		
		try {
			json=objectMapper.writeValueAsString(value);
		} catch (JsonProcessingException e) {			
			throw new RuntimeException("对象转JSON字符串失败",e);
		}		
		return json;		
	}
	
	public static String[] arrayFromString(String jsonArrayString) {
		ObjectMapper objectMapper=new ObjectMapper();
		String[] array;
		try {
			array=objectMapper.readValue(jsonArrayString, String[].class);
		} catch (JsonProcessingException e) {			
			throw new RuntimeException("JSON字符串转String[]失败",e);
		}
		return array;		
	}
}
