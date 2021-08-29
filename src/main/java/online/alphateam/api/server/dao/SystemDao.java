package online.alphateam.api.server.dao;

import java.util.List;
import java.util.Map;

import online.alphateam.api.server.bean.dto.SysApiDTO;
import online.alphateam.api.server.bean.param.AlphaParam;
import online.alphateam.api.server.bean.param.ApiParam;
import online.alphateam.api.server.bean.param.ModuleParam;
import online.alphateam.api.server.bean.po.*;

/**
 * 系统模块的dao
 * www.alphateam.online
 * @Description 
 * @date 2021-08-08 
 * @author Michael liangzongc@gmail.com
 */
public interface SystemDao {
	/**
	 * 根据ID获取数据源配置信息
	 * @param id
	 * @return
	 * @date 2021-08-08 
	 * @author Michael liangzongc@gmail.com
	 */
	public Map<String,Object> getDatasource(String id);

	/**
	 * 根据code查询用户
	 * @param code 用户编号
	 * @return
	 * @date 2021-08-12
	 * @author krypton lqbnet@yeah.net
	 */
	SysUser queryUserByCode(String code);
	/**
	 * 
	 * @param code
	 * @return
	 * @date 2021-08-13 
	 * @author Michael liangzongc@gmail.com
	 */
	SysModule getSysModule(String code);
	/**
	 * 
	 * @param moduleId
	 * @param apiCode
	 * @return
	 * @date 2021-08-13 
	 * @author Michael liangzongc@gmail.com
	 */
	SysApi getSysApi(Integer moduleId,String apiCode);
	
	SysApiAlpha getSysApiAlpha(Integer apiId,String requestMethod);
	
	List<SysApiAlpha> listSysApiAlpha(Integer apiId);

	/**
	 * 根据模块编号统计数量
	 * @param code
	 * @param id 当传入id不为null时，排除这个id的数据
	 * @return
	 * @date 2021-08-17
	 * @author krypton lqbnet@yeah.net
	 */
	int countSysModuleByCode(String code, Integer id);

	/**
	 * 保存模块信息
	 * @param moduleParam
	 * @param user
	 * @return
	 * @date 2021-08-17
	 * @author krypton lqbnet@yeah.net
	 */
	int saveSysModule(ModuleParam moduleParam, SysUser user);

	/**
	 * 更新模块信息
	 * @param moduleParam
	 * @param user
	 * @return
	 * @date 2021-08-17
	 * @author krypton lqbnet@yeah.net
	 */
	int updateSysModule(ModuleParam moduleParam, SysUser user);

	/**
	 * 删除模块
	 * @param moduleId
	 * @param user
	 * @return
	 * @date 2021-08-17
	 * @author kryton lqbnet@yeah.net
	 */
	int deleteSysModule(Integer moduleId, SysUser user);

	/**
	 * 查询所有的模块
	 * @return
	 */
	List<SysModule> queryAllSysModule();

	/**
	 * 根据id查询模块
	 * @param moduleId
	 * @return
	 */
    SysModule queryModuleById(Integer moduleId);

	/**
	 * 查询所有的数据源
	 * @return
	 */
	List<SysDatasource> queryAllSysDatasource();

	/**
	 * 统计在某个模块下的api code的数量
	 * @param sysModuleId
	 * @param apiCode
	 * @return
	 */
	int countSysAipCodeBySysModuleId(Integer sysModuleId, String apiCode, Integer apiId);

	/**
	 * 保存api
	 * @param apiParam
	 * @return
	 */
	long saveSysApi(ApiParam apiParam, SysUser user);

	/**
	 * 更新api
	 * @param apiParam
	 */
	int updateSysApi(ApiParam apiParam, SysUser user);

	/**
	 * 删除api
	 * @param apiId
	 * @param user
	 * @return
	 */
	int deleteSysApi(Integer apiId, SysUser user);

	/**
	 * 根据moduleId和datasourceId查询api
	 * @param moduleId
	 * @param datasourceId
	 * @return
	 */
	List<SysApiDTO> querySysApiByModuleIdAndDatasourceId(Integer moduleId, Integer datasourceId);

	/**
	 * 根据apiId查询alpha
	 * @param ids
	 * @return
	 */
	List<SysApiAlpha> queryAlphaByApiIds(List<Integer> ids);

	/**
	 * 根据id查询api
	 * @param apiId
	 * @return
	 */
	SysApiDTO querySysApiById(Integer apiId);

	/**
	 * 根据apiId查询alpha
	 * @param apiId
	 * @return
	 */
	List<SysApiAlpha> queryAlphaByApiId(Integer apiId);

	/**
	 * 保存alpha
	 * @param param
	 * @return
	 */
	long saveSysApiAlpha(AlphaParam param);

	/**
	 * 更新alpha
	 * @param param
	 */
	void updateSysApiAlpha(AlphaParam param);

	/**
	 * 删除alpha
	 * @param alphaId
	 */
	void deleteSysApiAlpha(Integer alphaId);

	/**
	 * 统计请求方法的数量
	 * @param sysApiId
	 * @param requestMethod
	 * @param alphaId
	 * @return
	 */
	int countSysApiAlphaRequestMethod(Integer sysApiId, String requestMethod, Integer alphaId);
}
