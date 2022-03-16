package course.stream.sam;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Nio2Lambda {
    public static <A, B, C> Stream<C> zip(Stream<A> streamA, Stream<B> streamB, BiFunction<A, B, C> zipper) {
        final Iterator<A> iteratorA = streamA.iterator();
        final Iterator<B> iteratorB = streamB.iterator();
        final Iterator<C> iteratorC = new Iterator<C>() {
            @Override
            public boolean hasNext() {
                return iteratorA.hasNext() && iteratorB.hasNext();
            }

            @Override
            public C next() {
                return zipper.apply(iteratorA.next(), iteratorB.next());
            }
        };
        final boolean parallel = streamA.isParallel() || streamB.isParallel();
        return iteratorToFiniteStream(iteratorC, parallel);
    }

    public static <T> Stream<T> iteratorToFiniteStream(Iterator<T> iterator, boolean parallel) {
        final Iterable<T> iterable = () -> iterator;
        return StreamSupport.stream(iterable.spliterator(), parallel);
    }

    public static void main(String[] args) {
        var path = Paths.get("src/course/stream/sam/Nio2Lambda.java");
        try {
            var lines = Files.lines(path);
            var numbers = IntStream.iterate(1, x -> x + 1).mapToObj(i -> Integer.valueOf(i));
            var results = zip(numbers, lines, (Integer n, String line) ->  n + ": " + line);
            results.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        IntStream s = IntStream.of(1, 2, 3, 4);
        long count = s.peek(System.out::println).limit(2).count();
        System.out.println(count);
    }
}
