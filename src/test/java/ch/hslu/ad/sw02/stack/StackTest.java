package ch.hslu.ad.sw02.stack;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StackTest {

    @Test
    void testDefaultConstructor() {
        Stack stack = new Stack();
        assertThat(stack.isEmpty()).isTrue();
        assertThat(stack.size()).isEqualTo(1000);
    }

    @Test
    void testConstructor() {
        Stack stack = new Stack(1);
        assertThat(stack.isEmpty()).isTrue();
        assertThat(stack.size()).isEqualTo(1);
    }

    @Test
    void testPush() {
        Stack stack = new Stack();
        boolean result = stack.push("Test1");

        assertThat(result).isTrue();
        assertThat(stack.isEmpty()).isFalse();
        assertThat(stack.count()).isEqualTo(1);
        assertThat(stack.peek()).isEqualTo("Test1");
    }

    @Test
    void testPushFull() {
        Stack stack = new Stack(1);
        boolean result1 = stack.push("Test2");
        boolean result2 = stack.push("Test3");

        assertThat(result1).isTrue();
        assertThat(result2).isFalse();
        assertThat(stack.count()).isEqualTo(1);
    }

    @Test
    void testCount() {
        Stack stack = new Stack(5);
        stack.push("Test4");

        assertThat(stack.count()).isEqualTo(1);
    }

    @Test
    void testCountEmpty() {
        Stack stack = new Stack(5);
        assertThat(stack.count()).isEqualTo(0);
    }

    @Test
    void testSize() {
        Stack stack = new Stack(10);
        stack.push("Test5");

        assertThat(stack.size()).isEqualTo(10);
    }

    @Test
    void testPop() {
        Stack stack = new Stack(10);
        stack.push("Test6");
        stack.push("Test7");

        assertThat(stack.pop()).isEqualTo("Test7");
        assertThat(stack.peek()).isEqualTo("Test6");
        assertThat(stack.count()).isEqualTo(1);
    }

    @Test
    void testPopLast() {
        Stack stack = new Stack(1);
        stack.push("Test6");

        assertThat(stack.pop()).isEqualTo("Test6");
        assertThat(stack.count()).isEqualTo(0);
    }

    @Test
    void testPopEmpty() {
        Stack stack = new Stack(10);
        assertThat(stack.pop()).isEqualTo(null);
    }

    @Test
    void testPeek() {
        Stack stack = new Stack(10);
        stack.push("Test8");
        stack.push("Test9");

        assertThat(stack.peek()).isEqualTo("Test9");
        assertThat(stack.count()).isEqualTo(2);
    }

    @Test
    void testPeekEmpty() {
        Stack stack = new Stack(10);
        assertThat(stack.peek()).isEqualTo(null);
    }

    @Test
    void testClear() {
        Stack stack = new Stack(10);
        stack.push("Test10");
        stack.push("Test11");

        stack.clear();
        assertThat(stack.count()).isEqualTo(0);
    }

    @Test
    void testIsFullTrue() {
        Stack stack = new Stack(3);
        stack.push("Test12");
        stack.push("Test13");
        stack.push("Test14");

        assertThat(stack.isFull()).isTrue();
    }

    @Test
    void testIsFullFalse() {
        Stack stack = new Stack(3);
        stack.push("Test15");
        stack.push("Test16");

        assertThat(stack.isFull()).isFalse();
    }

    @Test
    void testIsEmptyTrue() {
        Stack stack = new Stack(3);
        assertThat(stack.isEmpty()).isTrue();
    }

    @Test
    void testIsEmptyFalse() {
        Stack stack = new Stack(3);
        stack.push("Test17");
        assertThat(stack.isEmpty()).isFalse();
    }
}
