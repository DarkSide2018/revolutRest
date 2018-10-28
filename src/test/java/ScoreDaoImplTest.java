import com.revolut.dao.H2DaoFactory;
import com.revolut.dao.ScoreDaoImpl;
import com.revolut.model.Score;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static junit.framework.TestCase.*;

public class ScoreDaoImplTest {
    private static
    Logger log = Logger.getLogger(ScoreDaoImplTest.class);
    private final
    ScoreDaoImpl scoreDaoImpl = new ScoreDaoImpl();


    @BeforeClass
    public static void setup() {
        // prepare test database and test data by executing sql script demo.sql
        log.debug("setting up test database and sample data....");
        H2DaoFactory.populateTestData();
    }

    @Test
    public void testGetAllScores() {
        List<Score> allScores = scoreDaoImpl.getAllScores();
        assertTrue(allScores.size() > 1);
    }

    @Test
    public void testGetScoreById() {
        Score u = scoreDaoImpl.getScoreById(2L);
        assertEquals(new BigDecimal("200.0000"), u.getBalance());
    }

    @Test
    public void testGetNonExistingScoreById() {
        Score u = scoreDaoImpl.getScoreById(500L);
        assertNull(u);
    }

    @Test
    public void testCreateScore() {
        Score u = new Score(2, new BigDecimal(500.58), "abc");
        scoreDaoImpl.insertScore(u);
        Score uAfterInsert = scoreDaoImpl.getScoreByCurrencyCode("abc").get(0);
        assertEquals("abc", uAfterInsert.getCurrencyCode());
    }

    @Test
    public void testUpdateScore() {
        Score u = new Score(1L, new BigDecimal(80.90), "test2");
        scoreDaoImpl.updateScore(3L, u);
        assertEquals(new BigDecimal("80.9000"), scoreDaoImpl.getScoreById(3L).getBalance());
    }

    @Test
    public void testDeleteScore() {
        scoreDaoImpl.deleteScore(1L);
        assertNull(scoreDaoImpl.getScoreById(1L));
    }
}
