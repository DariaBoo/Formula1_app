package ua.foxminded.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * DAOCalculator is a class that calculates the lap time of the racers using
 * start time and end time taken from the database. The table of the database
 * should be already filled.
 * 
 * @author Boo Family
 * @version 1.0
 */
public class DAOCalculator {
    private final String SQL_SELECT_RACERS = "SELECT * FROM racers.racers";
    private final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");

    /**
     * Returns a map with racers id and lap time that can be then formated and
     * displayed as a racers result. Lap time counted as a different between start
     * time and end time taken from the database.
     * 
     * @return the map with String as racers id and Long as racers lap time.
     * @throws DAOException if a database access error occurs.
     */
    public Map<String, Long> countLapTime() throws DAOException {
        Map<String, Long> mapLapTime = new HashMap<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DAOConnection.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_SELECT_RACERS);
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String startTime = resultSet.getString("start_time");
                String endTime = resultSet.getString("end_time");
                LocalDateTime start = LocalDateTime.parse(startTime, DATE_TIME_FORMATTER);
                LocalDateTime end = LocalDateTime.parse(endTime, DATE_TIME_FORMATTER);
                long lapTime = Duration.between(start, end).toMillis();
                mapLapTime.put(id, lapTime);
            }
        } catch (SQLException sqlE) {
            throw new DAOException("Failed to connect to database while take lap time from table.", sqlE);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException sqlE) {
                throw new DAOException("Failed to close connection while take lap time from table.", sqlE);
            }
        }
        return mapLapTime;
    }
}
