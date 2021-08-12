package online.alphateam.api.server.dao;

import java.util.List;
import java.util.Map;
/**
 * 系统模块的dao
 * www.alphateam.online
 * @Description 
 * @date 2021-08-08 
 * @author Michael liangzongc@gmail.com
 */
public interface SystemDao {
	/**
	 * 根据ID获取数据源配置信息
	 * @param id
	 * @return
	 * @date 2021-08-08 
	 * @author Michael liangzongc@gmail.com
	 */
	public Map<String,Object> getDatasource(String id);
}
