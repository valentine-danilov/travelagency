package repository;

import com.epam.travelagency.bean.Country;
import com.epam.travelagency.bean.Hotel;
import com.epam.travelagency.bean.Tour;
import com.epam.travelagency.bean.enumeration.HotelFeature;
import com.epam.travelagency.bean.enumeration.TourType;
import com.epam.travelagency.repository.TourRepository;
import com.epam.travelagency.storage.posgresql.TourDataContext;
import com.opentable.db.postgres.embedded.FlywayPreparer;
import com.opentable.db.postgres.junit.EmbeddedPostgresRules;
import com.opentable.db.postgres.junit.PreparedDbRule;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TourRepositoryTest {
    private static final Integer INITIAL_NUMBER_OF_ENTRIES = 4;
    private static final Integer FIRST_ENTRY_ID = 1;
    @ClassRule
    public static PreparedDbRule db =
            EmbeddedPostgresRules.preparedDatabase(
                    FlywayPreparer.forClasspathLocation("db/migration"));

    private static TourRepository repository;

    private static Tour testTour;

    @BeforeClass
    public static void init() {
        //Repository init
        JdbcTemplate template = new JdbcTemplate(db.getTestDatabase());
        TourDataContext dataContext = new TourDataContext(template);
        repository = new TourRepository();
        repository.setDataContext(dataContext);

        //Test country init
        Country testCountry = new Country();
        testCountry.setId(1);
        testCountry.setName("testCountry");

        //Test hotel init
        Hotel testHotel = new Hotel();
        testHotel.setId(1);
        testHotel.setName("test1");
        testHotel.setStars((byte) 4);
        testHotel.setWebsite("test1");
        BigDecimal latitude = new BigDecimal(1)
                .setScale(6, RoundingMode.HALF_UP);
        BigDecimal longitude = new BigDecimal(1)
                .setScale(6, RoundingMode.HALF_UP);
        testHotel.setLatitude(latitude);
        testHotel.setLongitude(longitude);
        testHotel.setFeatures(HotelFeature.SECURITY);

        //Test tour init
        testTour = new Tour();
        testTour.setId(1);
        testTour.setPhoto("test1.jpg");
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(2012, Calendar.DECEMBER, 12);
        Date now = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(now);
        testTour.setDate(date);
        testTour.setDuration(12);
        testTour.setDescription("description1");
        testTour.setCost(new BigDecimal(505).setScale(3, RoundingMode.HALF_UP));
        testTour.setTourType(TourType.GUIDED);
        testTour.setCountry(testCountry);
        testTour.setHotel(testHotel);
    }

    @Test
    public void shouldReadCorrectly() {
        Tour actual = repository.read(FIRST_ENTRY_ID);
        Assert.assertEquals(testTour.getPhoto(), actual.getPhoto());
        Assert.assertEquals(testTour.getDate(), actual.getDate());
        Assert.assertEquals(testTour.getDuration(), actual.getDuration());
        Assert.assertEquals(testTour.getDescription(), actual.getDescription());
        Assert.assertEquals(testTour.getCost(), actual.getCost());
        Assert.assertEquals(testTour.getTourType(), actual.getTourType());
        Assert.assertEquals(testTour.getCountry(), actual.getCountry());
        Assert.assertEquals(testTour.getHotel(), actual.getHotel());
    }

    @Test
    public void shouldReadAllEntries() {
        Integer actual = repository.read().size();
        Assert.assertEquals(INITIAL_NUMBER_OF_ENTRIES, actual);
    }

    @Test
    public void shouldCreate() {
        Tour actual = repository.read(testTour.getId());
        Assert.assertEquals(testTour, actual);
    }

    @Test(expected = Exception.class)
    public void shouldBeDeleted() {
        repository.delete(FIRST_ENTRY_ID);
        repository.read(FIRST_ENTRY_ID);
    }
}
