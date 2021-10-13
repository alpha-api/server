package online.alphateam.api.server.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import online.alphateam.api.server.bean.dto.SysApiDTO;
import online.alphateam.api.server.bean.param.AlphaParam;
import online.alphateam.api.server.bean.param.ApiParam;
import online.alphateam.api.server.bean.param.DatasourceParam;
import online.alphateam.api.server.bean.param.ModuleParam;
import online.alphateam.api.server.bean.po.*;
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
		sql.append(" update sys_module set is_delete = 1, update_user_id = ?, update_time = ? where id = ? and is_delete = 0 ");
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

	@Override
	public List<SysDatasource> queryAllSysDatasource() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select id, name, driver_class_name, url, user_name username, remark, is_delete, create_user_id, create_time, update_user_id, update_time ");
		sql.append(" from sys_datasource ");
		sql.append(" where is_delete = 0 ");
		return super.getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper<>(SysDatasource.class));
	}

	@Override
	public int countSysAipCodeBySysModuleId(Integer sysModuleId, String apiCode, Integer apiId) {
		List<Object> params = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) from sys_api where sys_module_id = ? and code = ? ");
		params.add(sysModuleId);
		params.add(apiCode);
		if (apiId != null) {
			sql.append(" and id != ? ");
			params.add(apiId);
		}
		return super.getJdbcTemplate().queryForObject(sql.toString(), Integer.class, params.toArray());
	}

	@Override
	public long saveSysApi(ApiParam apiParam, SysUser user) {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into sys_api (type, sys_module_id, sys_datasource_id, is_delete, create_user_id, create_time) ");
		sql.append(" values (?, ?, ?, ?, ?, ?) ");
		List<Object> params = new ArrayList<>();
		params.add(apiParam.getType());
		params.add(apiParam.getSysModuleId());
		params.add(apiParam.getSysDatasourceId());
		params.add(0);
		params.add(user.getId());
		params.add(new Date());
		return super.saveAndReturnKey(sql.toString(), params);
	}

	@Override
	public int updateSysApi(ApiParam apiParam, SysUser user) {
		StringBuffer sql = new StringBuffer();
		sql.append(" update sys_api set code = ?, name = ?, remark = ?, update_user_id = ?, update_time = ? where id = ? ");
		return super.getJdbcTemplate().update(sql.toString(), apiParam.getCode(), apiParam.getName(), apiParam.getRemark(), user.getId(), new Date(), apiParam.getId());
	}

	@Override
	public int deleteSysApi(Integer apiId, SysUser user) {
		StringBuffer sql = new StringBuffer();
		sql.append(" update sys_api set is_delete = ?, update_user_id = ?, update_time = ? where id = ? ");
		return super.getJdbcTemplate().update(sql.toString(), 1, user.getId(), new Date(), apiId);
	}

	@Override
	public List<SysApiDTO> querySysApiByModuleIdAndDatasourceId(Integer moduleId, Integer datasourceId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select api.id, api.code, api.name, api.remark, api.type, api.sys_module_id sysModuleId, api.sys_datasource_id sysDatasourceId, module.name moduleName, module.code moduleCode ");
		sql.append(" from sys_api api ");
		sql.append(" left join sys_module module on module.id = api.sys_module_id ");
		sql.append(" where api.is_delete = 0 and api.sys_module_id = ? and api.sys_datasource_id = ? ");
		return super.getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper<>(SysApiDTO.class), moduleId, datasourceId);
	}

	@Override
	public List<SysApiAlpha> queryAlphaByApiIds(List<Integer> ids) {
		if (ids == null || ids.size() == 0) {
			return new ArrayList<>();
		}
		StringBuffer sql = new StringBuffer();
		sql.append(" select id, request_method, `sql`, remark, is_use isUse, sys_api_id sysApiId ");
		sql.append(" from sys_api_alpha ");
		sql.append(" where is_delete = 0 ");
		sql.append(" and sys_api_id in ( ");
		sql.append(ids.stream().map(item -> "?").collect(Collectors.joining(",")));
		sql.append(" ) ");
		return super.getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper<>(SysApiAlpha.class), ids.toArray());
	}

	@Override
	public SysApiDTO querySysApiById(Integer apiId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select api.id, api.code, api.name, api.remark, api.type, api.sys_module_id sysModuleId, api.sys_datasource_id sysDatasourceId, ");
		sql.append(" module.name moduleName, module.code moduleCode, datasource.name datasourceName ");
		sql.append(" from sys_api api ");
		sql.append(" left join sys_module module on module.id = api.sys_module_id ");
		sql.append(" left join sys_datasource datasource on datasource.id = api.sys_datasource_id ");
		sql.append(" where api.is_delete = 0 and api.id = ? ");
		return super.getJdbcTemplate().queryForObject(sql.toString(), new BeanPropertyRowMapper<>(SysApiDTO.class), apiId);
	}

	@Override
	public List<SysApiAlpha> queryAlphaByApiId(Integer apiId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select id, request_method, `sql`, remark, is_use isUse, sys_api_id sysApiId ");
		sql.append(" from sys_api_alpha ");
		sql.append(" where is_delete = 0 ");
		sql.append(" and sys_api_id = ? ");
		return super.getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper<>(SysApiAlpha.class), apiId);
	}

	@Override
	public long saveSysApiAlpha(AlphaParam param) {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into sys_api_alpha (request_method, `sql`, sys_api_id, is_use, is_delete) ");
		sql.append(" values (?, ?, ?, ?, ?) ");
		List<Object> params = new ArrayList<>();
		params.add(param.getRequestMethod());
		params.add(param.getSql());
		params.add(param.getSysApiId());
		params.add(param.getIsUse());
		params.add(0);
		return super.saveAndReturnKey(sql.toString(), params);
	}

	@Override
	public void updateSysApiAlpha(AlphaParam param) {
		StringBuffer sql = new StringBuffer();
		sql.append(" update sys_api_alpha set `sql` = ?, is_use = ? where id = ? ");
		super.getJdbcTemplate().update(sql.toString(), param.getSql(), param.getIsUse(), param.getId());
	}

	@Override
	public void deleteSysApiAlpha(Integer alphaId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" update sys_api_alpha set is_delete = 1 where id = ? ");
		super.getJdbcTemplate().update(sql.toString(), alphaId);
	}

	@Override
	public int countSysApiAlphaRequestMethod(Integer sysApiId, String requestMethod, Integer alphaId) {
		List<Object> params = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) from sys_api_alpha  ");
		sql.append(" where is_delete = 0 ");
		sql.append(" and sys_api_id = ? ");
		sql.append(" and request_method = ? ");
		params.add(sysApiId);
		params.add(requestMethod);
		if (alphaId != null) {
			sql.append(" and id != ?; ");
			params.add(alphaId);
		}
		return super.getJdbcTemplate().queryForObject(sql.toString(), Integer.class, params.toArray());
	}

	@Override
	public long saveSysDatasource(DatasourceParam param, SysUser user) {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into sys_datasource (name, driver_class_name, url, user_name, password, remark, create_user_id, create_time, is_delete) ");
		sql.append(" values (?, ?, ?, ?, ?, ?, ?, ?, ?) ");
		List<Object> params = new ArrayList<>();
		params.add(param.getName());
		params.add(param.getDriverClassName());
		params.add(param.getUrl());
		params.add(param.getUsername());
		params.add(param.getPassword());
		params.add(param.getRemark());
		params.add(user.getId());
		params.add(new Date());
		params.add(0);
		return super.saveAndReturnKey(sql.toString(), params);
	}

	@Override
	public int deleteDatasource(Integer datasourceId, SysUser user) {
		StringBuffer sql = new StringBuffer();
		sql.append(" update sys_datasource set is_delete = 1, update_user_id = ?, update_time = ? where id = ? ");
		return super.getJdbcTemplate().update(sql.toString(), user.getId(), new Date(), datasourceId);
	}

	@Override
	public int updateDatasource(DatasourceParam param, SysUser user) {
		StringBuffer sql = new StringBuffer();
		sql.append(" update sys_datasource set name = ?, driver_class_name = ?, url = ?, user_name = ?, remark = ?, update_user_id = ?, update_time = ? where id = ? ");
		return super.getJdbcTemplate().update(sql.toString(), param.getName(), param.getDriverClassName(), param.getUrl(),
				param.getUsername(), param.getRemark(), user.getId(), new Date(), param.getId());
	}
}
