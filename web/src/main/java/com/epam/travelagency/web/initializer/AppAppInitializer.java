package com.epam.travelagency.web.initializer;

import com.epam.travelagency.repository.config.DataAccessConfig;
import com.epam.travelagency.service.config.ServiceConfig;
import com.epam.travelagency.web.config.WebConfig;
import com.epam.travelagency.web.security.config.SecurityConfig;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class AppAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer  {

    @Override
    @NonNull
    protected String getServletName() {
        return "DispatcherServlet";
    }

    @Override
    @NonNull
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { WebConfig.class, SecurityConfig.class, ServiceConfig.class, DataAccessConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }


}
