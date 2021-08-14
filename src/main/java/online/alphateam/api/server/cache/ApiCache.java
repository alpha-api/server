package online.alphateam.api.server.cache;

import java.util.HashMap;
import java.util.Map;

import online.alphateam.api.server.bean.bo.AlphaApiBO;

public class ApiCache {
	public static Map<String,AlphaApiBO> alphaApiCache=new HashMap<String, AlphaApiBO>();
	
	public static AlphaApiBO getAlphaApiBO(String module,String api,String requestMethod) {
		return alphaApiCache.get(module+"-"+api+"-"+requestMethod);		
	}
	
	public static AlphaApiBO putAlphaApiBO(String module,String api,String requestMethod,AlphaApiBO bo) {
		return alphaApiCache.put(module+"-"+api+"-"+requestMethod,bo);		
	}
}
