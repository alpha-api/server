package online.alphateam.api.server.service;

import online.alphateam.api.server.bean.dto.SysApiDTO;
import online.alphateam.api.server.bean.param.ApiParam;
import online.alphateam.api.server.bean.param.ModuleParam;
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

import java.util.List;

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
	public void saveSysApi(ApiParam apiParam) {

	}

	@Override
	public void updateSysApi(ApiParam apiParam) {

	}

	@Override
	public void deleteSysApi(Integer apiId) {

	}

	@Override
	public void deleteSysApiChild(Integer type, Integer childId) {

	}

	@Override
	public SysApiDTO<?> getSysApi(Integer type, Integer detailId) {
		return null;
	}

	@Override
	public List<SysApiDTO<?>> querySysApi(Integer moduleId, Integer datasourceId) {
		return null;
	}

}
