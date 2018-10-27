import dao.H2DaoFactory;
import dao.ScoreDao;
import model.Score;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static junit.framework.TestCase.*;

public class ScoreDaoTest {
    private static
    Logger log = Logger.getLogger(ScoreDaoTest.class);
    private final ScoreDao scoreDao = new ScoreDao();


    @BeforeClass
    public static void setup() {
        // prepare test database and test data by executing sql script demo.sql
        log.debug("setting up test database and sample data....");
        H2DaoFactory.populateTestData();
    }

    @Test
    public void testGetAllScores() {
        List<Score> allScores = scoreDao.getAllScores();
        assertTrue(allScores.size() > 1);
    }

    @Test
    public void testGetScoreById() {
        Score u = scoreDao.getScoreById(2L);
        assertEquals(new BigDecimal("200.0000"), u.getBalance());
    }

    @Test
    public void testGetNonExistingScoreById() {
        Score u = scoreDao.getScoreById(500L);
        assertNull(u);
    }

    @Test
    public void testCreateScore() {
        Score u = new Score(2, new BigDecimal(500.58), "abc");
        scoreDao.insertScore(u);
        Score uAfterInsert = scoreDao.getScoreByCurrencyCode("abc").get(0);
        assertEquals("abc", uAfterInsert.getCurrencyCode());
    }

    @Test
    public void testUpdateScore() {
        Score u = new Score(1L, new BigDecimal(80.90), "test2");
        scoreDao.updateScore(3L, u);
        assertEquals(new BigDecimal("80.9000"), scoreDao.getScoreById(3L).getBalance());
    }

    @Test
    public void testDeleteScore() {
        scoreDao.deleteScore(1L);
        assertNull(scoreDao.getScoreById(1L));
    }
}
