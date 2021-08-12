package online.alphateam.api.server.exception;

public class UserNotFoundException extends RuntimeException {
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


	public UserNotFoundException() {
		super();
	}

	public UserNotFoundException(String message) {
		super(message);
	}

	public UserNotFoundException(String message, Object data) {
		super(message);
		this.data=data;
	}
}
