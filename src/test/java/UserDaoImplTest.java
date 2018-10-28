import com.revolut.dao.H2DaoFactory;
import com.revolut.dao.UserDaoImpl;
import com.revolut.model.User;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.*;

public class UserDaoImplTest {
    private static
    Logger log = Logger.getLogger(UserDaoImplTest.class);
    private final
    UserDaoImpl userDaoImpl = new UserDaoImpl();

    @BeforeClass
    public static void setup() {
        // prepare test database and test data by executing sql script demo.sql
        log.debug("setting up test database and sample data....");
        H2DaoFactory.populateTestData();
    }

    @Test
    public void testGetAllUsers() {
        List<User> allUsers = userDaoImpl.getAllUsers();
        assertTrue(allUsers.size() > 1);
    }

    @Test
    public void testGetUserById() {
        User u = userDaoImpl.getUserById(2L);
        assertEquals("test12411111", u.getName());
    }

    @Test
    public void testGetNonExistingUserById() {
        User u = userDaoImpl.getUserById(500L);
        assertNull(u);
    }

    @Test
    public void testGetNonExistingUserByName() {
        User u = userDaoImpl.getUserByName("abcdeftg");
        assertNull(u);
    }

    @Test
    public void testCreateUser() {
        User u = new User("abc");
        userDaoImpl.insertUser(u);
        User uAfterInsert = userDaoImpl.getUserByName("abc");
        assertEquals("abc", uAfterInsert.getName());
    }

    @Test
    public void testUpdateUser() {
        User u = new User(1L, "test2");
        userDaoImpl.updateUser(1L, u);
        assertEquals("test2", userDaoImpl.getUserById(1L).getName());
    }

    @Test
    public void testDeleteUser() {
        userDaoImpl.deleteUser(1L);
        assertNull(userDaoImpl.getUserById(1L));
    }
}
