package ua.foxminded.dao;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ua.foxminded.reader.ResourceReader;

/**
 * Fills the table 'Racers' upon SQL queries with racers id, name, team, start
 * time, end time and lap time. Takes data from resources files to fill id,
 * name, team, start time and end time data. This methods throw
 * FileNotFoundException if the files doesn't exist in the resource folder.
 * Takes data from database to fill lap time. And also all methods throw
 * DAOException if the connection to the database fails.
 * 
 * @author Daria Bogush
 * @version 1.0
 */
public class DAOFiller {
    private ResourceReader reader = new ResourceReader();

    private final String SQL_INSERT_PERSONAL_DATA = "INSERT INTO racers.racers (id, name, team) VALUES (?,?,?) ON CONFLICT DO NOTHING";
    private final String SQL_UPDATE_START_TIME = "UPDATE racers.racers SET start_time = ? WHERE id = ?";
    private final String SQL_UNDATE_END_TIME = "UPDATE racers.racers SET end_time = ? WHERE id = ?";
    private final String SQL_UPDATE_LAP_TIME = "UPDATE racers.racers SET lap_time = ? WHERE id = ?";

    private final String UNDERSCORE = "_";
    private final String MESSAGE_FILE_NOT_FOUND = " - file not found in directory src/main/resources.";

    private String id;
    private String startTime;
    private String endTime;

    /**
     * Inserts personal data to the table 'Racers' from abbreviations.txt into the
     * database upon SQL query that then can be displayed along with the racers
     * results.
     * 
     * @param fileAbbreviations should contain racers abbreviation, name and team.
     * @throws FileNotFoundException if file is not exist.
     * @throws DAOException          if a database access error occurs.
     */
    public void insertPersonalData(String fileAbbreviations) throws FileNotFoundException, DAOException {
        try (Connection connection = DAOConnection.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT_PERSONAL_DATA);) {
            if (!Files.exists(Paths.get(fileAbbreviations))) {
                throw new FileNotFoundException(Paths.get(fileAbbreviations).getFileName() + MESSAGE_FILE_NOT_FOUND);
            } else {
                List<String> listOfAbbreviations = reader.readFile(fileAbbreviations);
                for (String abbreviation : listOfAbbreviations) {
                    StringTokenizer token = new StringTokenizer(abbreviation, UNDERSCORE);
                    id = token.nextToken();
                    String name = token.nextToken();
                    String team = token.nextToken();
                    statement.setString(1, id);
                    statement.setString(2, name);
                    statement.setString(3, team);
                    statement.execute();
                }
            }
        } catch (SQLException sqlE) {
            throw new DAOException("Failed to connect while update personal data.", sqlE);
        }
    }

    /**
     * Updates start, end, lap time by id upon SQL query.
     * <p>
     * Updates start data from start.log into the database upon SQL query to count
     * lap time.
     * <p>
     * Updates end data from end.log into the database upon SQL query to count lap
     * time.
     * <p>
     * Updates lap time counted before as different between start and end data from
     * the database that then can be displayed as racers results.
     * 
     * @param fileStart should contain racers abbreviation and start date and time.
     * @param fileEnd   should contain racers abbreviation and end date and time.
     * @throws FileNotFoundException if file is not exist.
     * @throws DAOException          if a database access error occurs.
     */
    public void updateValues(String fileStart, String fileEnd) throws FileNotFoundException, DAOException {
        updateStartData(fileStart);
        updateEndData(fileEnd);
        updateLapTime();
    }

    private void updateStartData(String fileStart) throws FileNotFoundException, DAOException {
        try (Connection connection = DAOConnection.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_START_TIME);) {
            if (!Files.exists(Paths.get(fileStart))) {
                throw new FileNotFoundException(Paths.get(fileStart).getFileName() + MESSAGE_FILE_NOT_FOUND);
            } else {
                List<String> listStart = reader.readFile(fileStart);
                for (String string : listStart) {
                    Pattern pattern = Pattern.compile("[^a-zA-Z]{3}");// first three letters
                    Matcher matcher = pattern.matcher(string);
                    if (matcher.find()) {
                        id = string.substring(0, matcher.start());
                        startTime = string.substring(matcher.start());
                    }
                    statement.setString(1, startTime);
                    statement.setString(2, id);
                    statement.executeUpdate();
                }
            }
        } catch (SQLException sqlE) {
            throw new DAOException("Failed to connect while update start data.", sqlE);
        }
    }

    private void updateEndData(String fileEnd) throws FileNotFoundException, DAOException {
        try (Connection connection = DAOConnection.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_UNDATE_END_TIME);) {
            if (!Files.exists(Paths.get(fileEnd))) {
                throw new FileNotFoundException(Paths.get(fileEnd).getFileName() + MESSAGE_FILE_NOT_FOUND);
            } else {
                List<String> listEnd = reader.readFile(fileEnd);
                for (String string : listEnd) {
                    Pattern pattern = Pattern.compile("[^a-zA-Z]{3}");// first three letters
                    Matcher matcher = pattern.matcher(string);
                    if (matcher.find()) {
                        id = string.substring(0, matcher.start());
                        endTime = string.substring(matcher.start());
                    }
                    statement.setString(1, endTime);
                    statement.setString(2, id);
                    statement.executeUpdate();
                }
            }
        } catch (SQLException sqlE) {
            throw new DAOException("Failed to connect while update end data.", sqlE);
        }
    }

    private void updateLapTime() throws DAOException {
        Map<String, Long> mapLapTime = new DAOCalculator().countLapTime();
        try (Connection connection = DAOConnection.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_LAP_TIME);) {
            for (Map.Entry<String, Long> pair : mapLapTime.entrySet()) {
                statement.setLong(1, pair.getValue());
                statement.setString(2, pair.getKey());
                statement.executeUpdate();
            }
        } catch (SQLException sqlE) {
            throw new DAOException("Failed to connect while update lap time.", sqlE);
        }
    }
}
