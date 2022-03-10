package ua.foxminded.formatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ua.foxminded.racer.Racer;

/**
 * Provides support for alignment and formatting of result.
 * <p>
 * Formats list of strings taken from the database using formatting pattern:
 * <p>
 * Serial number + dot + racers name and surname + pipe + racers team + pipe +
 * formated lap time
 * <p>
 * Example:
 * <p>
 * 1.Sebastian Vettel |FERRARI |1:04.415
 * 
 * @author Daria Bogush
 * @version 1.0
 */
public class Formatter {
    private final int MILLISECONDS_IN_A_SECOND = 1000;
    private final int MILLISECONDS_IN_A_MINUTE = 60000;
    private final String LAP_TIME_FORMAT = "%d:%02d.%03d";
    private final String PIPE = "|";
    private final String DOT = ".";
    private final String SLASH = "/";
    private final String lastWinnerNumber = "15";// assignment's condition
    private final List<Racer> racers;
    private int maxNameLength;
    private int maxTeamLength;
    private int maxLapTimeLength;
    private int maxLineInfoLength;

    /**
     * Constructs a new formatter with list of objects 'RacerDTO'.
     * 
     * @param racers list of RacerDTO filled from the database.
     */
    public Formatter(List<Racer> racers) {
        this.racers = racers;
    }

    /**
     * Returns a formatted String.
     * 
     * @return a String containing the formatted result as '1.Sebastian Vettel|FERRARI |1:04.415'
     */
    public String formatResult() {
        List<String> preparedRacers = prepareData();
        String format = "%-" + maxNameLength + "s%-" + maxTeamLength + "s%-" + maxLapTimeLength + "s%n";
        StringBuilder result = new StringBuilder();
        preparedRacers.stream().map(string -> string.split(SLASH)).forEach(s -> {
            result.append(String.format(format, s[0], PIPE + s[1], PIPE + s[2]));
            if (s[0].startsWith(lastWinnerNumber)) {
                result.append(createLineSeparator(maxLineInfoLength, "-")).append("\n");
            }
        });
        return result.toString();
    }

    private List<String> prepareData() {
        if (racers.size() == 0) {
            throw new NullPointerException(" : Parametr List<RacerDTO> racers is null in method "
                    + Thread.currentThread().getStackTrace()[1].getMethodName());
        }
        List<String> result = new ArrayList<String>(racers.size());
        String[] partOne = new String[racers.size()];
        String[] partTwo = new String[racers.size()];
        String[] partThree = new String[racers.size()];
        int serialNumber = 1;
        for (int i = 0; i < racers.size(); i++) {
            partOne[i] = serialNumber + DOT + racers.get(i).getName();
            partTwo[i] = SLASH + racers.get(i).getTeam();
            partThree[i] = SLASH + formatLapTime(racers.get(i).getLapTime());
            StringBuilder builder = new StringBuilder();
            builder.append(partOne[i] + partTwo[i] + partThree[i]);
            result.add(builder.toString());
            serialNumber++;
        }
        maxNameLength = getMaxLength(partOne);
        maxTeamLength = getMaxLength(partTwo);
        maxLapTimeLength = getMaxLength(partThree);
        maxLineInfoLength = maxNameLength + maxTeamLength + maxLapTimeLength;
        return result;
    }

    private int getMaxLength(String[] part) {
        return Arrays.stream(part).mapToInt(string -> string.length()).max().getAsInt();
    }

    private String createLineSeparator(int countOfSymbols, String symbol) {        
        return Stream.generate(() -> symbol).limit(countOfSymbols).collect(Collectors.joining());
    }

    private String formatLapTime(long duration) {
        long mm = duration / MILLISECONDS_IN_A_MINUTE;
        long ss = (duration - (mm * MILLISECONDS_IN_A_MINUTE)) / MILLISECONDS_IN_A_SECOND;
        long ms = duration - (mm * MILLISECONDS_IN_A_MINUTE) - (ss * MILLISECONDS_IN_A_SECOND);
        return String.format(LAP_TIME_FORMAT, mm, ss, ms);
    }
}
