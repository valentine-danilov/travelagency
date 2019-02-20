package com.epam.travelagency;

import com.epam.travelagency.bean.AppConfig;
import com.epam.travelagency.bean.Hotel;
import com.epam.travelagency.bean.enumeration.HotelFeature;
import com.epam.travelagency.repository.HotelRepository;
import com.epam.travelagency.storage.posgresql.HotelDataContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

@SpringBootApplication
public class Client {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        HotelRepository repository = context.getBean(HotelRepository.class);
        HotelDataContext dataContext = context.getBean(HotelDataContext.class);
        repository.setDataContext(dataContext);
        Hotel hotel = new Hotel();
        hotel.setName("asd");
        hotel.setStars((byte) 4);
        hotel.setWebsite("asd.asd");
        hotel.setLatitude(new BigDecimal(13));
        hotel.setLongitude(new BigDecimal(13));
        hotel.setFeatures(HotelFeature.FOOD_DELIVERY);
        repository.create(hotel);
        SpringApplication.run(Client.class, args);
    }
}
