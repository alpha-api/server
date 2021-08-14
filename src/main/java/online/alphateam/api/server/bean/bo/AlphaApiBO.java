package online.alphateam.api.server.bean.bo;

import online.alphateam.api.server.bean.po.SysApi;
import online.alphateam.api.server.bean.po.SysApiAlpha;
import online.alphateam.api.server.bean.po.SysModule;
/**
 * 
 * www.alphateam.online
 * @Description 
 * @date 2021-08-13 
 * @author Michael liangzongc@gmail.com
 */
public class AlphaApiBO {
	private SysModule sysModule;
	private SysApi sysApi;
	private SysApiAlpha sysApiAlpha;
	public SysModule getSysModule() {
		return sysModule;
	}
	public void setSysModule(SysModule sysModule) {
		this.sysModule = sysModule;
	}
	public SysApi getSysApi() {
		return sysApi;
	}
	public void setSysApi(SysApi sysApi) {
		this.sysApi = sysApi;
	}
	public SysApiAlpha getSysApiAlpha() {
		return sysApiAlpha;
	}
	public void setSysApiAlpha(SysApiAlpha sysApiAlpha) {
		this.sysApiAlpha = sysApiAlpha;
	}
	
	public String getSql() {		
		return sysApiAlpha.getSql();
	}
	
	public Integer getDatasourceId() {		
		return sysApi.getSysDatasourceId();
	}
}
