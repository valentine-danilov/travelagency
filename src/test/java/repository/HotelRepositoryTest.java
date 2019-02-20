package repository;


import com.epam.travelagency.bean.Hotel;
import com.epam.travelagency.bean.enumeration.HotelFeature;
import com.epam.travelagency.repository.HotelRepository;
import com.epam.travelagency.storage.posgresql.HotelDataContext;
import com.opentable.db.postgres.embedded.FlywayPreparer;
import com.opentable.db.postgres.junit.EmbeddedPostgresRules;
import com.opentable.db.postgres.junit.PreparedDbRule;
import config.TestConfig;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@ContextConfiguration(classes = TestConfig.class)
public class HotelRepositoryTest {
    @ClassRule
    public static PreparedDbRule db =
            EmbeddedPostgresRules.preparedDatabase(
                    FlywayPreparer.forClasspathLocation("db/migration"));

    private static HotelRepository hotelRepository;
    private static Hotel testHotel;

    private static final Integer INITIAL_NUMBER_OF_ENTRIES = 4;
    private static final Integer FIRST_ENTRY_ID = 1;
    private static final String FIRST_ENTRY_NAME = "test1";
    private static final Byte FIRST_ENTRY_STARS = 4;
    private static final String FIRST_ENTRY_WEBSITE = "test1";
    private static final BigDecimal FIRST_ENTRY_LATITUDE = new BigDecimal(1)
            .setScale(6, RoundingMode.HALF_UP);
    private static final BigDecimal FIRST_ENTRY_LONGITUDE = new BigDecimal(1)
            .setScale(6, RoundingMode.HALF_UP);
    private static final HotelFeature FIRST_ENTRY_FEATURE = HotelFeature.SECURITY;

    @BeforeClass
    public static void init() {
        JdbcTemplate template = new JdbcTemplate(db.getTestDatabase());
        HotelDataContext dataContext = new HotelDataContext(template);
        hotelRepository = new HotelRepository();
        hotelRepository.setDataContext(dataContext);
        testHotel = new Hotel();
        testHotel.setId(5);
        testHotel.setName("TestHotel");
        testHotel.setStars((byte) 4);
        testHotel.setWebsite("testWebsite.com");
        BigDecimal latitude = new BigDecimal(10)
                .setScale(6, RoundingMode.HALF_UP);
        BigDecimal longitude = new BigDecimal(10)
                .setScale(6, RoundingMode.HALF_UP);
        testHotel.setLatitude(latitude);
        testHotel.setLongitude(longitude);
        testHotel.setFeatures(HotelFeature.FOOD_DELIVERY);
    }

    @Test
    public void shouldBeReadCorrectly() {
        Hotel actual = hotelRepository.read(FIRST_ENTRY_ID);
        Assert.assertEquals(FIRST_ENTRY_NAME, actual.getName());
        Assert.assertEquals(FIRST_ENTRY_STARS, actual.getStars());
        Assert.assertEquals(FIRST_ENTRY_WEBSITE, actual.getWebsite());
        Assert.assertEquals(FIRST_ENTRY_LATITUDE, actual.getLatitude());
        Assert.assertEquals(FIRST_ENTRY_LONGITUDE, actual.getLongitude());
        Assert.assertEquals(FIRST_ENTRY_FEATURE, actual.getFeatures());
    }

    @Test
    public void shouldReadAllEntries(){
        Integer actual = hotelRepository.read().size();
        Assert.assertEquals(INITIAL_NUMBER_OF_ENTRIES, actual);
    }

    @Test
    public void shouldBeCreated() {
        hotelRepository.create(testHotel);
        Hotel actual = hotelRepository.read(testHotel.getId());
        Assert.assertEquals(testHotel, actual);
    }

    @Test(expected = Exception.class)
    public void shouldBeDeleted(){
        hotelRepository.delete(FIRST_ENTRY_ID);
        hotelRepository.read(FIRST_ENTRY_ID);
    }


}
