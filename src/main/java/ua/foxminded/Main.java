package ua.foxminded;

import ua.foxminded.dao.DAOException;
import ua.foxminded.dao.DAOFacade;
import ua.foxminded.dao.DAOSorter;
import ua.foxminded.formatter.Formatter;

public class Main {
    private static final String fileStart = "src/main/resources/start.log";
    private static final String fileEnd = "src/main/resources/end.log";
    private static final String fileAbbreviations = "src/main/resources/abbreviations.txt";

    public static void main(String[] args) {
        try {
            DAOFacade daoFacade = new DAOFacade();
            DAOSorter daoSorter = new DAOSorter();
            daoFacade.prepareDatabase(fileStart, fileEnd, fileAbbreviations);
            Formatter formatter = new Formatter(daoSorter.sortByLapTime());
            System.out.println(formatter.formatResult());
        } catch (DAOException exception) {
            System.err.println("Exception Message : " + exception.getMessage());
            System.exit(0);
        }
    }
}
