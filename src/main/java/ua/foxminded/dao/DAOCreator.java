package ua.foxminded.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Creates a new schema with empty table in the database upon SQL query that can
 * then be populated with data. If schema or table already exist does nothing.
 * The methods in this class all throw a DAOException if the connection to the
 * database fails.
 * 
 * @author Daria Bogush
 */
public class DAOCreator {
    private final String sqlCreateSchema = "CREATE SCHEMA IF NOT EXISTS racers";
    private final String sqlCreateTable = "CREATE TABLE IF NOT EXISTS racers.racers\r\n" + "(\r\n"
            + "\"id\" text COLLATE pg_catalog.\"default\" NOT NULL,\r\n"
            + "\"name\" character varying(20) COLLATE pg_catalog.\"default\" NOT NULL,\r\n"
            + "\"team\" character varying(30) COLLATE pg_catalog.\"default\" NOT NULL,\r\n"
            + "\"start_time\" character varying,\r\n" + "\"end_time\" character varying,\r\n"
            + "\"lap_time\" bigint,\r\n" + "CONSTRAINT \"RACER_pkey\" PRIMARY KEY (\"id\")\r\n" + ")";

    /**
     * Creates a new schema upon SQL query if not exist.
     * 
     * @throws DAOException if a database access error occurs.
     */
    public final void createDatabase() throws DAOException {
        try (Connection connection = DAOConnection.getInstance().getConnection();
                Statement statement = connection.createStatement();) {
            statement.execute(sqlCreateSchema);
        } catch (SQLException e) {
            throw new DAOException("Failed to connect to database while create new schema.", e);
        }
    }

    /**
     * Creates an empty table upon SQL query with columns id as primary key, name,
     * team, start time, end time and lap time. If the table already exist does
     * nothing.
     * 
     * @throws DAOException
     */
    public final void createTable() throws DAOException {
        try (Connection connection = DAOConnection.getInstance().getConnection();
                Statement statement = connection.createStatement();) {
            statement.execute(sqlCreateTable);
        } catch (SQLException e) {
            throw new DAOException("Failed to connect to database while create new table.", e);
        }
    }
}
