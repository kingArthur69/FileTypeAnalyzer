package analyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class PatternDescriptorReader {

    public static Map<Integer, PatternDescriptor> readToMap(String patternsDb) {

        Map<Integer, PatternDescriptor> descriptorHashMap = new HashMap<>();

        try (Stream<String> stream = Files.lines(Paths.get(patternsDb))) {
            stream.forEach(s -> {
                String[] strings = s.replace("\"", "").split(";");
                descriptorHashMap.put(Integer.valueOf(strings[0]), new PatternDescriptor(strings[1], strings[2]));
            });
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return descriptorHashMap.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

    }

    public static List<PatternDescriptor> readToList(String patternsDb) {
        List<PatternDescriptor> descriptors = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(patternsDb))) {
            stream.forEach(s -> {
                String[] strings = s.replace("\"", "").split(";");
                descriptors.add(new PatternDescriptor(strings[1], strings[2]));
            });
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        Collections.reverse(descriptors);
        return descriptors;
    }
}
