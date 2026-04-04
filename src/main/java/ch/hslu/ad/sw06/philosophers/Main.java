package ch.hslu.ad.sw06.philosophers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    static void main() throws InterruptedException {

        boolean fair = true;
        List<Philosopher> philosophers = getPhilosophers(getForks(fair));

        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        for (Philosopher p : philosophers) {
            executor.submit(p);
        }

        executor.shutdown();
        // wait until all tasks finished, max 1 minute
        executor.awaitTermination(1, TimeUnit.MINUTES);

        long total = 0;
        for (int i = 0; i < philosophers.size(); i++) {
            long t = philosophers.get(i).getWaitTime();
            total += t;
            System.out.printf("Philosopher %d wait time: %d ms%n", i, t);
        }

        System.out.println("Total wait time: " + total + " ms");
    }

    private static List<Fork> getForks(final boolean fair) {
        List<Fork> forks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            forks.add(new Fork(fair));
        }
        return forks;
    }

    private static List<Philosopher> getPhilosophers(final List<Fork> forks) {
        List<Philosopher> philosophers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Fork left = forks.get(i);
            Fork right = forks.get((i + 1) % 5);
            philosophers.add(new Philosopher(left, right));
        }

        return philosophers;
    }
}
