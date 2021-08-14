package online.alphateam.api.server.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CachePool {
	public static Map<String,CacheBean<Object>> pool=new ConcurrentHashMap<String, CacheBean<Object>>();
	public static long expiryTime= 1000*60*30l;	
	public static void put(String key,Object obj) {
		CacheBean<Object> bean=new CacheBean<Object>();		
		long time=System.currentTimeMillis();
		time += expiryTime;
		bean.setData(obj);
		bean.setExpiryTime(time);
		pool.put(key, bean);
	}
	public static <T> T get(String key,Class<T> T) {
		CacheBean<Object> bean=pool.get(key);
		if( bean != null ) {
			T data=(T)bean.getData();	
			return data;			
		}			
		return null;		
	}
}
