package ch.hslu.ad.sw05.histogram;

import java.util.LinkedList;
import java.util.List;

public class Histogram {

    private static void fill(
            final int[] histogram, final byte[] pixels, final int from, final int to) {
        for (int i = from; i < to; i++) {
            histogram[0xff & pixels[i]]++;
        }
    }

    private static int[] reduce(final List<int[]> histograms) {
        int[] result = new int[256];
        for (int[] histogram : histograms) {
            for (int i = 0; i < histogram.length; i++) {
                result[i] += histogram[i];
            }
        }

        return result;
    }

    // code by Mario Kopp, because prof's code doesn't work lol
    public static int[] getParallel(final byte[] pixels, final int nofThreads)
            throws InterruptedException {
        List<int[]> histograms = new LinkedList<>();
        List<Thread> threads = new LinkedList<>();
        int chunkSize = (int) Math.ceil((double) pixels.length / nofThreads);

        for (int threadId = 0; threadId < nofThreads; threadId++) {
            final int[] histogram = new int[256];
            final int from = threadId * chunkSize;
            final int to = Math.min((threadId + 1) * chunkSize, pixels.length);

            threads.add(
                    Thread.startVirtualThread(
                            () -> {
                                fill(histogram, pixels, from, to);
                            }));

            histograms.add(histogram);
        }

        for (Thread thread : threads) {
            thread.join();
        }

        return reduce(histograms);
    }

    public static int[] getSequential(final byte[] pixels) {
        int[] histogram = new int[256];
        fill(histogram, pixels, 0, pixels.length);
        return histogram;
    }
}
