package com.epam.travelagency;

import com.epam.travelagency.bean.AppConfig;
import com.epam.travelagency.bean.User;
import com.epam.travelagency.dataaccess.DataSource;
import org.flywaydb.core.Flyway;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {
    public static void main(String[] args) {
        /*Flyway flyway = Flyway.configure().dataSource(url, user, password).load();
        flyway.migrate();*/
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        User user = context.getBean(User.class);
        DataSource dataSource = context.getBean(DataSource.class);
        System.out.println(dataSource);
        System.out.println(user);

    }
}
