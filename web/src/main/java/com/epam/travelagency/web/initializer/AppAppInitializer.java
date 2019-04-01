package com.epam.travelagency.web.initializer;

import com.epam.travelagency.repository.config.DataAccessConfig;
import com.epam.travelagency.service.config.ServiceConfig;
import com.epam.travelagency.web.config.WebConfig;
import com.epam.travelagency.web.security.config.SecurityConfig;
import org.springframework.lang.NonNull;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Array;


public class AppAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    @NonNull
    protected String getServletName() {
        return "DispatcherServlet";
    }

    @Override
    @NonNull
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{WebConfig.class, SecurityConfig.class, ServiceConfig.class, DataAccessConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
    }

    @Override
    protected Filter[] getServletFilters() {
        OpenEntityManagerInViewFilter filter = new OpenEntityManagerInViewFilter();
        return new Filter[] {filter};
    }
}
