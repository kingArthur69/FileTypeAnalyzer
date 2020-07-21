package analyzers;

import analyzer.PatternDescriptor;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class KMPFileAnalyzer extends FileAnalyzer {
    private final List<PatternDescriptor> patternDescriptors;

    public KMPFileAnalyzer(List<PatternDescriptor> descriptor) {
        this.patternDescriptors = descriptor;
    }

//

    @Override
    protected String getFileType(InputStream inputStream) throws IOException {
        byte[] fileBytes = inputStream.readAllBytes();
        for (PatternDescriptor descriptor : patternDescriptors) {
            if (isPatternExistsInArray(descriptor.getPattern(), fileBytes)) {
                return descriptor.getFileType();
            }
        }
        return FileAnalyzer.UNKNOWN_FILE_TYPE;
    }

    private boolean isPatternExistsInArray(String pattern, byte[] readAllBytes) {
        return !KMPSearch(pattern, readAllBytes).isEmpty();
    }

    public static List<Integer> KMPSearch(String pattern, byte[] text) {
        /* 1 */
        int[] prefixFunc = prefixFunction(pattern);
        ArrayList<Integer> occurrences = new ArrayList<>();
        int j = 0;
        /* 2 */
        for (int i = 0; i < text.length; i++) {
            /* 3 */
            while (j > 0 && text[i] != pattern.charAt(j)) {
                j = prefixFunc[j - 1];
            }
            /* 4 */
            if (text[i] == pattern.charAt(j)) {
                j += 1;
            }
            /* 5 */
            if (j == pattern.length()) {
                occurrences.add(i - j + 1);
                j = prefixFunc[j - 1];
            }
        }
        /* 6 */
        return occurrences;
    }

    public static int[] prefixFunction(String str) {
        /* 1 */
        int[] prefixFunc = new int[str.length()];

        /* 2 */
        for (int i = 1; i < str.length(); i++) {
            /* 3 */
            int j = prefixFunc[i - 1];

            while (j > 0 && str.charAt(i) != str.charAt(j)) {
                j = prefixFunc[j - 1];
            }

            /* 4 */
            if (str.charAt(i) == str.charAt(j)) {
                j += 1;
            }

            /* 5 */
            prefixFunc[i] = j;
        }

        /* 6 */
        return prefixFunc;
    }
}
