package analyzers;

import analyzer.PatternDescriptor;
import analyzers.FileAnalyzer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class NaiveFileAnalyzer extends FileAnalyzer {

    private final PatternDescriptor descriptor;

    public NaiveFileAnalyzer(PatternDescriptor descriptor) {
        this.descriptor = descriptor;
    }

    @Override
    protected String getFileType(InputStream inputStream) throws IOException {
        if (isPatternExistsInArray(descriptor.getPattern().getBytes(), inputStream.readAllBytes())) {
            return descriptor.getFileType();
        }
        return FileAnalyzer.UNKNOWN_FILE_TYPE;
    }

    private boolean isPatternExistsInArray(byte[] patternArray, byte[] array) {
        int patternLength = patternArray.length;
        for (int j = 0; j <= array.length - patternLength; j++) {
            if (Arrays.compare(array, j, j + patternLength, patternArray, 0, patternLength) == 0) {
                return true;
            }
        }
        return false;
    }
}
