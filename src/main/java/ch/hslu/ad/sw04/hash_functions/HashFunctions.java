package ch.hslu.ad.sw04.hash_functions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

@FunctionalInterface
interface HashFunction {
    int hash(String s);
}

public class HashFunctions {

    /**
     * Zählt die Anzahl Kollisionen, die bei der Anwendung der Hashfunktion f auf die Liste der Wörter auftreten.
     * @param f die Hashfunktion, die auf die Wörter angewendet wird
     * @param words die Liste der Wörter, auf die die Hashfunktion angewendet wird
     * @return die Anzahl der Kollisionen
     */
    public static int countCollisions(HashFunction f, List<String> words) {
        Set<Integer> set = new TreeSet<>();
        int collisionCount = 0;

        for (String word : words) {
            int hash = f.hash(word);
            if (set.contains(hash)) {
                // Kollision!
                collisionCount++;
            } else {
                set.add(hash);
            }
        }

        return collisionCount;
    }

    public static void main(String[] args) throws IOException {

        // Aufgabe b)
        HashFunction h1 = (String s) -> s.charAt(0);

        HashFunction h2 = (String s) -> s.chars().sum();

        HashFunction h3 =
                (String s) -> {
                    AtomicInteger result = new AtomicInteger(1);
                    s.chars().forEach((final int c) -> result.set(result.get() * c));
                    return result.get();
                };

        // Aufgabe c)
        HashFunction h4 = String::hashCode;

        Path path = Path.of("./src/main/java/ch/hslu/ad/sw04/hash_functions/words.txt");
        List<String> words = Files.readAllLines(path);

        // VERY BAD: A lot of words have the same first char, and so the same hash
        int collisionsH1 = countCollisions(h1, words);
        // BAD: A lot of collisions, because the char position doesn't count: "ab" has same hash as
        // "ba" (anagram collisions)
        // Also, the range of possible values is small
        int collisionsH2 = countCollisions(h2, words);
        // OKAY: Only a few collisions, because products grow way faster.
        // However, problem with anagram collisions and overflows.
        int collisionsH3 = countCollisions(h3, words);
        // VERY GOOD: polynomial hash
        int collisionsH4 = countCollisions(h4, words);

        System.out.printf("%-50s %-10d%n", "collisions of h1 (first char as hash):", collisionsH1);
        System.out.printf(
                "%-50s %-10d%n", "collisions of h2 (sum of all chars as hash):", collisionsH2);
        System.out.printf(
                "%-50s %-10d%n", "collisions of h3 (product of all chars as hash):", collisionsH3);
        System.out.printf(
                "%-50s %-10d%n", "collisions of h4 (Java's hashCode as hash):", collisionsH4);
    }
}
