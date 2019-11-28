package br.com.fsdata.leroy.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import br.com.fsdata.leroy.controllers.DataController;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses={DataController.class})
public class AppConfig extends WebMvcConfigurerAdapter { 

    
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(100000);
        return multipartResolver;
    }
	
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configure){        
        configure.enable();
    }
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
