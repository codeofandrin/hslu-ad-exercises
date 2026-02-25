package ch.hslu.ad.sw01;

public class Task3Fibonacci {

    private static int[] fiboRec2Memo;

    public static int fiboRec1(final int n) throws InterruptedException {
        // Thread.sleep(1);    // for time measurement

        // Rekursionsbasis
        if (n < 0) {
            throw new IllegalArgumentException("'n' must be equal or greater 0");
        }

        if (n == 0 || n == 1) {
            return n;
            // Rekursionsschritt
        } else {
            return fiboRec1(n - 2) + fiboRec1(n - 1);
        }
    }

    public static int fiboRec2(final int n) throws InterruptedException {
        // Thread.sleep(1);    // for time measurement

        // Rekursionsbasis
        if (n < 0) {
            throw new IllegalArgumentException("'n' must be equal or greater 0");
        }

        // init
        if (fiboRec2Memo == null || fiboRec2Memo.length <= n) {
            fiboRec2Memo = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                fiboRec2Memo[i] = -1;
            }
        }

        if (n == 0 || n == 1) {
            return n;
        }

        // Rekursionsschritt
        if (fiboRec2Memo[n] != -1) {
            return fiboRec2Memo[n];
        }

        fiboRec2Memo[n] = fiboRec2(n - 2) + fiboRec2(n - 1);
        return fiboRec2Memo[n];
    }

    public static int fiboIter(final int n) throws InterruptedException {
        // Rekursionsbasis
        if (n < 0) {
            throw new IllegalArgumentException("'n' must be equal or greater 0");
        }

        int n_2 = 0;
        int n_1 = 1;
        int result = n;
        // Rekursionsschritt
        for (int i = 2; i <= n; i++) {
            // Thread.sleep(1);    // for time measurement
            result = n_2 + n_1;
            n_2 = n_1;
            n_1 = result;
        }
        return result;
    }
}
