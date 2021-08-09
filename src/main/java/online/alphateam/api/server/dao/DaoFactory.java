package online.alphateam.api.server.dao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import online.alphateam.api.server.util.Pager;
/**
 * dao核心类，提供公共方法
 * www.alphateam.online
 * @Description 
 * @date 2021-08-08 
 * @author 梁文正 liangzongc@gmail.com
 */
public abstract class DaoFactory {
	private JdbcTemplate jdbcTemplate;	
	private String dbType;
	protected JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	protected void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;		
	}	
	
	/**
	 * 
	 * @return
	 * @date 2021-08-07 
	 * @author 梁文正 liangzongc@gmail.com
	 */
	private String getDbType() {
		if(dbType == null) {					
			Connection conn=null;
			try {
				conn=jdbcTemplate.getDataSource().getConnection();			
				dbType=conn.getMetaData().getDatabaseProductName();
				conn.close();
			} catch (SQLException e) {			
				throw new NullPointerException("获取数据库类型失败");
			}			
		}		
		return dbType;
	}
	

	/**
	 * 
	 * @param jdbcTemplate
	 * @date 2021-08-07 
	 * @author 梁文正 liangzongc@gmail.com
	 */
	abstract void initFactory(JdbcTemplate jdbcTemplate);
	
	/**
	 * 
	 * @param sql
	 * @param pageNo
	 * @param pageSize
	 * @param args
	 * @return
	 * @date 2021-08-07 
	 * @author 梁文正 liangzongc@gmail.com
	 */
	public Pager<Map<String,Object>> queryForPager(String sql,Integer pageNo,Integer pageSize,Object... args) {
		Pager<Map<String,Object>> pager=null;		
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		Integer startIndex=(pageNo - 1) * pageSize + 1;		
		Integer endIndex=pageNo*pageSize;
		Integer total;	
		
		//1.获取总数量		
		int index=sql.indexOf("from");		
		String totalSql=" select COUNT(*) ALPHATOTAL " + sql.substring(index);				
		Map<String,Object> jdbcTotal=null;
		if(args != null && args.length > 0) {	
			jdbcTotal=jdbcTemplate.queryForMap(totalSql,args);
		}else {			
			jdbcTotal=jdbcTemplate.queryForMap(totalSql);
		}
		total=Integer.parseInt(jdbcTotal.get("ALPHATOTAL").toString());		
		if(startIndex > total) {//起始下标越界，返回一个空集			
			pager=new Pager<Map<String,Object>>(pageNo, pageSize, total, list);
			return pager;		
		}		
		
		//2.获取数据库类型		
		String dbType=getDbType();			
		List<Object> params=new ArrayList<Object>();
		for(int i=0;i<args.length;i++) {
			params.add(args[i]);			
		}
		//根据数据库类型动态拼接分页SQL		
		StringBuffer psql=new StringBuffer();
		if( "MICROSOFT SQL SERVER".equals( dbType.toUpperCase() ) ) {//sqlserver							
			psql.append(" select top ").append(endIndex).append(" basetab.* from ( ");
			psql.append(sql);
			psql.append(" ) basetab ");			
			list=jdbcTemplate.queryForList(psql.toString(),params.toArray());
			return new Pager<Map<String,Object>>().subPager(pageNo, pageSize, total, list);			
		}else if( "ORACLE".equals( dbType.toUpperCase() ) ) {//oracle	
			psql.append(" select * from( ");
				psql.append(" select basetab.*,ROWNUM RN from ");
				psql.append(" ( ").append(sql).append(" ) basetab ");
			psql.append(" ) ");
			psql.append("  where RN between ? and ?  ");	
			params.add(startIndex);
			params.add(endIndex);
		}else if( "MYSQL".equals( dbType.toUpperCase() ) ){			
			psql.append( sql )
			.append(" limit ?,? ");
			params.add( (pageNo-1)*pageSize );
			params.add( pageSize );						
		}else {			
			throw new NullPointerException("未研发"+dbType+"的分页API");			
		}	
		//3.执行分页查询	       
		list=jdbcTemplate.queryForList(psql.toString(),params.toArray());
		return new Pager<Map<String,Object>>(pageNo, pageSize, total, list);		
	}
	
	
	
}

