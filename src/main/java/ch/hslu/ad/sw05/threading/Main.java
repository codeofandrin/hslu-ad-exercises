package ch.hslu.ad.sw05.threading;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static void clear() {
        System.out.println("\033[2J");
    }

    private static void hideCursor() {
        System.out.println("\033[?25l");
    }

    private static List<Thread> getThreads() {
        List<Thread> threads = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            Star star = new Star(i);

            threads.add(Thread.startVirtualThread(star::move));
        }

        return threads;
    }

    static void main() throws InterruptedException {
        clear();
        hideCursor();

        for (Thread thread : getThreads()) {
            thread.join();
        }
    }
}
