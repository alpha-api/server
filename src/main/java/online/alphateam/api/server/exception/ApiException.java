package online.alphateam.api.server.exception;

public class ApiException extends RuntimeException {
	/**
	 * 
	 */
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
