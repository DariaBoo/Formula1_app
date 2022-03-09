package ua.foxminded.dao;

import java.io.FileNotFoundException;

/**
 * This is a simple facade class which initiates the creation of the schema and
 * table and populates all the columns of the table.
 * 
 * @author Daria Bogush
 * @version 1.0
 *
 */
public class DAOFacade {
    private DAOCreator creator = new DAOCreator();
    private DAOFiller filler = new DAOFiller();

    /**
     * Prepares database by creating a new schema with the table if if doesn't
     * exist, and populate the table with data from resources files.
     * 
     * @param fileStart         should contain racers abbreviation and start date
     *                          and time.
     * @param fileEnd           should contain racers abbreviation and end date and
     *                          time.
     * @param fileAbbreviations should contain racers abbreviation, name and team.
     * @throws DAOException if a database access error occurs.
     */
    public void prepareDatabase(String fileStart, String fileEnd, String fileAbbreviations) throws DAOException {
        try {
            creator.createDatabase();
            creator.createTable();
            filler.insertPersonalData(fileAbbreviations);
            filler.updateValues(fileStart, fileEnd);
        } catch (FileNotFoundException exception) {
            System.err.println(exception.getMessage());
            System.exit(0);
        }
    }
}
