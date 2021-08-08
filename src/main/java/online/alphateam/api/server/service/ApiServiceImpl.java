package online.alphateam.api.server.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.alphateam.api.server.dao.ApiDao;
import online.alphateam.api.server.util.DaoUtil;
@Service
public class ApiServiceImpl implements ApiService {
	@Autowired
	private DaoUtil daoUtil;	
	@Override
	public List<Map<String, Object>> list(HttpServletRequest request, String module, String api) {
		
		
		ApiDao dao=daoUtil.get("1");
		
		
		
		
		return dao.list(" select * from cinema_file ");
	}
	
}
