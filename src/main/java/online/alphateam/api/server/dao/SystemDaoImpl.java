package online.alphateam.api.server.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class SystemDaoImpl extends DaoFactory implements SystemDao {
	@Autowired
	@Override
	void initFactory(JdbcTemplate jdbcTemplate) {
		super.setJdbcTemplate(jdbcTemplate);	
	}
	
	
	public void test() {
		
		JdbcTemplate jdbcTemplate=getJdbcTemplate();		
		String sql=" select * from sys_datasource order by id ";
		
		
		
		System.out.println( queryForPager(sql, 1, 10)  );		
		System.out.println( queryForPager(sql, 1, 10).getPageData()  );	
	}


	@Override
	public List<Map<String, Object>> listDatasource() {
		StringBuffer sql=new StringBuffer();
		sql.append(" select * from sys_datasource order by id ");	
		return super.getJdbcTemplate().queryForList(sql.toString());
	}


	@Override
	public Map<String, Object> getDatasource(String id) {
		StringBuffer sql=new StringBuffer();
		sql.append(" select * from sys_datasource where id = ? ");
		return super.getJdbcTemplate().queryForMap(sql.toString(), id);
	}
}
