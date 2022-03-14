package ua.foxminded.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import ua.foxminded.exception.ReaderException;

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
     * @throws ReaderException thrown if file is empty.
     */
    public List<String> readFile(String fileDirectory) throws ReaderException{
        List<String> result = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(fileDirectory))) {
            if (Files.size(Paths.get(fileDirectory)) == 0) {
               throw new ReaderException("File '" + fileDirectory + "' is empty.");               
            }
            lines.forEach(s -> result.add(s));
        } catch (IOException exception) {
            throw new RuntimeException("IOException in ResourceReader class.", exception);
        }
        return result;
    }
}
