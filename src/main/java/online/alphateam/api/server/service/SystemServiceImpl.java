package online.alphateam.api.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import online.alphateam.api.server.dao.ApiDao;
import online.alphateam.api.server.dao.SystemDaoImpl;
import online.alphateam.api.server.util.DaoUtil;

@Service
public class SystemServiceImpl {
	@Autowired
    private ApplicationContext applicationContext;
	@Autowired
	private DaoUtil daoUtil;
	
	public void test() {
		
		ApiDao apiDao=daoUtil.get("1");
		
		System.out.println(apiDao.list(" select * from cinema_file "));
		
		
	}
}
