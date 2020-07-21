package analyzers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.Callable;

public abstract class FileAnalyzer {
    public static final String UNKNOWN_FILE_TYPE = "Unknown file type";

    public String analyze(Path fileName) {

        try (InputStream inputStream = Files.newInputStream(fileName)) {
            return fileName + ": " + getFileType(inputStream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return UNKNOWN_FILE_TYPE;
    }

    protected abstract String getFileType(InputStream inputStream) throws IOException;
}
