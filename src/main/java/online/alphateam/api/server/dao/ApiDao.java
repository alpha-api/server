package online.alphateam.api.server.dao;

import java.util.List;
import java.util.Map;

import online.alphateam.api.server.util.Pager;
/**
 * API接口模块的dao
 * www.alphateam.online
 * @Description 
 * @date 2021-08-08 
 * @author Michael liangzongc@gmail.com
 */
public interface ApiDao {
	/**
	 * 普通查询
	 * @param sql
	 * @param args
	 * @return
	 * @date 2021-08-08 
	 * @author Michael liangzongc@gmail.com
	 */
	public List<Map<String,Object>> list(String sql,Object... args);	
	/**
	 * 分页查询
	 * @param sql
	 * @param pageNo
	 * @param pageSize
	 * @param args
	 * @return
	 * @date 2021-08-08 
	 * @author Michael liangzongc@gmail.com
	 */
	public Pager<Map<String,Object>> pager(String sql,Integer pageNo,Integer pageSize,Object... args);
}
