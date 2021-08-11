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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import online.alphateam.api.server.bean.bo.JwtBO;
import online.alphateam.api.server.bean.po.JwtHeader;
import online.alphateam.api.server.bean.po.JwtPayload;
import online.alphateam.api.server.util.JwtUtil;
import online.alphateam.api.server.util.SqlParser;



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
    	
    	System.out.println( JwtUtil.authentication(token, secret) );
    	long t3=System.currentTimeMillis();
    	System.out.println(t3-t1+"ms");
    	
    }
    
    @Test
    public void token02() {
    	JwtHeader header=new JwtHeader();
    	header.setTyp("JWT");
    	header.setAlg("MD5");
    	
    	//1.{"typ":"JWT","alg":"MD5"}
    	//2.{"typ":"JWT","alg":"MD5"}
    	
    	//1.{"sub":"user9527","iss":"www.alphateam.online","exp":"1630995260962","iat":"1628403260962"}
    	//2.{"iss":"www.alphateam.online","sub":"user9527","iat":1628403348815,"exp":1630995348815}
    	
    	
    	JwtPayload payload=new JwtPayload();
    	payload.setIss("www.alphateam.online");
    	payload.setSub("user9527");
    	Long iat=System.currentTimeMillis();
    	Long exp=iat+30*24*60*60*1000l; 
    	payload.setIat(iat);
    	payload.setExp(exp);
    	String secret="PC2021";
    	
    	String token=JwtUtil.createToken(header, payload, secret);
    	
    	System.out.println(token);
    	//eyJ0eXAiOiJKV1QiLCJhbGciOiJNRDUifQ.eyJpc3MiOiJ3d3cuYWxwaGF0ZWFtLm9ubGluZSIsInN1YiI6InVzZXI5NTI3IiwiaWF0IjoxMDAxLCJleHAiOjEwMDJ9.mWXiRqcYXyzpWKE_TOZing
    	//eyJ0eXAiOiJKV1QiLCJhbGciOiJNRDUifQ.eyJpc3MiOiJ3d3cuYWxwaGF0ZWFtLm9ubGluZSIsInN1YiI6InVzZXI5NTI3IiwiaWF0IjoxMDAxLCJleHAiOjEwMDJ9.mWXiRqcYXyzpWKE_TOZing
    	
    	JwtBO bo=JwtUtil.parseToken(token);
    	System.out.println(bo);
    	
    	System.out.println( JwtUtil.authentication(token, secret) );
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
    
    @Test
    public void objectMapper() {    	
    	JwtHeader header=new JwtHeader();
    	header.setTyp("JWT");
    	header.setAlg("MD5");
    	
    	JwtPayload payload=new JwtPayload();
    	
    	payload.setSub("9527");
    	
    	
    	JwtBO bo=new JwtBO();    	
    	bo.setHeader(header);
    	bo.setPayload(payload);    	
    	
    	ObjectMapper mapp=new ObjectMapper();
    	
    	try {
			System.out.println( mapp.writeValueAsString(bo) );
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    	
    }
    @Test
    public void sqlParser() {
    	SqlParser parser=new SqlParser();
    	String alphaSql=" select * from User where name like #{%name%} or age=#{age} or class in (#{myClass...}) ";
    	
    	
    	parser.parse(alphaSql, null);
    	System.out.println(parser);    	
    }
    
    @Test
    public void root() {
    	
    	System.out.println(JwtUtil.md5("888888"));    	
    }

}
