package online.alphateam.api.server.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import online.alphateam.api.server.bean.param.ModuleParam;
import online.alphateam.api.server.bean.po.SysApi;
import online.alphateam.api.server.bean.po.SysApiAlpha;
import online.alphateam.api.server.bean.po.SysModule;
import online.alphateam.api.server.bean.po.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
		sql.append(" select * from sys_datasource where id = ? and is_delete = ? ");
		return super.getJdbcTemplate().queryForMap(sql.toString(), id,0);
	}

	@Override
	public SysUser queryUserByCode(String code) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select id, code, name, password, type from sys_user where is_delete = 0 and code = ? ");
		return super.getJdbcTemplate().queryForObject(sql.toString(), (rs, index) -> {
			SysUser user = new SysUser();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setCode(rs.getString("code"));
			user.setPassword(rs.getString("password"));
			user.setType(rs.getInt("type"));
			return user;
		}, code);
	}
	@Override
	public SysModule getSysModule(String code) {
		StringBuffer sql=new StringBuffer();
		sql.append(" select * from sys_module where code = ? and is_delete = ? ");
		return super.getJdbcTemplate().queryForObject(sql.toString(), (rs, index) -> {
			SysModule module = new SysModule();
			module.setId(rs.getInt("id"));			
			module.setCode(rs.getString("code"));
			module.setName(rs.getString("name"));
			module.setRemark(rs.getString("remark"));
			module.setIsUse(rs.getInt("is_use"));			
			module.setCreateUserId( rs.getInt("create_user_id") );
			module.setCreateTime(rs.getTimestamp("create_time"));  
			module.setUpdateUserId( rs.getInt("update_user_id") );
			module.setUpdateTime(rs.getTimestamp("update_time"));  
			return module;
		}, code,0);
	}
	@Override
	public SysApi getSysApi(Integer moduleId,String apiCode) {
		StringBuffer sql=new StringBuffer();
		sql.append(" select * from sys_api where sys_module_id = ? and code = ? and is_delete = ? ");
		return super.getJdbcTemplate().queryForObject(sql.toString(), (rs, index) -> {
			SysApi api = new SysApi();
			api.setId(rs.getInt("id"));			
			api.setCode(rs.getString("code"));
			api.setName(rs.getString("name"));
			api.setType(rs.getInt("type"));
			api.setRemark(rs.getString("remark"));			
			api.setSysModuleId(rs.getInt("sys_module_id"));
			api.setSysDatasourceId(rs.getInt("sys_datasource_id"));				
			api.setCreateUserId( rs.getInt("create_user_id") );
			api.setCreateTime(rs.getTimestamp("create_time"));  
			api.setUpdateUserId( rs.getInt("update_user_id") );
			api.setUpdateTime(rs.getTimestamp("update_time"));  
			api.setIsDelete(rs.getInt("is_delete"));
			return api;
		},moduleId, apiCode,0);
	}
	@Override
	public SysApiAlpha getSysApiAlpha(Integer apiId, String requestMethod) {
		StringBuffer sql=new StringBuffer();
		sql.append(" select * from sys_api_alpha where sys_api_id = ? and request_method = ? and is_delete = ? ");
		return super.getJdbcTemplate().queryForObject(sql.toString(), (rs, index) -> {
			SysApiAlpha alpha = new SysApiAlpha();
			alpha.setId(rs.getInt("id"));			
			alpha.setRequestMethod(rs.getString("request_method"));
			alpha.setSql(rs.getString("sql"));
			alpha.setSysApiId(rs.getInt("sys_api_id"));
			alpha.setIsDelete(rs.getInt("is_delete"));
			return alpha;
		},apiId, requestMethod,0);
	}
	@Override
	public List<SysApiAlpha> listSysApiAlpha(Integer apiId) {
		return null;		
	}

	@Override
	public int countSysModuleByCode(String code, Integer id) {
		List<Object> params = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) from sys_module where code = ? and is_delete = 0 ");
		params.add(code);
		if (id != null) {
			sql.append(" and id != ? ");
			params.add(id);
		}
		int count = super.getJdbcTemplate().queryForObject(sql.toString(), Integer.class, params.toArray());
		return count;
	}

	@Override
	public int saveSysModule(ModuleParam moduleParam, SysUser user) {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into sys_module (name, code, remark, is_use, is_delete, create_user_id, create_time) values (?, ?, ?, ?, ?, ?, ?) ");
		return super.getJdbcTemplate().update(sql.toString(), moduleParam.getName(), moduleParam.getCode(),
				moduleParam.getRemark(), moduleParam.getIsUse(), 0, user.getId(), new Date());
	}

	@Override
	public int updateSysModule(ModuleParam moduleParam, SysUser user) {
		StringBuffer sql = new StringBuffer();
		sql.append(" update sys_module set name = ?, code = ?, remark = ?, is_use = ?, update_user_id = ?, update_time = ? where id = ? ");
		return super.getJdbcTemplate().update(sql.toString(), moduleParam.getName(), moduleParam.getCode(), moduleParam.getRemark(),
				moduleParam.getIsUse(), user.getId(), new Date(), moduleParam.getId());
	}

	@Override
	public int deleteSysModule(Integer moduleId, SysUser user) {
		StringBuffer sql = new StringBuffer();
		sql.append(" update sys_module set is_delete = 1, update_user_id, update_time where id = ? and is_delete = 0 ");
		return super.getJdbcTemplate().update(sql.toString(), user.getId(), new Date(), moduleId);
	}

	@Override
	public List<SysModule> queryAllSysModule() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select id, code, name, remark, is_use, is_delete, create_user_id, create_time, update_user_id, update_time from sys_module where is_delete = 0 ");
		return super.getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper<>(SysModule.class));
	}

	@Override
	public SysModule queryModuleById(Integer moduleId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select id, code, name, remark, is_use, is_delete, create_user_id, create_time, update_user_id, update_time from sys_module where id = ? and is_delete = 0 ");
		return super.getJdbcTemplate().queryForObject(sql.toString(), new BeanPropertyRowMapper<>(SysModule.class), moduleId);
	}
}
