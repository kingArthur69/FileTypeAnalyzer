package analyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class FileDirectoryParser {

    public static Stream<Path> parse(String directoryPath) {
        try {
            return Files.list(Path.of(directoryPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Stream.empty();
    }
}
