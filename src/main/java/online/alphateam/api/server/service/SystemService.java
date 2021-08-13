package online.alphateam.api.server.service;

import online.alphateam.api.server.bean.param.LoginParam;
import online.alphateam.api.server.util.Result;

/**
 * 
 * www.alphateam.online
 * @Description 
 * @date 2021-08-08 
 * @author Michael liangzongc@gmail.com
 */
public interface SystemService {
	String login(LoginParam loginParam);
}
