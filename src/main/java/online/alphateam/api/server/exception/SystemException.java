package online.alphateam.api.server.exception;

public class SystemException extends RuntimeException {
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


	public SystemException() {
		super();
	}
	
	public SystemException(String message) {
		super(message);
	}
	
	public SystemException(String message,Object data) {		
		super(message);
		this.data=data;
	}
}
