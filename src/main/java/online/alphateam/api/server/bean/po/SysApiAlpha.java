package online.alphateam.api.server.bean.po;

public class SysApiAlpha {
	private Integer id;
	private String requestMethod;
	private String sql;
	private Integer sysApiId;
	private Integer isUse;
	private Integer isDelete;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRequestMethod() {
		return requestMethod;
	}
	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public Integer getSysApiId() {
		return sysApiId;
	}
	public void setSysApiId(Integer sysApiId) {
		this.sysApiId = sysApiId;
	}
	public Integer getIsUse() {
		return isUse;
	}
	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
}
