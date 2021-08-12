package online.alphateam.api.server.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;


/**
 * 解析AlphaSql的工具类
 * www.alphateam.online
 * @Description 
 * @date 2021-08-09 
 * @author Michael liangzongc@gmail.com
 */
public class SqlParser {
	public static final String REGEX ="#\\{%?[A-Za-z.]+%?\\}";
	private String sql;
	private List<Object> params;	
	public String getSql() {
		return sql;
	}	
	public void setSql(String sql) {
		this.sql = sql;
	}
	public List<Object> getParams() {
		return params;
	}
	public void setParams(List<Object> params) {
		this.params = params;
	}
	public void parse(String alphaSql,HttpServletRequest request) {				
		Pattern p=Pattern.compile(REGEX);		
		Matcher m = p.matcher(alphaSql);	
		List<Object> params=new ArrayList<Object>();
		String group="";
		String[] array;
		StringBuffer ps=null;
		String key;
		while( m.find() ){	
			group=m.group();			
			if( group.contains("...") ){
				ps=new StringBuffer();
				key=group.substring(2, group.length()-4).trim();				
				array=XUtil.arrayFromString( request.getParameter(key) );			
				for(int i=0;i<array.length;i++){
					ps.append("?,");					
					params.add(array[i]);
				}				
				alphaSql=alphaSql.replaceFirst(REGEX, ps.substring(0, ps.length()-1));				
			}else if( group.contains("%")){
				key=group.substring(3, group.length()-2).trim();
				alphaSql=alphaSql.replaceFirst(REGEX, "?");
				params.add("%"+request.getParameter(key)+"%");
				
			}else{		
				key=group.substring(2, group.length()-1).trim();
				alphaSql=alphaSql.replaceFirst(REGEX, "?");
				params.add(request.getParameter(key));
			}		
		}		
		this.setSql(alphaSql.toString());
		this.setParams(params);		
	}
}
