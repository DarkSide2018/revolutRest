package dao;

import dao.interFaces.ScoreDao;
import model.Score;
import util.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ScoreDaoImpl implements ScoreDao {
    private static final String SELECT_BY_ID = "SELECT * FROM Score WHERE id=?";
    private final static String UPDATE = "UPDATE Score SET userID = ?,balance=?,currencyCode=? WHERE ID=? ";
    private static final String GET_ALL = "SELECT * FROM Score";
    private final static String INSERT = "INSERT INTO Score (userID,balance,currencyCode) VALUES (?,?,?)";
    private static final String DELETE = "DELETE FROM Score WHERE id = ?";
    private static final Logger LOGGER = LoggerFactory.getLogger();

    @Override
    public Score getScoreById(long scoreId) {
        LOGGER.info("getScore by id: " + scoreId);
        ResultSet rs = null;
        Score score = null;
        try (Connection conn = H2DaoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID)
        ) {
            stmt.setLong(1, scoreId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                score = new Score(rs.getLong("id"),
                        rs.getLong("userID"),
                        rs.getBigDecimal("balance"),
                        rs.getString("currencyCode"));
            }
            return score;
        } catch (Exception e) {
            LOGGER.warning("error with getScore by id: " + e.getMessage());
        }
        return score;
    }

    @Override
    public List<Score> getScoreByCurrencyCode(String code) {
        LOGGER.info("getScore by currencyCode: " + code);
        ResultSet rs = null;
        Score score = null;
        List<Score> scores = new ArrayList<Score>();
        try (Connection conn = H2DaoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Score WHERE currencyCode=?")
        ) {
            stmt.setString(1, code);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Score u = new Score(rs.getLong("id"),
                        rs.getLong("userID"),
                        rs.getBigDecimal("balance"),
                        rs.getString("currencyCode"));
                scores.add(u);
            }
            return scores;
        } catch (Exception e) {
            LOGGER.warning("error with getScore by id: " + e);
            return scores;
        }
    }

    @Override
    public List<Score> getAllScores() {

        LOGGER.info("getAllScores");
        ResultSet rs = null;
        List<Score> scores = new ArrayList<Score>();
        try (Connection conn = H2DaoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(GET_ALL)
        ) {
            rs = stmt.executeQuery();
            while (rs.next()) {
                Score u = new Score(rs.getLong("id"),
                        rs.getLong("userID"),
                        rs.getBigDecimal("balance"),
                        rs.getString("currencyCode"));
                scores.add(u);
            }
            return scores;
        } catch (SQLException e) {
            LOGGER.warning("problem with getAllScores : " + e);
        }
        return scores;
    }

    @Override
    public void insertScore(Score score) {
        LOGGER.info("insertUser");
        try (Connection conn = H2DaoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT)
        ) {
            stmt.setLong(1, score.getUserId());
            stmt.setBigDecimal(2, score.getBalance());
            stmt.setString(3, score.getCurrencyCode());
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warning("Error Inserting User :" + e);
        }
    }

    @Override
    public void updateScore(Long scoreId, Score score) {
        LOGGER.info("updateScore");
        try (Connection conn = H2DaoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE);
        ) {
            stmt.setLong(1, score.getUserId());
            stmt.setBigDecimal(2, score.getBalance());
            stmt.setString(3, score.getCurrencyCode());
            stmt.setLong(4, scoreId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warning("Error Updating Score :" + e);
        }
    }

    @Override
    public void deleteScore(long scoreId) {
        LOGGER.info("deleteUser");
        try (Connection conn = H2DaoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE)
        ) {
            stmt.setLong(1, scoreId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warning("Error Deleting User :" + e);
        }
    }
}
