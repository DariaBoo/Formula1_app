package ua.foxminded.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DAOFactoryTest {
    private final String DB_USERNAME = "postgres";
    private final String DB_PASSWORD = "555";
    private final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

    DAOFactory daoFactory = new DAOFactory();

    @Disabled("Not implemented yet")
    @Test
    void getConnection_connectionTest() throws DAOException, SQLException {
        assertEquals(DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD), daoFactory.getConnection());
    }
}
