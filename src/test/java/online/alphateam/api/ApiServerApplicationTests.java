package online.alphateam.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import online.alphateam.api.server.util.JwtUtil;



@SpringBootTest
class ApiServerApplicationTests {

    @Test
    void token() {    	
    	Map<String,String> header=new HashMap<String,String>();
    	header.put("typ", "JWT");
    	header.put("alg", "MD5");
    	
    	Map<String,String> payload=new HashMap<String,String>();
    	Long iat=System.currentTimeMillis();
    	Long exp=iat+30*24*60*60*1000l;    	
    	payload.put("iss", "www.alphateam.online");
    	payload.put("sub", "user9527");
    	payload.put("iat", iat.toString());
    	payload.put("exp", exp.toString());
    	
    	String secret="PC2021";
    	
    	long t1=System.currentTimeMillis();
    	
    	String token=JwtUtil.createToken(new JSONObject(header).toString(),
    			new JSONObject(payload).toString(), 
    			secret);    	
    	System.out.println(token);
    	long t2=System.currentTimeMillis();
    	System.out.println(t2-t1+"ms");
    	
    	System.out.println( JwtUtil.authentication("13212313", secret) );
    	long t3=System.currentTimeMillis();
    	System.out.println(t3-t1+"ms");
    	
    }
    
    @Test
	public void sql01() throws JSONException{
		long t1=System.currentTimeMillis();	
		String sql=" select * from User where name like #{%name%} or age=#{age} or class in (#{myClass...}) ";
		
		String name="张三";
		String age="26";
		String myClass="[7,8,9]";		
		Map<String,String> request=new HashMap<String, String>();
		request.put("name", name);
		request.put("age", age);
		request.put("myClass", myClass);
		
		
		String regex="#\\{%?[A-Za-z.]+%?\\}";
		
		Pattern p=Pattern.compile(regex);		
		Matcher m = p.matcher(sql);	
		List<String> params=new ArrayList<String>();
		String group="";
		JSONArray array;
		StringBuffer ps=null;
		String key;
		while( m.find() ){	
			group=m.group();			
			if( group.contains("...") ){
				ps=new StringBuffer();
				key=group.substring(2, group.length()-4).trim();			
				array=new JSONArray(request.get(key));			
				for(int i=0;i<array.length();i++){
					ps.append("?,");					
					params.add(array.getString(i));
				}				
				sql=sql.replaceFirst(regex, ps.substring(0, ps.length()-1));				
			}else if( group.contains("%")){
				key=group.substring(3, group.length()-2).trim();
				sql=sql.replaceFirst(regex, "?");
				params.add("%"+request.get(key)+"%");
				
			}else{		
				key=group.substring(2, group.length()-1).trim();
				sql=sql.replaceFirst(regex, "?");
				params.add(request.get(key));
			}		
		}
		System.out.println(System.currentTimeMillis()-t1+"ms");
		System.out.println(sql);
		System.out.println(params);		
	}

}
