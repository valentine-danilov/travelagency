package service;

import com.epam.travelagency.bean.Hotel;
import com.epam.travelagency.bean.Tour;
import com.epam.travelagency.bean.enumeration.TourType;
import com.epam.travelagency.repository.TourRepository;
import com.epam.travelagency.service.TourService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TourServiceTest {

    private static final Integer INITIAL_ENTRY_NUMBER = 2;

    @InjectMocks
    private TourService tourService;

    @Mock
    private TourRepository repository;

    private Tour testTour;

    @Before
    public void init() {
        testTour = new Tour();
        testTour.setId(1);
        testTour.setPhoto("photo.jpg");
        testTour.setDate("01-01-01");
        testTour.setDuration(14);
        testTour.setDescription("description");
        testTour.setCost(new BigDecimal(500));
        testTour.setTourType(TourType.GUIDED);
        testTour.setHotelId(1);
        testTour.setCountryId(1);
        when(repository.read(ArgumentMatchers.anyInt())).thenReturn(testTour);
        when(repository.read()).thenReturn(List.of(new Tour(), new Tour()));
    }

    @Test
    public void shouldReadByIdCorrectly() {
        Tour actual = tourService.readById(1);
        verify(repository).read(1);
        Assert.assertEquals(testTour, actual);
    }

    @Test
    public void shouldInvokeReadAll() {
        List<Tour> actualHotels = tourService.readAll();
        Integer actual = actualHotels.size();
        verify(repository).read();
        Assert.assertEquals(INITIAL_ENTRY_NUMBER, actual);
    }

    @Test
    public void deleteShouldBeInvoked() {
        tourService.deleteById(1);
        verify(repository).delete(1);
    }

    @Test
    public void createShouldBeInvoked() {
        tourService.add(
                testTour.getPhoto(), testTour.getDate(),
                testTour.getDuration(), testTour.getCost(),
                testTour.getTourType(), testTour.getHotelId(),
                testTour.getCountryId()
        );
        verify(repository).create(ArgumentMatchers.any(Tour.class));
    }

    @Test
    public void updateShouldBeInvoked() {
        tourService.update(
                testTour.getId(),
                testTour.getPhoto(), testTour.getDate(),
                testTour.getDuration(), testTour.getCost(),
                testTour.getTourType(), testTour.getHotelId(),
                testTour.getCountryId()
        );
        verify(repository).update(ArgumentMatchers.any(Tour.class));
    }

}
