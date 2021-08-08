package online.alphateam.api.server.util;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import online.alphateam.api.server.dao.ApiDaoImpl;
import online.alphateam.api.server.dao.SystemDao;
import online.alphateam.api.server.exception.ApiException;
/**
 * 
 * www.alphateam.online
 * @Description 
 * @date 2021-08-08 
 * @author 梁文正 liangzongc@gmail.com
 */
@Component
public class DaoUtil {
	private Map<String,ApiDaoImpl> daoContext=new HashMap<String, ApiDaoImpl>();
	@Autowired
	private SystemDao systemDao;
	/**
	 * 根据数据源ID获取dao实例
	 * @param datasourceId
	 * @return
	 * @date 2021-08-08 
	 * @author 梁文正 liangzongc@gmail.com
	 */
	public ApiDaoImpl get(String datasourceId) {		
		ApiDaoImpl dao=daoContext.get(datasourceId);
		if( dao == null ) {			
			dao=create(datasourceId);
		}		
		return dao;		
	}
	
	private ApiDaoImpl create(String datasourceId) {				
		Map<String,Object> bean=null;
		try {
			bean=systemDao.getDatasource(datasourceId);
		} catch (Exception e) {
			throw new ApiException("无数据源配置");	
		}		
		String driver=bean.get("driver_class_name").toString();			
		String url=bean.get("url").toString();				
		String name=bean.get("user_name").toString();	
		String password=bean.get("password").toString();		
		HikariConfig config=new HikariConfig();
		config.setDriverClassName(driver);
		config.setUsername(name);
		config.setPassword(password);
		config.setJdbcUrl(url);		
	    DataSource ds=new HikariDataSource(config);
		JdbcTemplate temp=new JdbcTemplate(ds);		
		ApiDaoImpl apiDao=new ApiDaoImpl();		
		apiDao.setJdbcTemplate(temp);	
		daoContext.put(datasourceId, apiDao);
		return apiDao;	
	}
}
