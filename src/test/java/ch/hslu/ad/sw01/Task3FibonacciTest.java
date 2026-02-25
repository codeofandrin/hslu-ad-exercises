package ch.hslu.ad.sw01;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Task3FibonacciTest {

    private static final int N_TIME_TEST = 20;

    @Test
    void testFiboRec1Illegal() {
        assertThatThrownBy(() -> Task3Fibonacci.fiboRec1(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("'n' must be equal or greater 0");
    }

    @Test
    void testFiboRec1Edge() throws InterruptedException {
        assertThat(Task3Fibonacci.fiboRec1(0)).isEqualTo(0);
        assertThat(Task3Fibonacci.fiboRec1(1)).isEqualTo(1);
    }

    @Test
    void testFiboRec1() throws InterruptedException {
        assertThat(Task3Fibonacci.fiboRec1(36)).isEqualTo(14_930_352);
    }

    @Test
    void testFiboRec1TimeConsumption() throws InterruptedException {
        long beforeTime = System.currentTimeMillis();
        Task3Fibonacci.fiboRec1(N_TIME_TEST);
        long afterTime = System.currentTimeMillis();

        System.out.println("fiboRec1 time (1ms/call): " + (afterTime - beforeTime) + " ms");
    }

    @Test
    void testFiboRec2Illegal() {
        assertThatThrownBy(() -> Task3Fibonacci.fiboRec2(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("'n' must be equal or greater 0");
    }

    @Test
    void testFiboRec2Edge() throws InterruptedException {
        assertThat(Task3Fibonacci.fiboRec2(0)).isEqualTo(0);
        assertThat(Task3Fibonacci.fiboRec2(1)).isEqualTo(1);
    }

    @Test
    void testFiboRec2() throws InterruptedException {
        assertThat(Task3Fibonacci.fiboRec2(36)).isEqualTo(14_930_352);
    }

    @Test
    void testFiboRec2TimeConsumption() throws InterruptedException {
        long beforeTime = System.currentTimeMillis();
        Task3Fibonacci.fiboRec2(N_TIME_TEST);
        long afterTime = System.currentTimeMillis();

        System.out.println("fiboRec2 time (1ms/call): " + (afterTime - beforeTime) + " ms");
    }

    @Test
    void testFiboIterIllegal() {
        assertThatThrownBy(() -> Task3Fibonacci.fiboIter(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("'n' must be equal or greater 0");
    }

    @Test
    void testFiboIterEdge() throws InterruptedException {
        assertThat(Task3Fibonacci.fiboIter(0)).isEqualTo(0);
        assertThat(Task3Fibonacci.fiboIter(1)).isEqualTo(1);
    }

    @Test
    void testFiboIter() throws InterruptedException {
        assertThat(Task3Fibonacci.fiboIter(36)).isEqualTo(14_930_352);
    }

    @Test
    void testFiboIterTimeConsumption() throws InterruptedException {
        long beforeTime = System.currentTimeMillis();
        Task3Fibonacci.fiboIter(N_TIME_TEST);
        long afterTime = System.currentTimeMillis();

        System.out.println("fiboIter time (1ms/call): " + (afterTime - beforeTime) + " ms");
    }
}
