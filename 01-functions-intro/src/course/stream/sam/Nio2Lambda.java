package course.stream.sam;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Nio2Lambda {
    public static void main(String[] args) {
        var path = Paths.get("src/course/stream/sam/Nio2Lambda.java");
        try {
            Files.lines(path).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
