package online.alphateam.api.server.util;
/**
 * 
 * www.alphateam.online
 * @Description 
 * @date 2021-08-07 
 * @author 梁文正 liangzongc@gmail.com
 */
public class Result<T> {
	private Integer status;//成功[1] 失败[0] 
	private String msg;//讯息
	private T data;//数据
	private String copyright;//版权信息	
	public Result() {
		super();
	}	
	public Result(Integer status, String msg, T data, String copyright) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
		this.copyright = copyright;
	}	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}

	public String getCopyright() {
		return copyright;
	}
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}
	@Override
	public String toString() {
		return "Result [status=" + status + ", msg=" + msg + ", data=" + data + ", copyright=" + copyright + "]";
	}
}
