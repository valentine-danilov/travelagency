package service;

import com.epam.travelagency.bean.Hotel;
import com.epam.travelagency.bean.enumeration.HotelFeature;
import com.epam.travelagency.repository.HotelRepository;
import com.epam.travelagency.service.HotelService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HotelServiceTest {

    private static final Integer INITIAL_ENTRY_NUMBER = 2;
    @InjectMocks
    private HotelService hotelService;

    @Mock
    private HotelRepository repository;

    private Hotel testHotel;

    @Before
    public void init() {
        testHotel = new Hotel();
        testHotel.setId(1);
        testHotel.setName("test");
        testHotel.setStars((byte) 5);
        testHotel.setWebsite("test.com");
        testHotel.setLatitude(new BigDecimal(10));
        testHotel.setLongitude(new BigDecimal(11));
        testHotel.setFeatures(HotelFeature.WI_FI);

        when(repository.read(ArgumentMatchers.anyInt())).thenReturn(testHotel);
        when(repository.read()).thenReturn(List.of(new Hotel(), new Hotel()));
    }

    @Test
    public void shouldReadByIdCorrectly() {
        Hotel actual = hotelService.readById(1);
        verify(repository).read(1);
        Assert.assertEquals(testHotel, actual);
    }

    @Test
    public void shouldInvokeReadAll() {
        List<Hotel> actualHotels = hotelService.readAll();
        Integer actual = actualHotels.size();
        verify(repository).read();
        Assert.assertEquals(INITIAL_ENTRY_NUMBER, actual);
    }

    @Test
    public void deleteShouldBeInvoked() {
        hotelService.deleteById(1);
        verify(repository).delete(1);
    }

    @Test
    public void createShouldBeInvoked() {
        hotelService.add(
                testHotel.getName(), testHotel.getStars(),
                testHotel.getWebsite(), testHotel.getLatitude(),
                testHotel.getLongitude(), testHotel.getFeatures()
        );
        verify(repository).create(ArgumentMatchers.any(Hotel.class));
    }

    @Test
    public void updateShouldBeInvoked() {
        hotelService.update(
                testHotel.getId(),
                testHotel.getName(), testHotel.getStars(),
                testHotel.getWebsite(), testHotel.getLatitude(),
                testHotel.getLongitude(), testHotel.getFeatures()
        );
        verify(repository).update(ArgumentMatchers.any(Hotel.class));
    }
}
