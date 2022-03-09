package ua.foxminded.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Reads a file line by line as a stream from resource folder and converts it to
 * a list of strings. If text file is not exist throws IOException.
 * 
 * @author Daria Bogush
 * @version 1.0
 */
public class ResourceReader {
    /**
     * Reads a file line by line.
     * 
     * @param fileDirectory not empty text file or log file.
     * @return a List of strings containing the contents of the text file.
     */
    public List<String> readFile(String fileDirectory) {
        List<String> result = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(fileDirectory))) {
            lines.forEach(s -> result.add(s));
        } catch (IOException exception) {
            throw new RuntimeException("IOException in ResourceReader class.", exception);
        }
        return result;
    }
}
