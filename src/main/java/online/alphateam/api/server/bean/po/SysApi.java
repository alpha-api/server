package online.alphateam.api.server.bean.po;

import java.sql.Timestamp;

public class SysApi extends SysBasic {
	private Integer id;
	private String code;
	private String name;
	private Integer type;
	private String remark;
	private Integer sysModuleId;
	private Integer sysDatasourceId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getSysModuleId() {
		return sysModuleId;
	}
	public void setSysModuleId(Integer sysModuleId) {
		this.sysModuleId = sysModuleId;
	}
	public Integer getSysDatasourceId() {
		return sysDatasourceId;
	}
	public void setSysDatasourceId(Integer sysDatasourceId) {
		this.sysDatasourceId = sysDatasourceId;
	}
	

	
	
	
}
