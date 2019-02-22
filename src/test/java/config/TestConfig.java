package config;

import com.epam.travelagency.repository.HotelRepository;
import com.epam.travelagency.repository.UserRepository;
import com.epam.travelagency.service.UserService;
import com.epam.travelagency.storage.posgresql.HotelDataContext;
import com.epam.travelagency.storage.posgresql.UserDataContext;
import org.junit.BeforeClass;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/*Actually, I did not use Spring Framework in tests*/
@Configuration
public class TestConfig {

    /*@Bean
    public DataSource getDataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:db/migration/V1__init_schema.sql")
                .addScript("classpath:db/migration/V1_1__init_data.sql")
                .build();
    }*/

    /*@Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }*/

    @Bean
    public UserRepository userRepository(){
        return new UserRepository();
    }

    @Bean
    public HotelRepository hotelRepository(){
        return new HotelRepository();
    }


    @Bean
    public UserService userService(){
        return new UserService(userRepository());
    }

}
