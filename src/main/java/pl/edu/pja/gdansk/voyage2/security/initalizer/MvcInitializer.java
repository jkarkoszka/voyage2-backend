package pl.edu.pja.gdansk.voyage2.security.initalizer;

import org.apache.catalina.security.SecurityConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import pl.edu.pja.gdansk.voyage2.Application;
import pl.edu.pja.gdansk.voyage2.security.config.HttpSessionConfig;

public class MvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {SecurityConfig.class, HttpSessionConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { Application.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}
