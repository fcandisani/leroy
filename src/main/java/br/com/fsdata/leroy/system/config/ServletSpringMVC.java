package br.com.fsdata.leroy.system.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {
 
    @Override
    protected Class<?>[] getRootConfigClasses() {

        return new Class[]{ AppConfig.class};
    }
 
    @Override
    protected Class<?>[] getServletConfigClasses() {
        
        return new Class[]{ };
    }
 
    @Override
    protected String[] getServletMappings() {
        
        return new String [] {"/"};
    }
}
