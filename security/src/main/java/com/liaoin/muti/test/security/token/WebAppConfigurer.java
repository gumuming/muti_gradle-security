package com.liaoin.muti.test.security.token;


import com.liaoin.muti.test.security.properties.RegistryProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.MappedInterceptor;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

/**
 * @author mc
 * Create date 2019/3/4 15:53
 * Version 1.0
 * Description
 */
@Configuration
public class WebAppConfigurer extends WebMvcConfigurationSupport {
    @Resource
    private TokenMethodArgumentResolver tokenMethodArgumentResolver;
	@Resource
	private TokenInterceptor tokenInterceptor;
    @Resource
	private RegistryProperties registryProperties;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(tokenMethodArgumentResolver);
    }

//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		InterceptorRegistration interceptorRegistration = registry.addInterceptor(tokenInterceptor);
//		String[] noVerify = registryProperties.getNoVerify();
//		if (noVerify != null && noVerify.length != 0){
//			interceptorRegistration.excludePathPatterns(Arrays.asList(noVerify));
//		}
//	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry){
		try {
			File directory = new File("");
			String path = directory.getCanonicalPath();
			registry.addResourceHandler("/upload/**").addResourceLocations("file:///"+path+"/upload/");
			registry.addResourceHandler("/static/**").addResourceLocations("file:///"+path+"/static/");
		}catch (Exception e){
		}
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "OPTIONS", "PUT")
				.allowedHeaders("Surpassm","Login","Authorization", "Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method","Access-Control-Request-Headers")
				.exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
				.allowCredentials(true).maxAge(36000);
	}

	@Bean
	public MappedInterceptor getTokenInterceptor() {
		return new MappedInterceptor(new String[] { "/**" },registryProperties.getNoVerify(), tokenInterceptor);
	}

}
