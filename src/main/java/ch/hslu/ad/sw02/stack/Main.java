package ch.hslu.ad.sw02.stack;

public class Main {

    static void main() {
        Stack stack = new Stack(3);
        stack.push("toll");
        stack.push("sind");
        stack.push("Datenstrukturen");

        for (int i = 0; i < stack.size(); i++) {
            System.out.println(stack.pop());
        }
    }
}
