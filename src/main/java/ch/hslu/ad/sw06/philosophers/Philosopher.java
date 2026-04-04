package ch.hslu.ad.sw06.philosophers;

public class Philosopher implements Runnable {
    private final Fork first;
    private final Fork second;
    private long waitTime = 0;

    public Philosopher(Fork left, Fork right) {
        if (System.identityHashCode(left) < System.identityHashCode(right)) {
            this.first = left;
            this.second = right;
        } else {
            this.first = right;
            this.second = left;
        }
    }

    private void sleepRandom() throws InterruptedException {
        Thread.sleep((int) (Math.random() * 200) + 200);
    }

    public void think() throws InterruptedException {
        sleepRandom();
    }

    public void eat() throws InterruptedException {
        long start = System.nanoTime();

        first.mutex().lock();
        second.mutex().lock();

        long end = System.nanoTime();
        waitTime += (end - start) / 1_000_000;

        try {
            sleepRandom();
        } finally {
            second.mutex().unlock();
            first.mutex().unlock();
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                think();
                eat();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public long getWaitTime() {
        return waitTime;
    }
}
