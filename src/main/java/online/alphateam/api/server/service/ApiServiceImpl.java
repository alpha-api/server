package online.alphateam.api.server.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import online.alphateam.api.server.bean.bo.AlphaApiBO;
import online.alphateam.api.server.bean.po.SysApi;
import online.alphateam.api.server.bean.po.SysApiAlpha;
import online.alphateam.api.server.bean.po.SysModule;
import online.alphateam.api.server.cache.ApiCache;
import online.alphateam.api.server.cache.CachePool;
import online.alphateam.api.server.dao.ApiDao;
import online.alphateam.api.server.dao.SystemDao;
import online.alphateam.api.server.exception.ApiException;
import online.alphateam.api.server.util.DaoUtil;
import online.alphateam.api.server.util.Pager;
import online.alphateam.api.server.util.SqlParser;
@Service
public class ApiServiceImpl implements ApiService {
	@Autowired
	private DaoUtil daoUtil;
	@Autowired
	private SystemDao systemDao;
	
	
	@Override
	public List<Map<String, Object>> list(HttpServletRequest request, String module, String api) {
		AlphaApiBO bo=getAlphaApiBO(module, api, request.getMethod().toUpperCase());		
		//1，查出API的配置信息，sql/参数的数据类型/数据源ID
		String alphaSql=bo.getSql();
		//2，解析sql/参数
		SqlParser parser=new SqlParser();
		parser.parse(alphaSql, request);
		String sql=parser.getSql();
		List<Object> params=parser.getParams();		
		//3，获取数据源的dao实例		
		ApiDao dao=daoUtil.get(bo.getDatasourceId().toString());		
		return dao.list(sql,params.toArray());
	}
	@Override
	public Pager<Map<String, Object>> pager(HttpServletRequest request, String module, String api,Integer pageNo,Integer pageSize) {
		AlphaApiBO bo=getAlphaApiBO(module, api, request.getMethod().toUpperCase());		
		//1，查出API的配置信息，sql/参数的数据类型/数据源ID
		String alphaSql=bo.getSql();
		//2，解析sql/参数
		SqlParser parser=new SqlParser();
		parser.parse(alphaSql, request);
		String sql=parser.getSql();
		List<Object> params=parser.getParams();		
		//3，获取数据源的dao实例		
		ApiDao dao=daoUtil.get(bo.getDatasourceId().toString());		
		return dao.pager(sql,pageNo,pageSize,params.toArray());
	}
	
	private AlphaApiBO getAlphaApiBO(String module, String api,String requestMethod) {
		String cacheKey=module+"-"+api+"-"+requestMethod;
		AlphaApiBO bo=CachePool.get(cacheKey, AlphaApiBO.class);		
		if( bo != null )return bo;
		try {
			bo=new AlphaApiBO();
			SysModule sysModule=systemDao.getSysModule(module);			
			SysApi sysApi=systemDao.getSysApi(sysModule.getId(), api);		
			SysApiAlpha sysApiAlpha=systemDao.getSysApiAlpha(sysApi.getId(), requestMethod);
			bo.setSysModule(sysModule);
			bo.setSysApi(sysApi);
			bo.setSysApiAlpha(sysApiAlpha);	
			CachePool.put(cacheKey, bo);
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			throw new ApiException("api not found");
		}
		return bo;
	}
}
