import dao.H2DaoFactory;
import dao.UserDao;
import model.User;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.*;

public class UserDaoTest {
    private static
    Logger log = Logger.getLogger(UserDaoTest.class);
    private final
    UserDao userDao = new UserDao();

    @BeforeClass
    public static void setup() {
        // prepare test database and test data by executing sql script demo.sql
        log.debug("setting up test database and sample data....");
        H2DaoFactory.populateTestData();
    }

    @Test
    public void testGetAllUsers() {
        List<User> allUsers = userDao.getAllUsers();
        assertTrue(allUsers.size() > 1);
    }

    @Test
    public void testGetUserById() {
        User u = userDao.getUserById(2L);
        assertEquals("test12411111", u.getName());
    }

    @Test
    public void testGetNonExistingUserById() {
        User u = userDao.getUserById(500L);
        assertNull(u);
    }

    @Test
    public void testGetNonExistingUserByName() {
        User u = userDao.getUserByName("abcdeftg");
        assertNull(u);
    }

    @Test
    public void testCreateUser() {
        User u = new User("abc");
        userDao.insertUser(u);
        User uAfterInsert = userDao.getUserByName("abc");
        assertEquals("abc", uAfterInsert.getName());
    }

    @Test
    public void testUpdateUser() {
        User u = new User(1L, "test2");
        userDao.updateUser(1L, u);
        assertEquals("test2", userDao.getUserById(1L).getName());
    }

    @Test
    public void testDeleteUser() {
        userDao.deleteUser(1L);
        assertNull(userDao.getUserById(1L));
    }
}
