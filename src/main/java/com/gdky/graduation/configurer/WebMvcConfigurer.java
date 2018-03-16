package com.gdky.graduation.configurer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Administrator on 2018/2/28 0028.
 */

@EnableWebMvc
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {
    private final Logger logger = LoggerFactory.getLogger(WebMvcConfigurer.class);

    //解决跨域问题
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD")
                .allowedHeaders("*")
                .allowedOrigins("*");
    }

    //统一异常处理
//    @Override
//    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
//        exceptionResolvers.add(new HandlerExceptionResolver() {
//            @Override
//            public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
//                Result result = new Result();
//                if (e instanceof ServiceException) {//业务失败的异常，如“账号或密码错误”
//                    result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
//                    logger.info(e.getMessage());
//                } else if (e instanceof NoHandlerFoundException) {
//                    result.setCode(ResultCode.NOT_FOUND).setMessage("接口 [" + request.getRequestURI() + "] 不存在");
//                } else if (e instanceof ServletException) {
//                    result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
//                } else {
//                    result.setCode(ResultCode.INTERNAL_SERVER_ERROR).setMessage("接口 [" + request.getRequestURI() + "] 内部错误，请联系管理员");
//                    String message;
//                    if (handler instanceof HandlerMethod) {
//                        HandlerMethod handlerMethod = (HandlerMethod) handler;
//                        message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
//                                request.getRequestURI(),
//                                handlerMethod.getBean().getClass().getName(),
//                                handlerMethod.getMethod().getName(),
//                                e.getMessage());
//                    } else {
//                        message = e.getMessage();
//                    }
//                    logger.error(message, e);
//                }
//                responseResult(response, result);
//                return new ModelAndView();
//            }
//
//        });
}
