package course.stream.reduce;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class StatefulReduction {
    public static void main(String[] args) throws IOException {
        var path = Paths.get("src/course/stream/reduce/StatefulReduction.java");
        Files.lines(path)
                .collect(HashMap<Integer, String>::new, (map, line) -> map.put(map.size(), line), Map::putAll) // Create a map of the index to the object
                .forEach((i, o) -> { // Now we can use a BiConsumer forEach!
                    System.out.println(String.format("%d: %s", i+1, o));
                });
    }
}
