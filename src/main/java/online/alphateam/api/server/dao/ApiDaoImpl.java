package online.alphateam.api.server.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class ApiDaoImpl extends DaoFactory {
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		initFactory(jdbcTemplate);		
	}	
	@Override
	void initFactory(JdbcTemplate jdbcTemplate) {
		super.setJdbcTemplate(jdbcTemplate);	
	}
	
	public void test() {		
		JdbcTemplate jdbcTemplate=getJdbcTemplate();		
		String sql=" select * from user ";
		System.out.println( jdbcTemplate.queryForList(sql)  );		
	}
	
}
