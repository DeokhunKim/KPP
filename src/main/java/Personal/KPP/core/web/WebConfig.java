package Personal.KPP.core.web;

import Personal.KPP.core.web.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {


   @Override
   public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(new LoginInterceptor())
               .order(1)
               .addPathPatterns("/**")
               .excludePathPatterns("/", "/login", "/register", "/core/css/**", "/core/js/**",
                       "/chat/jmeter", "/glossary/**", "/app/glossary/css/**", "/app/glossary/js/**",
                       "/api/**");
   }

}
