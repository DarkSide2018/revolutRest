import com.revolut.dao.H2DaoFactory;
import com.revolut.dao.ScoreDaoImpl;
import com.revolut.model.Account;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static junit.framework.TestCase.*;

public class AccountDaoImplTest {
    private static
    Logger log = Logger.getLogger(AccountDaoImplTest.class);
    private final
    ScoreDaoImpl scoreDaoImpl = new ScoreDaoImpl();


    @BeforeClass
    public static void setup() {
        log.debug("setting up test database and sample data....");
        H2DaoFactory.populateTestData();
    }

    @Test
    public void testGetAllScores() {
        List<Account> allAccounts = scoreDaoImpl.getAllScores();
        assertTrue(allAccounts.size() > 1);
    }

    @Test
    public void testGetScoreById() {
        Account u = scoreDaoImpl.getScoreById(2L);
        assertEquals(new BigDecimal("200.0000"), u.getBalance());
    }

    @Test
    public void testGetNonExistingScoreById() {
        Account u = scoreDaoImpl.getScoreById(500L);
        assertNull(u);
    }

    @Test
    public void testCreateScore() {
        Account u = new Account(2, new BigDecimal(500.58), "abc");
        scoreDaoImpl.insertScore(u);
        Account uAfterInsert = scoreDaoImpl.getScoreByCurrencyCode("abc").get(0);
        assertEquals("abc", uAfterInsert.getCurrencyCode());
    }

    @Test
    public void testUpdateScore() {
        Account u = new Account(1L, new BigDecimal(80.90), "test2");
        scoreDaoImpl.updateScore(3L, u);
        assertEquals(new BigDecimal("80.9000"), scoreDaoImpl.getScoreById(3L).getBalance());
    }

    @Test
    public void testDeleteScore() {
        scoreDaoImpl.deleteScore(1L);
        assertNull(scoreDaoImpl.getScoreById(1L));
    }
}
