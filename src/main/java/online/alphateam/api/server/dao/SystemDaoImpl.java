package online.alphateam.api.server.dao;

import java.util.Map;

import online.alphateam.api.server.bean.po.SysUser;
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

	@Override
	public SysUser queryUserByCode(String code) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select id, code, name, password, type from sys_user where is_delete = 0 and code = ? ");
		try {
			return super.getJdbcTemplate().queryForObject(sql.toString(), (rs, index) -> {
				SysUser user = new SysUser();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setCode(rs.getString("code"));
				user.setPassword(rs.getString("password"));
				user.setType(rs.getInt("type"));
				return user;
			}, code);
		} catch (Exception e) {
			return null;
		}
	}
}
