package ua.foxminded.calculator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import ua.foxminded.dao.ConnectionManager;
import ua.foxminded.exception.DAOException;

/**
 * DAOCalculator is a class that calculates the lap time of the racers using
 * start time and end time taken from the database. The table of the database
 * should be already filled.
 * 
 * @author Boo Family
 * @version 1.0
 */
public class Calculator {
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
        try (Connection connection = ConnectionManager.getInstance().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SQL_SELECT_RACERS);) {
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String startTime = resultSet.getString("start_time");
                String endTime = resultSet.getString("end_time");
                if (startTime == null) {
                    throw new NullPointerException(" : Parametr 'startTime' is null in the class "
                            + Thread.currentThread().getStackTrace()[1].getClassName() + " in the method "
                            + Thread.currentThread().getStackTrace()[1].getMethodName());
                }
                LocalDateTime start = LocalDateTime.parse(startTime, DATE_TIME_FORMATTER);
                if (endTime == null) {
                    throw new NullPointerException(" : Parametr 'endTime' is null in the class "
                            + Thread.currentThread().getStackTrace()[1].getClassName() + " in the method "
                            + Thread.currentThread().getStackTrace()[1].getMethodName());
                }
                LocalDateTime end = LocalDateTime.parse(endTime, DATE_TIME_FORMATTER);
                long lapTime = Duration.between(start, end).toMillis();
                mapLapTime.put(id, lapTime);
            }
        } catch (SQLException sqlE) {
            throw new DAOException("Failed to connect to database while take lap time from table.", sqlE);
        }
        return mapLapTime;
    }
}
