package online.alphateam.api.server.service;

import online.alphateam.api.server.bean.dto.SysApiDTO;
import online.alphateam.api.server.bean.param.ApiParam;
import online.alphateam.api.server.bean.param.LoginParam;
import online.alphateam.api.server.bean.param.ModuleParam;
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

    void saveSysApi(ApiParam apiParam);

	void updateSysApi(ApiParam apiParam);

	void deleteSysApi(Integer apiId);

	void deleteSysApiChild(Integer type, Integer childId);

	SysApiDTO<?> getSysApi(Integer type, Integer detailId);

	List<SysApiDTO<?>> querySysApi(Integer moduleId, Integer datasourceId);
}
