package repository;

import com.epam.travelagency.entity.Country;
import com.epam.travelagency.entity.Tour;
import com.epam.travelagency.entity.User;
import com.epam.travelagency.enumeration.TourType;
import com.epam.travelagency.repository.IRepository;
import com.epam.travelagency.repository.ITourRepository;
import com.epam.travelagency.repository.impl.postgre.TourRepository;
import com.epam.travelagency.repository.specification.impl.postgre.tour.*;
import config.TestConfig;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@FixMethodOrder(MethodSorters.JVM)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class TourRepositoryTest {

    private static Logger LOG = LoggerFactory.getLogger(TourRepositoryTest.class);


    private static final Integer INITIAL_ENTRY_NUMBER = 4;
    private static final Integer ID = 1;

    @Autowired
    private ITourRepository tourRepository;

    @Autowired
    private Tour testTour;

    @Test
    @Transactional
    public void shouldReadByIdCorrectly() {
        testTour.setId(1);
        Tour actual = tourRepository.get(ID);
        Assert.assertEquals(testTour, actual);
    }

    @Test
    public void shouldReadAll() {
        List<Tour> actualHotels = tourRepository.getAll();
        Integer actual = actualHotels.size();
        Assert.assertEquals(INITIAL_ENTRY_NUMBER, actual);
    }

    @Test
    @DirtiesContext
    public void shouldDeleteById() {
        tourRepository.delete(ID);
        Tour actual = tourRepository.get(ID);
        Assert.assertNull(actual);
    }

    @Test
    @DirtiesContext
    public void shouldBeAdded() {
        testTour.setId(null);
        tourRepository.add(testTour);
        Tour actual = tourRepository.get(5);
        Assert.assertEquals(testTour, actual);
    }

    @Test
    @DirtiesContext
    public void shouldBeUpdated() {
        testTour.setId(2);
        testTour.getHotel().setId(1);
        tourRepository.update(testTour);
        Tour actual = tourRepository.get(2);
        Assert.assertEquals(testTour, actual);
    }

    /*@Test
    public void shouldFindByCountry() {
        List<Tour> actual = tourRepository.findAllBySpecification(new ByCountry(new Country() {{
            setId(1);
        }}));
        List<Tour> expected = List.of(new Tour() {{
                                          setId(1);
                                      }},
                new Tour() {{
                    setId(2);
                }});
        Assert.assertFalse(actual.isEmpty());
        Assert.assertEquals(2, actual.size());
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldFindByTourDate() {
        List<Tour> actual = tourRepository.findAllBySpecification(new ByTourDate("2012-12-11"));
        List<Tour> expected = List.of(new Tour() {{
            setId(2);
        }});
        Assert.assertFalse(actual.isEmpty());
        Assert.assertEquals(1, actual.size());
        Assert.assertEquals(expected, actual);
    }*/

    @Test
    public void shouldFindByTourDuration() {
        List<Tour> actualLess = tourRepository.findAllBySpecification(List.of(new ByTourDuration(10, "<")));
        List<Tour> actualEq = tourRepository.findAllBySpecification(List.of(new ByTourDuration(11, "=")));
        List<Tour> actualGr = tourRepository.findAllBySpecification(List.of(new ByTourDuration(12, ">")));
        List<Tour> expectedEq = List.of(
                new Tour() {{
                    setId(3);
                }});
        List<Tour> expectedLess = List.of(
                new Tour() {{
                    setId(2);
                }});
        List<Tour> expectedGr = List.of(
                new Tour() {{
                    setId(1);
                }},
                new Tour() {{
                    setId(4);
                }});
        Assert.assertFalse(actualLess.isEmpty());
        Assert.assertEquals(1, actualLess.size());
        Assert.assertEquals(expectedLess, actualLess);
        Assert.assertFalse(actualEq.isEmpty());
        Assert.assertEquals(1, actualEq.size());
        Assert.assertEquals(expectedEq, actualEq);
        Assert.assertFalse(actualGr.isEmpty());
        Assert.assertEquals(2, actualGr.size());
        Assert.assertEquals(expectedGr, actualGr);
    }

    @Test
    public void shouldFindByTourType() {
        List<Tour> actual = tourRepository.findAllBySpecification(List.of(new ByTourType(TourType.BUS)));
        List<Tour> expected = List.of(new Tour() {{
            setId(3);
        }});
        Assert.assertFalse(actual.isEmpty());
        Assert.assertEquals(1, actual.size());
        Assert.assertEquals(expected, actual);
    }

    /*@Test
    public void shouldFindByTourCost() {
        List<Tour> actual = tourRepository
                .findAllBySpecification(
                        List.of(new ByTourCost(
                                new BigDecimal(500)
                                        .setScale(3, RoundingMode.UP),
                                "<"), new ByTourType(TourType.CRUISE)));
        List<Tour> expected = List.of(
                new Tour() {{
                    setId(2);
                }},
                new Tour() {{
                    setId(3);
                }}
        );
        Assert.assertFalse(actual.isEmpty());
        Assert.assertEquals(2, actual.size());
        Assert.assertEquals(expected, actual);
    }
*/
    /*@Test
    public void shouldFindByHotelStars() {
        List<Tour> actual = tourRepository
                .findAllBySpecification(new ByHotelStars((short) 4, "<"));
        List<Tour> expected = List.of(
                new Tour() {{
                    setId(1);
                }},
                new Tour() {{
                    setId(4);
                }}
        );
        Assert.assertFalse(actual.isEmpty());
        Assert.assertEquals(2, actual.size());
        Assert.assertEquals(expected, actual);
    }*/

    /*@Test
    public void shouldReadByUserCorrectly() {
        List<Tour> actual = tourRepository.findAllByUser(new User() {{
            setId(2);
        }});
        List<Tour> expected = List.of(
                new Tour() {{
                    setId(1);
                }},
                new Tour() {{
                    setId(4);
                }}
        );
        int actualSize = actual.size();
        Assert.assertFalse(actual.isEmpty());
        Assert.assertEquals(2, actualSize);
        Assert.assertEquals(expected, actual);
    }*/


}
