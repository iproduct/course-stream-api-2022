package course.stream.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class KeywordExtraction {
    public static void main(String[] args) throws IOException {
        var path = Paths.get("data/fp_wiki.txt");
        Files.lines(path).skip(80).limit(5)
                .forEach(System.out::println);
    }
}
