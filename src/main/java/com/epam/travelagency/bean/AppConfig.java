package com.epam.travelagency.bean;

import com.epam.travelagency.repository.HotelRepository;
import com.epam.travelagency.repository.UserRepository;
import com.epam.travelagency.storage.posgresql.HotelDataContext;
import com.epam.travelagency.storage.posgresql.TourDataContext;
import com.epam.travelagency.storage.posgresql.UserDataContext;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class AppConfig {

    @Bean
    @FlywayDataSource
    public HikariDataSource getDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/travelagencydb");
        dataSource.setUsername("postgres");
        dataSource.setPassword("1623107");
        return dataSource;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }

    @Bean
    public UserRepository userRepository(){
        return new UserRepository();
    }

    @Bean
    public HotelRepository hotelRepository(){
        return new HotelRepository();
    }

    @Bean
    public UserDataContext userDataContext(){
        return new UserDataContext(getJdbcTemplate());
    }

    @Bean
    public HotelDataContext hotelDataContext(){
        return new HotelDataContext(getJdbcTemplate());
    }

    @Bean
    TourDataContext tourDataContext(){
        return new TourDataContext(getJdbcTemplate());
    }



}
