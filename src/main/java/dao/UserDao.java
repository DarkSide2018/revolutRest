package dao;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
    private final static String SQL_GET_USER_BY_ID = "SELECT * FROM USER WHERE ID=?";

    public User getUserById(long userId) {
        ResultSet rs = null;
        User u = null;
        try(Connection conn = H2DaoFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(SQL_GET_USER_BY_ID)
        ) {
            stmt.setLong(1, userId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                u = new User(rs.getLong("id"), rs.getString("name"));

            }
            return u;
        }catch (Exception e){

        }
        return u;
    }
}
