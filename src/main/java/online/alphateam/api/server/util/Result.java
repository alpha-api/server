package online.alphateam.api.server.util;

import online.alphateam.api.server.exception.ExceptionUtil;

/**
 * 
 * www.alphateam.online
 * @Description 
 * @date 2021-08-07 
 * @author Michael liangzongc@gmail.com
 */
public class Result<T> {
	private Integer status;//成功[1] 失败[0] 
	private String msg;//讯息
	private T data;//数据
	private String copyright="Michael（liangzongc@gmail.com）| Krypton（lqbnet@yeah.net）  ";//版权信息	
	public static final Integer SUCCESS=1;//成功
	public static final Integer ERROR=0;//失败
	public static final String UNKNOWN_REASON="未知异常";
	
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
	

	public void success(){
		this.status=SUCCESS;
	}	
	public void success(String msg){
		this.status=SUCCESS;
		this.msg=msg;
	}
	public void success(T data){
		this.status=SUCCESS;
		this.data=data;	
	}
	public void success(String msg,T data){
		this.status=SUCCESS;
		this.msg=msg;
		this.data=data;	
	}

	public void error(){
		this.status=ERROR;
	}
	
	public void error(String msg){
		this.status=ERROR;
		this.msg=msg;
	}
	
	public void error(String msg,T data){
		this.status=ERROR;
		this.msg=msg;
		this.data=data;	
	}
	
	public void error(Exception e){
		this.status=ERROR;
	   	boolean isBusiness=ExceptionUtil.isBusiness(e);
		if(isBusiness){
			this.msg=e.getMessage();			
		}else{
			this.msg=UNKNOWN_REASON+":"+e.getMessage();			
		}
	}
	
	
	public void error(Exception e,Class<?>... classes){
		this.status=ERROR;
	   	boolean isBusiness=ExceptionUtil.isBusiness(e,classes);
		if(isBusiness){
			this.msg=e.getMessage();			
		}else{
			this.msg=UNKNOWN_REASON;			
		}
	}
	
}
