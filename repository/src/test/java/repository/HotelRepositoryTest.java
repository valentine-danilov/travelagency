package repository;

import com.epam.travelagency.entity.Hotel;
import com.epam.travelagency.repository.IHotelRepository;
import com.epam.travelagency.repository.IRepository;
import config.TestConfig;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@FixMethodOrder(MethodSorters.JVM)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)

public class HotelRepositoryTest {

    private static final Integer INITIAL_ENTRY_NUMBER = 4;
    private static final Integer ID = 1;

    @Autowired
    private IHotelRepository hotelRepository;

    @Autowired
    private Hotel testHotel;

    @Test
    public void shouldReadByIdCorrectly() {
        testHotel.setId(ID);
        Hotel actual = hotelRepository.get(ID);
        Assert.assertEquals(testHotel, actual);
    }

    @Test
    public void shouldReadAll() {
        List<Hotel> actualHotels = hotelRepository.getAll();
        Integer actual = actualHotels.size();
        Assert.assertEquals(INITIAL_ENTRY_NUMBER, actual);
    }

    @Test
    @DirtiesContext
    public void shouldBeDeleted() {
        hotelRepository.delete(ID);
        Hotel hotel = hotelRepository.get(ID);
        Assert.assertNull(hotel);
    }

    @Test
    @DirtiesContext
    public void shouldBeAdded() {
        testHotel.setId(null);
        hotelRepository.add(testHotel);
        Hotel actual = hotelRepository.get(5);
        Assert.assertEquals(testHotel, actual);
    }

    @Test
    @DirtiesContext
    public void updateShouldBeInvoked() {
        testHotel.setId(2);
        hotelRepository.update(testHotel);
        Hotel actual = hotelRepository.get(2);
        Assert.assertEquals(testHotel, actual);
    }
}

