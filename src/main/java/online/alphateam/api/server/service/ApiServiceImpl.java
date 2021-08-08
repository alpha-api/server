package online.alphateam.api.server.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.alphateam.api.server.dao.ApiDao;
import online.alphateam.api.server.util.DaoUtil;
import online.alphateam.api.server.util.SqlParser;
@Service
public class ApiServiceImpl implements ApiService {
	@Autowired
	private DaoUtil daoUtil;	
	@Override
	public List<Map<String, Object>> list(HttpServletRequest request, String module, String api) {
		//1，查出API的配置信息，sql/参数的数据类型/数据源ID
		String alphaSql=" select * from cinema_file where cinema_name like #{%name%} ";
		//2，解析sql/参数
		SqlParser parser=new SqlParser();
		parser.parse(alphaSql, request);
		String sql=parser.getSql();
		List<Object> params=parser.getParams();
		
		//3，获取数据源的dao实例		
		ApiDao dao=daoUtil.get("1");		
		return dao.list(parser.getSql(),parser.getParams().toArray());
	}
	
}
