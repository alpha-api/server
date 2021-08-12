package online.alphateam.api.server.exception;

public class ParamException extends RuntimeException {
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


	public ParamException() {
		super();
	}

	public ParamException(String message) {
		super(message);
	}

	public ParamException(String message, Object data) {
		super(message);
		this.data=data;
	}
}
