package repository;


import com.epam.travelagency.bean.Hotel;
import com.epam.travelagency.bean.enumeration.HotelFeature;
import com.epam.travelagency.repository.HotelRepository;
import com.epam.travelagency.storage.posgresql.HotelDataContext;
import config.TestConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class HotelRepositoryTest {
    @Autowired
    private HotelDataContext dataContext;
    @Autowired
    private HotelRepository hotelRepository;

    private Hotel testHotel;

    @Before
    public void init() {
        hotelRepository.setDataContext(dataContext);
        testHotel = new Hotel();
        testHotel.setId(1);
        testHotel.setName("TestHotel");
        testHotel.setStars((byte) 4);
        testHotel.setWebsite("testWebsite.com");
        testHotel.setLatitude(new BigDecimal(10));
        testHotel.setLongitude(new BigDecimal(10));
        testHotel.setFeatures(HotelFeature.FOOD_DELIVERY);
    }

    @Test
    public void shouldBeCreated() {
        hotelRepository.create(testHotel);
        Assert.assertEquals(hotelRepository.read(testHotel.getId()), testHotel);
    }
}
