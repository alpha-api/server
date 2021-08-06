package online.alphateam.api.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {
	@RequestMapping("/demo")
	public void test(HttpServletRequest request,HttpServletResponse response ) {
		
		System.out.println(9530);
		
		
		
	}
}
