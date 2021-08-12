package online.alphateam.api.server.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import online.alphateam.api.server.util.JwtUtil;
/**
 * 
 * www.alphateam.online
 * @Description API拦截器
 * @date 2021-08-07 
 * @author Michael liangzongc@gmail.com
 */
@Component
public class ApiInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("进入API拦截器。。。");
		String token=request.getHeader("token");	
		
		
		Cookie cookie=new Cookie("token", token);
		cookie.setMaxAge(14*24*60*60);		
		response.addCookie(cookie);
		
		if( !authentication(token) ) {			
			response.sendError(response.SC_UNAUTHORIZED);			
			return false;
		}
		
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("进入API拦截器。。。after");
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	/**
	 * 
	 * @param token
	 * @return 校验请求是否合法
	 * @date 2021-08-07 
	 * @author Michael liangzongc@gmail.com
	 */
	private boolean authentication(String token) {		
		if( JwtUtil.authentication(token, "PC2021") ) {//如果token合法则返回true
			return true;			
		}		
		return false;		
	}

}
