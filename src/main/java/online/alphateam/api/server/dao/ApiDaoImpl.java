package online.alphateam.api.server.dao;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import online.alphateam.api.server.util.Pager;
@Repository
public class ApiDaoImpl extends DaoFactory implements ApiDao {
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		initFactory(jdbcTemplate);		
	}	
	@Override
	void initFactory(JdbcTemplate jdbcTemplate) {
		super.setJdbcTemplate(jdbcTemplate);	
	}
	@Override
	public List<Map<String, Object>> list(String sql, Object... args) {
		JdbcTemplate jdbcTemplate=getJdbcTemplate();		
		if( args == null || args.length == 0 ) {
			return jdbcTemplate.queryForList(sql);			
		}
		return jdbcTemplate.queryForList(sql,args);
	}
	@Override
	public Pager<Map<String, Object>> pager(String sql, Integer pageNo, Integer pageSize, Object... args) {
		if( args == null || args.length == 0 ) {
			return queryForPager(sql,pageNo,pageSize);			
		}
		return queryForPager(sql,pageNo,pageSize,args);		
	}
	
}
