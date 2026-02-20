package ch.hslu.ad.sw01;

public class Task2Runtime {
    private static long task1Count = 0;
    private static long task2Count = 0;
    private static long task3Count = 0;

    static void main() throws InterruptedException {
        System.out.printf(
                "%-10s %-15s %-15s %-15s %-15s %-10s %-10s %-10s %-10s%n",
                "n",
                "# total",
                "# task1",
                "# task2",
                "# task3",
                "% task1",
                "% task2",
                "% task3",
                "ms total (1us/call)");
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------------");

        for (int i = 0; i <= 15; i++) {
            task((int) Math.pow(2, i));
        }

        System.out.println(
                "-------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf(
                "%-10s %-15s %-15s %-15s %-15s %-10s %-10s %-10s %-10s%n",
                "n",
                "# total",
                "# task1",
                "# task2",
                "# task3",
                "% task1",
                "% task2",
                "% task3",
                "ms total (1us/call)");
    }

    public static void task(final int n) throws InterruptedException {
        long beforeTime = System.currentTimeMillis();

        task1();
        task1();
        task1();
        task1();
        for (int i = 0; i < n; i++) {
            task2();
            task2();
            task2();
            for (int j = 0; j < n; j++) {
                task3();
                task3();
            }
        }

        long afterTime = System.currentTimeMillis();
        long runTime = afterTime - beforeTime;

        long totalCalls = task1Count + task2Count + task3Count;
        System.out.printf(
                "%-10d %-15d %-15d %-15d %-15d %-10.4f %-10.4f %-10.4f %-10d%n",
                n,
                totalCalls,
                task1Count,
                task2Count,
                task3Count,
                (float) task1Count * 100 / totalCalls,
                (float) task2Count * 100 / totalCalls,
                (float) task3Count * 100 / totalCalls,
                runTime);
    }

    public static void task1() throws InterruptedException {
        task1Count++;
        Thread.sleep(0, 1000);
    }

    public static void task2() throws InterruptedException {
        task2Count++;
        Thread.sleep(0, 1000);
    }

    public static void task3() throws InterruptedException {
        task3Count++;
        Thread.sleep(0, 1000);
    }
}
