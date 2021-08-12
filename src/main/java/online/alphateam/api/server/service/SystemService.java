package online.alphateam.api.server.service;

import online.alphateam.api.server.param.LoginParam;
import online.alphateam.api.server.util.Result;

/**
 * 
 * www.alphateam.online
 * @Description 
 * @date 2021-08-08 
 * @author 梁文正 liangzongc@gmail.com
 */
public interface SystemService {
	Result<String> login(LoginParam loginParam);
}
