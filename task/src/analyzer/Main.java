package analyzer;

import analyzers.FileAnalyzer;
import analyzers.KMPFileAnalyzer;
import analyzers.NaiveFileAnalyzer;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String filePath = args[0];
        String patternsDb = args[1];

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<PatternDescriptor> descriptorMap = PatternDescriptorReader.readToList(patternsDb);
        FileAnalyzer analyzer = create(descriptorMap);

//        long startTime = System.nanoTime();
        List<Future<String>> collect = FileDirectoryParser.parse(filePath)
                .map(path -> executorService.submit(() -> analyzer.analyze(path)))
                .collect(Collectors.toList());

        collect.forEach(stringFuture -> {
            try {
                System.out.println(stringFuture.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        executorService.shutdown();
//        long elapsedNanos = System.nanoTime() - startTime;

//        System.out.println("It took " + elapsedNanos + " seconds");
    }

    public static FileAnalyzer create(List<PatternDescriptor> descriptor) {
        return  new KMPFileAnalyzer(descriptor);
    }
}
