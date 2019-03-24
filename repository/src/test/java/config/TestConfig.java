package config;

import com.epam.travelagency.entity.*;
import com.epam.travelagency.enumeration.HotelFeature;
import com.epam.travelagency.enumeration.TourType;
import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.epam.travelagency", "com.epam.travelagency.service.config"})
@PropertySource("classpath:application.properties")
public class TestConfig {
    @Autowired
    private
    Environment environment;

    @Bean
    public EmbeddedPostgres embeddedPostgres() throws IOException {
        return EmbeddedPostgres.start();
    }

    @Bean
    public DataSource dataSource() throws IOException {
        return embeddedPostgres().getPostgresDatabase();
    }

    @Bean(initMethod = "migrate")
    @DependsOn("dataSource")
    public Flyway flyway(DataSource dataSource) {
        return Flyway.configure().mixed(true).dataSource(dataSource).load();
    }

    @Bean
    @DependsOn("flyway")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) throws IOException {
        LocalContainerEntityManagerFactoryBean entityManager =
                new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(dataSource);
        entityManager.setPackagesToScan("com.epam.travelagency.entity");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.POSTGRESQL);
        entityManager.setJpaVendorAdapter(vendorAdapter);
        entityManager.setJpaProperties(hibernateProperties());
        return entityManager;
    }

    @Bean
    public Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto",
                environment.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.setProperty("hibernate.dialect",
                environment.getProperty("spring.jpa.properties.hibernate.dialect"));
        properties.setProperty("hibernate.default_schema",
                environment.getProperty("spring.jpa.properties.hibernate.default_schema"));
        return properties;
    }

    @Bean
    @DependsOn("entityManagerFactory")
    public PlatformTransactionManager transactionManager(
            LocalContainerEntityManagerFactoryBean factory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(factory.getObject());
        return transactionManager;
    }


    @Bean(name = "testHotel")
    public Hotel hotel() {
        Hotel testHotel = new Hotel();
        testHotel.setId(1);
        testHotel.setName("test1");
        Short stars = 4;
        testHotel.setStars(stars);
        testHotel.setWebsite("test1");
        testHotel.setLatitude(new BigDecimal(1)
                .setScale(6, RoundingMode.HALF_UP));
        testHotel.setLongitude(new BigDecimal(1)
                .setScale(6, RoundingMode.HALF_UP));
        testHotel.setFeatures(HotelFeature.SECURITY);
        return testHotel;
    }

    @Bean(name = "testCountry")
    public Country country() {
        Country country = new Country();
        country.setId(1);
        country.setName("testCountry");
        return country;
    }

    @Bean(name = "testTour")
    public Tour tour() {
        Tour testTour = new Tour();
        testTour.setId(1);
        testTour.setPhoto("test1.jpg");
        testTour.setDate(java.sql.Date.valueOf("2012-12-12"));
        testTour.setDuration(12);
        testTour.setDescription("description1");
        testTour.setCost(new BigDecimal(505)
                .setScale(3, RoundingMode.HALF_UP));
        testTour.setTourType(TourType.GUIDED);
        testTour.setCountry(country());
        testTour.setHotel(hotel());
        return testTour;
    }

    @Bean(name = "testUser")
    public User user() {
        User user = new User();
        user.setId(1);
        user.setLogin("user1");
        user.setPassword("user1");
        return user;
    }

    @Bean(name = "testReview1")
    public Review review() {
        Review review = new Review();
        review.setId(1);
        review.setText("Some words1");
        review.setDate(java.sql.Date.valueOf("2012-12-12"));
        review.setUser(user());
        review.setTour(tour());
        return review;
    }

}
