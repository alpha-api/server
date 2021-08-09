package online.alphateam.api.server.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
@Configuration
public class Config extends WebMvcConfigurationSupport {
	@Autowired
	private HandlerInterceptor apiInterceptor;	
	@Autowired
	private HandlerInterceptor systemInterceptor;	
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {		
		registry.addInterceptor(apiInterceptor).addPathPatterns("/api/**");
		registry.addInterceptor(systemInterceptor).addPathPatterns("/sys/**").excludePathPatterns("/sys/login");
		super.addInterceptors(registry);
	}

}
