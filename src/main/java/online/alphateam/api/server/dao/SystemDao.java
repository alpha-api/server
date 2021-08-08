package online.alphateam.api.server.dao;

import java.util.List;
import java.util.Map;

public interface SystemDao {
	public List<Map<String,Object>> listDatasource();
	
	public Map<String,Object> getDatasource(String id);
}
