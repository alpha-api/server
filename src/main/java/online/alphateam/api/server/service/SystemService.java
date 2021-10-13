package online.alphateam.api.server.service;

import online.alphateam.api.server.bean.dto.SysApiDTO;
import online.alphateam.api.server.bean.param.*;
import online.alphateam.api.server.bean.po.SysDatasource;
import online.alphateam.api.server.bean.po.SysModule;
import online.alphateam.api.server.bean.po.SysUser;
import java.util.List;

/**
 * 
 * www.alphateam.online
 * @Description 
 * @date 2021-08-08 
 * @author Michael liangzongc@gmail.com
 */
public interface SystemService {
	String login(LoginParam loginParam);

	void saveSysModule(ModuleParam moduleParam, SysUser user);

	void updateSysModule(ModuleParam moduleParam, SysUser user);

	void deleteSysModule(Integer moduleId, SysUser user);

	SysModule querySysModuleById(Integer moduleId);

	List<SysModule> queryAllSysModule();

    long saveSysApi(ApiParam apiParam, SysUser user);

	void updateSysApi(ApiParam apiParam, SysUser user);

	void deleteSysApi(Integer apiId, SysUser user);

	SysApiDTO getSysApi(Integer apiId);

	List<SysApiDTO> querySysApi(Integer moduleId, Integer datasourceId);

    List<SysDatasource> queryAllSysDatasource();

	long saveSysApiAlpha(AlphaParam param);

	void updateSysApiAlpha(AlphaParam param);

	void deleteSysApiAlpha(Integer alphaId);

	void saveSysDatasource(DatasourceParam param, SysUser user);

	void updateSysDatasource(DatasourceParam param, SysUser user);

	void deleteSysDatasource(Integer datasourceId, SysUser user);
}
