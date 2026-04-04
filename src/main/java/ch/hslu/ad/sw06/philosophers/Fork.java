package ch.hslu.ad.sw06.philosophers;

import java.util.concurrent.locks.ReentrantLock;

public class Fork {
    private final ReentrantLock lock;

    public Fork(boolean fair) {
        this.lock = new ReentrantLock(fair);
    }

    public ReentrantLock mutex() {
        return lock;
    }
}
