package dao;

import model.User;
import util.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserDao {
    private static final Logger LOGGER = LoggerFactory.getLogger();
    private final static String GET_BY_ID = "SELECT * FROM USER WHERE ID=?";
    private final static String GET_ALL = "SELECT * FROM User";
    private final static String GET_BY_NAME = "SELECT * FROM User WHERE name = ? ";
    private final static String INSERT = "INSERT INTO User (name) VALUES (?)";
    private final static String UPDATE = "UPDATE User SET name = ? WHERE ID=? ";
    private final static String DELETE_BY_ID = "DELETE FROM User WHERE id = ? ";

    public User getUserById(long userId) {
        LOGGER.info("getUser by id: " + userId);
        ResultSet rs = null;
        User u = null;
        try (Connection conn = H2DaoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(GET_BY_ID)
        ) {
            stmt.setLong(1, userId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                u = new User(rs.getLong("id"), rs.getString("name"));

            }
            return u;
        } catch (Exception e) {
            LOGGER.warning("error with getUser by id: " + userId);
        }
        return u;
    }

    public List<User> getAllUsers() {
        LOGGER.info("getAllUsers");
        ResultSet rs = null;
        List<User> users = new ArrayList<User>();
        try (Connection conn = H2DaoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(GET_ALL)
        ) {
            rs = stmt.executeQuery();
            while (rs.next()) {
                User u = new User(rs.getLong("id"), rs.getString("name"));
                users.add(u);
            }
            return users;
        } catch (SQLException e) {
            LOGGER.warning("problem with getAllUsers");
        }
        return users;
    }

    public User getUserByName(String userName) {
        LOGGER.info("getUserByName");
        ResultSet rs = null;
        User u = null;
        try (Connection conn = H2DaoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(GET_BY_NAME);
        ) {
            stmt.setString(1, userName);
            rs = stmt.executeQuery();
            if (rs.next()) {
                u = new User(rs.getLong("id"), rs.getString("name"));
            }
            return u;
        } catch (SQLException e) {
            LOGGER.warning("problem with getUserByName : " + e.getMessage());
        }
        return u;
    }

    public void insertUser(User user) {
        LOGGER.info("insertUser");
        try (Connection conn = H2DaoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT)
        ) {
            stmt.setString(1, user.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warning("Error Inserting User :" + e);
        }
    }

    public void updateUser(Long userId, User user) {
        LOGGER.info("updateUser");
        try (Connection conn = H2DaoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE);
        ) {
            stmt.setString(1, user.getName());
            stmt.setLong(2, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warning("Error Updating User :" + e);
        }
    }

    public void deleteUser(long userId) {
        LOGGER.info("deleteUser");
        try (Connection conn = H2DaoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_BY_ID)
        ) {
            stmt.setLong(1, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warning("Error Deleting User :" + e);
        }
    }
}
