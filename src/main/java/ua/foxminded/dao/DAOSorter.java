package ua.foxminded.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.foxminded.racer.Racer;

/**
 * DAOSorter is a class that can sort data by lap time taken from database to be
 * displayed as a result.
 * 
 * @author Daria Bogush
 * @version 1.0
 */
public class DAOSorter {
    private final String SQL_SELECT_BY_LAP_TIME = "SELECT * FROM racers.racers ORDER BY lap_time";

    /**
     * Returns a sorted list by lap time from smallest to largest that can then be
     * formated and displayed as a racers results.
     * 
     * @return the sorted by lap time list with RacerDTO.
     * @throws DAOException if a database access error occurs.
     */
    public List<Racer> sortByLapTime() throws DAOException {
        List<Racer> racers = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DAOConnection.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_SELECT_BY_LAP_TIME);
            while (resultSet.next()) {
                racers.add(new Racer(resultSet.getString("name"), resultSet.getString("team"),
                        resultSet.getLong("lap_time")));
            }
        } catch (SQLException sqlE) {
            throw new DAOException("Failed to connect to database while sorted data from table or table does't exist.",
                    sqlE);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException sqlE) {
                throw new DAOException("Failed to close connection while fill table.", sqlE);
            }
        }
        return racers;
    }
}
