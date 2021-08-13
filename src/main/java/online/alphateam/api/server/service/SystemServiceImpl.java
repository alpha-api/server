package online.alphateam.api.server.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.alphateam.api.server.bean.param.LoginParam;
import online.alphateam.api.server.bean.po.SysUser;
import online.alphateam.api.server.dao.SystemDao;
import online.alphateam.api.server.exception.ParamException;
import online.alphateam.api.server.exception.UserNotFoundException;
import online.alphateam.api.server.util.JwtUtil;
import online.alphateam.api.server.util.Result;

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

}
