package online.alphateam.api.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.alphateam.api.server.dao.SystemDao;

@Service
public class SystemServiceImpl implements SystemService {
	@Autowired
	private SystemDao systemDao;

	@Override
	public String login(String userCode, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
