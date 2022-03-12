package ua.foxminded;

import ua.foxminded.dao.DAOFacade;
import ua.foxminded.exception.DAOException;
import ua.foxminded.formatter.Formatter;
import ua.foxminded.sorter.Sorter;

public class Main {
    private static final String fileStart = "src/main/resources/start.log";
    private static final String fileEnd = "src/main/resources/end.log";
    private static final String fileAbbreviations = "src/main/resources/abbreviations.txt";

    public static void main(String[] args) {
        try {
            DAOFacade daoFacade = new DAOFacade();
            Sorter daoSorter = new Sorter();
            daoFacade.prepareDatabase(fileStart, fileEnd, fileAbbreviations);
            Formatter formatter = new Formatter(daoSorter.sortByLapTime());
            System.out.println(formatter.formatResult());
        } catch (DAOException exception) {
            System.err.println("Exception Message : " + exception.getMessage());
            System.exit(0);
        }
    }
}
