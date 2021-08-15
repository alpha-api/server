package online.alphateam.api.server.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
/**
 * 企业微信单点登陆
 * www.alphateam.online
 * @Description 
 * @date 2021-08-15 
 * @author Michael liangzongc@gmail.com
 */
@RequestMapping("/wxwork")
@Controller
public class WXWorkController {
	@Value("${wxwork.corpid}")
	private String corpid;//企业微信ID
	@Value("${wxwork.corpsecret}")
	private String corpsecret;//应用secret
	@Value("${wxwork.redirectUri}")
	private String redirectUri;//服务端sso的uri	
	@Value("${wxwork.gettokenUri}")
	private String gettokenUri;	//获取access_token
	@Value("${wxwork.authorizeUri}")
	private String authorizeUri;	
	@Value("${wxwork.getuserinfoUri}")
	private String getuserinfoUri;	
	private RestTemplate restTemplate=new RestTemplate();
	@RequestMapping("/index")
	public String index(HttpServletRequest request,HttpServletResponse response ) throws IOException {	
		Object userCode=request.getSession().getAttribute("userCode");	
		if( userCode == null ) {
			String url=authorizeUri+"?appid="+corpid+"&redirect_uri="+redirectUri+"&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
			response.sendRedirect(url);			
			return null;
		}
		return "/index.html";
	}
	
	@RequestMapping("/sso")
	public String sso(HttpServletRequest request,HttpServletResponse response ) {	
		String code=request.getParameter("code");		
		String accessToken=getAccessToken();		
		String userCode=getUserCode(code, accessToken);
		request.getSession().setAttribute("userCode", userCode);			
		return "/index.html";
	}
	
	private String getAccessToken() {
		String url=gettokenUri+"?corpid="+corpid+"&corpsecret="+corpsecret;
		Map<String,String> result=restTemplate.getForObject(url, HashMap.class);
		String accessToken=result.get("access_token");
		return accessToken;		
	}
	
	private String getUserCode(String code,String accessToken) {
		String url=getuserinfoUri+"?code="+code+"&access_token="+accessToken;
		Map<String,String> result=restTemplate.getForObject(url, HashMap.class);
		String userId=result.get("UserId");		
		return userId;		
	}
}
