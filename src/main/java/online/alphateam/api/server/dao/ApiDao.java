package online.alphateam.api.server.dao;

import java.util.List;
import java.util.Map;

import online.alphateam.api.server.util.Pager;

public interface ApiDao {
	public List<Map<String,Object>> list(String sql,Object... args);	
	public Pager<Map<String,Object>> pager(String sql,Integer pageNo,Integer pageSize,Object... args);
}
