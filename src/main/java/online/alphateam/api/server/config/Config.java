package online.alphateam.api.server.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import online.alphateam.api.server.config.resolver.CurrentUserMethodArgumentResolver;
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
    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new CurrentUserMethodArgumentResolver());
        super.addArgumentResolvers(argumentResolvers);
    }

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/WW_verify_oIBCTwHZsUh60Qfn.txt");
	}
}
