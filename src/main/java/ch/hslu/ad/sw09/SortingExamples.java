package ch.hslu.ad.sw09;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

record SortMeasurement(long timeMs, long cmps) {}

public class SortingExamples {

    /**
     * Liefert ein Array mit den Zahlen 1 bis size in zufälliger Reihenfolge.
     *
     * @param size die Anzahl der Zahlen
     *
     */
    static int[] getShuffledNumbers(int size) {
        List<Integer> numbers = IntStream.range(1, size + 1).boxed().collect(Collectors.toList());
        Collections.shuffle(numbers);
        return numbers.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * Liefert ein Array mit den Zahlen 1 bis size in aufsteigender Reihenfolge.
     *
     * @param size die Anzahl der Zahlen
     */
    static int[] getAscendingNumbers(int size) {
        return IntStream.range(1, size + 1).toArray();
    }

    /**
     * Liefert ein Array mit den Zahlen 1 bis size in absteigender Reihenfolge.
     *
     * @param size die Anzahl der Zahlen
     */
    static int[] getDescendingNumbers(int size) {
        return IntStream.range(1, size + 1).map(i -> size - i + 1).toArray();
    }

    static SortMeasurement measure(int[] array, Function<int[], Long> func) {
        long start = System.currentTimeMillis();
        long cmps = func.apply(array);
        long time = System.currentTimeMillis() - start;
        return new SortMeasurement(time, cmps);
    }

    static void benchmarkSort(final String name, final Function<int[], Long> func) {
        SortMeasurement meas100kRandom = measure(getShuffledNumbers(100_000), func);
        SortMeasurement meas100kSorted = measure(getAscendingNumbers(100_000), func);
        SortMeasurement meas100kReverse = measure(getDescendingNumbers(100_000), func);

        SortMeasurement meas200kRandom = measure(getShuffledNumbers(200_000), func);
        SortMeasurement meas200kSorted = measure(getAscendingNumbers(200_000), func);
        SortMeasurement meas200kReverse = measure(getDescendingNumbers(200_000), func);

        SortMeasurement meas400kRandom = measure(getShuffledNumbers(400_000), func);
        SortMeasurement meas400kSorted = measure(getAscendingNumbers(400_000), func);
        SortMeasurement meas400kReverse = measure(getDescendingNumbers(400_000), func);

        String divider1 = " ".repeat(15) + "||" + ("-".repeat(30) + "||").repeat(3);
        String divider2 =
                "-".repeat(15) + "||" + ("-".repeat(12) + "|" + "-".repeat(17) + "||").repeat(3);

        int tableWidth = 15 + 2 + ((30 + 2) * 3);
        int sideWidth = (tableWidth - name.length() - 2) / 2;
        int rest = (tableWidth - name.length() - 2) % 2;
        System.out.println(
                "=".repeat(sideWidth) + " " + name + " " + "=".repeat(sideWidth + rest) + "\n");

        System.out.printf(
                "%-15s||            n = 100k          ||           n = 200k           ||        "
                        + "   n = 400k           ||%n",
                "");
        System.out.println(divider1);
        System.out.printf(
                "%-15s|| %10s | %15s || %10s | %15s || %10s | %15s ||%n",
                "Input Array", "Time [ms]", "Cmps", "Time [ms]", "Cmps", "Time [ms]", "Cmps");
        System.out.println(divider2);
        System.out.printf(
                "%-15s|| %10d | %15d || %10d | %15d || %10d | %15d ||%n",
                "Random",
                meas100kRandom.timeMs(),
                meas100kRandom.cmps(),
                meas200kRandom.timeMs(),
                meas200kRandom.cmps(),
                meas400kRandom.timeMs(),
                meas400kRandom.cmps());
        System.out.printf(
                "%-15s|| %10d | %15d || %10d | %15d || %10d | %15d ||%n",
                "Sorted",
                meas100kSorted.timeMs(),
                meas100kSorted.cmps(),
                meas200kSorted.timeMs(),
                meas200kSorted.cmps(),
                meas400kSorted.timeMs(),
                meas400kSorted.cmps());
        System.out.printf(
                "%-15s|| %10d | %15d || %10d | %15d || %10d | %15d ||%n",
                "Sorted reverse",
                meas100kReverse.timeMs(),
                meas100kReverse.cmps(),
                meas200kReverse.timeMs(),
                meas200kReverse.cmps(),
                meas400kReverse.timeMs(),
                meas400kReverse.cmps());
    }

    public static void main(String[] args) {
        benchmarkSort("Insertion Sort", (int[] array) -> Sorts.insertionSort(array, false));
    }
}
