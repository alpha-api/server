package online.alphateam.api.server.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import online.alphateam.api.server.dao.ApiDaoImpl;
import online.alphateam.api.server.dao.SystemDaoImpl;



@Controller
@RequestMapping("/test")
public class TestController {

	private static final Logger logger = LoggerFactory.getLogger(TestController.class);

	@Autowired
	private SystemDaoImpl dao;	
	@RequestMapping("/demo")
	public void test(HttpServletRequest request,HttpServletResponse response ) {
		
		System.out.println(9530);

		logger.debug("debug");
		logger.info("info");
		logger.warn("warn");
		logger.error("error");
		
		dao.test();
		
	}
	
	
	@RequestMapping("/ds")
	public void ds(HttpServletRequest request,HttpServletResponse response ) {
		//ApiDaoImpl apiDao=getApiDao();
		
		String myClass="[7,8,9]";
		ObjectMapper mapp=new ObjectMapper();
		try {
			String[] cs=mapp.readValue(myClass, String[].class);
			System.out.println(cs);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private ApiDaoImpl getApiDao() {
		String driver="com.mysql.cj.jdbc.Driver";
		String url="jdbc:mysql://192.168.50.54:3306/cinema?characterEncoding=utf-8&serverTimezone=GMT";
		String name="admin";
		String password="888888";		
		HikariConfig config=new HikariConfig();
		config.setDriverClassName(driver);
		config.setUsername(name);
		config.setPassword(password);
		config.setJdbcUrl(url);		
	    DataSource ds=new HikariDataSource(config);
		JdbcTemplate temp=new JdbcTemplate(ds);		
		ApiDaoImpl apiDao=new ApiDaoImpl();		
		apiDao.setJdbcTemplate(temp);		
		return apiDao;		
	}
}
