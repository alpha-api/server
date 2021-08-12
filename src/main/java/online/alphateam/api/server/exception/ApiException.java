package online.alphateam.api.server.exception;
/**
 * 
 * www.alphateam.online
 * @Description 
 * @date 2021-08-12 
 * @author Michael liangzongc@gmail.com
 */
public class ApiException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private Object data;	
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}


	public ApiException() {
		super();
	}
	
	public ApiException(String message) {
		super(message);
	}
	
	public ApiException(String message,Object data) {		
		super(message);
		this.data=data;
	}
}
