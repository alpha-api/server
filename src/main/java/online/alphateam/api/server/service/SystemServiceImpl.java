package online.alphateam.api.server.service;

import online.alphateam.api.server.bean.dto.SysApiDTO;
import online.alphateam.api.server.bean.param.AlphaParam;
import online.alphateam.api.server.bean.param.ApiParam;
import online.alphateam.api.server.bean.param.ModuleParam;
import online.alphateam.api.server.bean.po.SysApiAlpha;
import online.alphateam.api.server.bean.po.SysDatasource;
import online.alphateam.api.server.bean.po.SysModule;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import online.alphateam.api.server.bean.param.LoginParam;
import online.alphateam.api.server.bean.po.SysUser;
import online.alphateam.api.server.dao.SystemDao;
import online.alphateam.api.server.exception.ParamException;
import online.alphateam.api.server.exception.UserNotFoundException;
import online.alphateam.api.server.util.JwtUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SystemServiceImpl implements SystemService {
	@Autowired
	private SystemDao systemDao;

	@Override
	public String login(LoginParam loginParam) {
		SysUser user = systemDao.queryUserByCode(loginParam.getCode());
		if (user == null) {
			throw new UserNotFoundException("没有找到用户");
		}
		String password = JwtUtil.md5(loginParam.getPassword());
		if (!StringUtils.equals(password, user.getPassword())) {
			throw new ParamException("密码错误");
		}
		user.setPassword(null);
		String token = JwtUtil.createToken(user);		
		return token;
	}

	@Override
	public void saveSysModule(ModuleParam moduleParam, SysUser user) {
		int count = systemDao.countSysModuleByCode(moduleParam.getCode(), moduleParam.getId());
		if (count > 0) {
			throw new ParamException("模块编号已经存在【" + moduleParam.getCode() + "】");
		}
		systemDao.saveSysModule(moduleParam, user);
	}

	@Override
	public void updateSysModule(ModuleParam moduleParam, SysUser user) {
		int count = systemDao.countSysModuleByCode(moduleParam.getCode(), moduleParam.getId());
		if (count > 0) {
			throw new ParamException("模块编号已经存在【" + moduleParam.getCode() + "】");
		}
		systemDao.updateSysModule(moduleParam, user);
	}

	@Override
	public void deleteSysModule(Integer moduleId, SysUser user) {
		systemDao.deleteSysModule(moduleId, user);
	}

	@Override
	public SysModule querySysModuleById(Integer moduleId) {
		return systemDao.queryModuleById(moduleId);
	}

	@Override
	public List<SysModule> queryAllSysModule() {
		return systemDao.queryAllSysModule();
	}

	@Override
	public long saveSysApi(ApiParam apiParam, SysUser user) {
		// 判断在该模块下是否已经存在接口编号
		if (checkApiCodeExists(apiParam.getSysModuleId(), apiParam.getCode(), null)) {
			throw new ParamException("此模块下已经存在该编码");
		}
		long id = systemDao.saveSysApi(apiParam, user);
		return id;
	}

	private boolean checkApiCodeExists(Integer sysModuleId, String apiCode, Integer apiId) {
		return systemDao.countSysAipCodeBySysModuleId(sysModuleId, apiCode, apiId) > 0;
	}

	@Override
	public void updateSysApi(ApiParam apiParam, SysUser user) {
		if (checkApiCodeExists(apiParam.getSysModuleId(), apiParam.getCode(), apiParam.getId())) {
			throw new ParamException("此模块下已经存在该编码");
		}
		systemDao.updateSysApi(apiParam, user);
	}

	@Override
	public void deleteSysApi(Integer apiId, SysUser user) {
		systemDao.deleteSysApi(apiId, user);
	}

	@Override
	public SysApiDTO getSysApi(Integer apiId) {
		SysApiDTO api = systemDao.querySysApiById(apiId);
		List<SysApiAlpha> children = systemDao.queryAlphaByApiId(apiId);
		api.setDetails(children);
		return  api;
	}

	@Override
	public List<SysApiDTO> querySysApi(Integer moduleId, Integer datasourceId) {
		List<SysApiDTO> apiList = systemDao.querySysApiByModuleIdAndDatasourceId(moduleId, datasourceId);
		List<Integer> ids = apiList.stream().map(item -> item.getId()).collect(Collectors.toList());
		List<SysApiAlpha> alphas = systemDao.queryAlphaByApiIds(ids);
		Map<Integer, List<SysApiAlpha>> alphaGroup = alphas.stream().collect(Collectors.groupingBy(item -> item.getSysApiId()));
		apiList.forEach(item -> {
			String url = "/api/v1/alpha/" +  item.getModuleCode() + "/" + item.getCode();
			item.setUrl(url);
			Integer id = item.getId();
			List<SysApiAlpha> children = alphaGroup.get(id);
			if (children == null) {
				children = new ArrayList<>();
			}
			List<String> requestMethods = children.stream().map(child -> child.getRequestMethod()).collect(Collectors.toList());
			item.setRequestMethods(requestMethods);
		});
		return apiList;
	}

	@Override
	public List<SysDatasource> queryAllSysDatasource() {
		List<SysDatasource> datasourceList = systemDao.queryAllSysDatasource();
		return datasourceList;
	}

	@Override
	public long saveSysApiAlpha(AlphaParam param) {
		if(checkAlphaMethodExists(param.getSysApiId(), param.getRequestMethod(), null)) {
			throw new ParamException("请求类型已经存在");
		}
		long id = systemDao.saveSysApiAlpha(param);
		return id;
	}

	@Override
	public void updateSysApiAlpha(AlphaParam param) {
		if(checkAlphaMethodExists(param.getSysApiId(), param.getRequestMethod(), param.getId())) {
			throw new ParamException("请求类型已经存在");
		}
		systemDao.updateSysApiAlpha(param);
	}

	@Override
	public void deleteSysApiAlpha(Integer alphaId) {
		systemDao.deleteSysApiAlpha(alphaId);
	}

	private boolean checkAlphaMethodExists(Integer sysApiId, String requestMethod, Integer alphaId) {
		return systemDao.countSysApiAlphaRequestMethod(sysApiId, requestMethod, alphaId) > 0;
	}

}
