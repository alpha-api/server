package online.alphateam.api.server.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class SystemDaoImpl extends DaoFactory {
	@Autowired
	@Override
	void initFactory(JdbcTemplate jdbcTemplate) {
		super.setJdbcTemplate(jdbcTemplate);	
	}
	
	
	public void test() {
		
		JdbcTemplate jdbcTemplate=getJdbcTemplate();		
		String sql=" select * from sys_user order by id ";
		System.out.println( queryForPager(sql, 1, 10)  );		
	}
}
