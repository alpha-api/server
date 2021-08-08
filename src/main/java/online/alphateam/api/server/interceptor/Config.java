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
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {		
		registry.addInterceptor(apiInterceptor).addPathPatterns("/api/**");
		super.addInterceptors(registry);
	}

}
