package online.alphateam.api.server.dao;

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
	@Override
	public Map<String, Object> getDatasource(String id) {
		StringBuffer sql=new StringBuffer();
		sql.append(" select * from sys_datasource where id = ? ");
		return super.getJdbcTemplate().queryForMap(sql.toString(), id);
	}
}
