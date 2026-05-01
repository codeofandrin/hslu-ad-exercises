package ch.hslu.ad.sw10;

import java.util.Collections;
import java.util.List;
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

    enum ArrayType {
        SHUFFLED("Random"),
        ASC("Sorted"),
        DESC("Sorted reverse");

        final String label;

        ArrayType(String label) {
            this.label = label;
        }
    }

    static SortMeasurement measure(int[] array, Function<int[], Long> func) {
        long start = System.currentTimeMillis();
        long cmps = func.apply(array);
        long time = System.currentTimeMillis() - start;
        return new SortMeasurement(time, cmps);
    }

    static int[] generateArray(int size, ArrayType type) {
        return switch (type) {
            case SHUFFLED -> getShuffledNumbers(size);
            case ASC -> getAscendingNumbers(size);
            case DESC -> getDescendingNumbers(size);
        };
    }

    static void benchmarkSort(
            String name,
            Function<int[], Long> func,
            int[] sizes,
            ArrayType[] types,
            boolean showAvg) {
        final int labelCol = 15;
        final int timeCol = 10;
        final int cmpsCol = 15;

        final String CELL_FMT = " %" + timeCol + "s | %" + cmpsCol + "s ||";
        final int dataCol = timeCol + cmpsCol + 5;

        SortMeasurement[][] results = new SortMeasurement[types.length][sizes.length];
        for (int t = 0; t < types.length; t++)
            for (int s = 0; s < sizes.length; s++)
                results[t][s] = measure(generateArray(sizes[s], types[t]), func);

        String cellDivider = "-".repeat(timeCol + 2) + "|" + "-".repeat(cmpsCol + 2) + "||";
        String dividerBetween =
                "-".repeat(labelCol) + "||" + ("-".repeat(dataCol) + "||").repeat(sizes.length);
        String dividerHeader = "-".repeat(labelCol) + "||" + cellDivider.repeat(sizes.length);

        int tableWidth = labelCol + 2 + (dataCol + 2) * sizes.length;
        int sideWidth = (tableWidth - name.length() - 2) / 2;
        int rest = (tableWidth - name.length() - 2) % 2;

        System.out.println(
                "=".repeat(sideWidth) + " " + name + " " + "=".repeat(sideWidth + rest) + "\n");

        System.out.printf("%-" + labelCol + "s||", "");
        for (int size : sizes) {
            String label = "n = " + formatSize(size);
            int left = (dataCol - label.length()) / 2;
            int right = dataCol - label.length() - left;
            System.out.print(" ".repeat(left) + label + " ".repeat(right) + "||");
        }
        System.out.println();
        System.out.println(dividerBetween);

        System.out.printf("%-" + labelCol + "s||", "Input Array");
        for (int i = 0; i < sizes.length; i++) System.out.printf(CELL_FMT, "Time [ms]", "Cmps");
        System.out.println();
        System.out.println(dividerHeader);

        for (int t = 0; t < types.length; t++) {
            System.out.printf("%-" + labelCol + "s||", types[t].label);
            for (int s = 0; s < sizes.length; s++)
                System.out.printf(
                        CELL_FMT,
                        formatSize(results[t][s].timeMs()),
                        formatSize(results[t][s].cmps()));
            System.out.println();
        }

        if (showAvg) {
            System.out.println(dividerHeader.replaceAll("-", "="));
            System.out.printf("%-" + labelCol + "s||", "Avg");
            for (int s = 0; s < sizes.length; s++) {
                long avgTime = 0, avgCmps = 0;
                for (int t = 0; t < types.length; t++) {
                    avgTime += results[t][s].timeMs();
                    avgCmps += results[t][s].cmps();
                }
                System.out.printf(
                        CELL_FMT,
                        formatSize(avgTime / types.length),
                        formatSize(avgCmps / types.length));
            }
            System.out.println();
        }

        System.out.println();
    }

    static String formatSize(long n) {
        long abs = Math.abs(n);

        if (abs >= 1_000_000_000) {
            double v = n / 1_000_000_000.0;
            return format(v) + "B";
        }
        if (abs >= 1_000_000) {
            double v = n / 1_000_000.0;
            return format(v) + "M";
        }
        if (abs >= 1_000) {
            double v = n / 1_000.0;
            return format(v) + "k";
        }
        return String.valueOf(n);
    }

    private static String format(double v) {
        if (v == (long) v) return String.valueOf((long) v);
        return String.format("%.1f", v);
    }

    public static void main(String[] args) {
        // ===== Task 2 =====

        // --- e) ---
        // int[] sizes         = { 1_000_000, 2_000_000, 4_000_000 };
        // ArrayType[] types   = new ArrayType[10]; Arrays.fill(types, ArrayType.SHUFFLED);
        //
        // benchmarkSort("Quick Sort", (int[] array) -> Sorts.quickSort(array, false, false), sizes,
        // types, true);

        // --- f) ---
        // low n, without random pivot
        // ---
        // int[] sizes = {10_000, 20_000, 40_000};
        // ArrayType[] types = {ArrayType.ASC, ArrayType.DESC, ArrayType.SHUFFLED};
        // benchmarkSort(
        //        "Quick Sort without random pivot",
        //        (int[] array) -> Sorts.quickSort(array, false, false),
        //        sizes,
        //        types,
        //        false);

        // ---
        // low n, with random pivot
        // ---
        // benchmarkSort("Quick Sort with random pivot", (int[] array) -> Sorts.quickSort(array,
        // true, false), sizes, types, false);

        // ---
        // high n, with random pivot
        // ---
        // int[] sizes         = { 1_000_000, 2_000_000, 4_000_000 };
        // ArrayType[] types   = { ArrayType.ASC, ArrayType.DESC, ArrayType.SHUFFLED};
        // benchmarkSort("Quick Sort with random pivot", (int[] array) -> Sorts.quickSort(array,
        // true, false), sizes, types, false);

        // ===== Task 4 =====
        int[] sizes = {1_000_000, 2_000_000, 4_000_000};
        ArrayType[] types = {ArrayType.ASC, ArrayType.DESC, ArrayType.SHUFFLED};
        benchmarkSort(
                "Heapsort", (int[] array) -> Sorts.heapSort(array, false), sizes, types, false);
    }
}
