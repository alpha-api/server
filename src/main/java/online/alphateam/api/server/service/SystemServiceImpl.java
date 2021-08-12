package online.alphateam.api.server.service;

import online.alphateam.api.server.bean.po.SysUser;
import online.alphateam.api.server.exception.ParamException;
import online.alphateam.api.server.exception.UserNotFoundException;
import online.alphateam.api.server.param.LoginParam;
import online.alphateam.api.server.util.JwtUtil;
import online.alphateam.api.server.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.alphateam.api.server.dao.SystemDao;

@Service
public class SystemServiceImpl implements SystemService {
	@Autowired
	private SystemDao systemDao;

	@Override
	public Result<String> login(LoginParam loginParam) {
		Result<String> result=new Result<String>();

		SysUser user = systemDao.queryUserByCode(loginParam.getCode());
		if (user == null) {
			throw new UserNotFoundException("没有找到用户");
		}
		String password = JwtUtil.md5(loginParam.getPassword());
		if (!StringUtils.equals(password, user.getPassword())) {
			throw new ParamException("密码错误");
		}

		String token = "jwt_token";
		result.success("登陆成功", token);
		return result;
	}

}
