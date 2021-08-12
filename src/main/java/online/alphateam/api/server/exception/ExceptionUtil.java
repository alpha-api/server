package online.alphateam.api.server.exception;
/**
 * 
 * www.alphateam.online
 * @Description 
 * @date 2021-08-09 
 * @author Michael liangzongc@gmail.com
 */
public class ExceptionUtil {
	public static final Class<?>[] BUSINESS=new Class<?>[]{
		SystemException.class,
		ApiException.class
	};

	public static boolean isBusiness(Exception e,Class<?>... classes){		
		for(Class<?> c : classes){			
			if( c.isInstance(e)){
				return true;		
			}			
		}	
		return false;
	}
	public static boolean isBusiness(Exception e){	
		Class<?>[] classes=BUSINESS;
		return isBusiness(e,classes);
	}
}
