package online.alphateam.api.server.config.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import online.alphateam.api.server.annotation.CurrentUser;
import online.alphateam.api.server.bean.bo.JwtBO;
import online.alphateam.api.server.bean.po.JwtPayload;
import online.alphateam.api.server.bean.po.SysUser;
import online.alphateam.api.server.util.JwtUtil;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().isAssignableFrom(SysUser.class)
                && methodParameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        String token = nativeWebRequest.getHeader("token");
        JwtBO bo = JwtUtil.parseToken(token);
        JwtPayload payload = bo.getPayload();
        String sub = payload.getSub();
        SysUser user = new ObjectMapper().readValue(sub, SysUser.class);
        return user;
    }
}
