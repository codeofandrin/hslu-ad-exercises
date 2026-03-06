package ch.hslu.ad.sw02.stack_machine;

import ch.hslu.ad.sw02.stack.Stack;

public final class StackMachine {
    private final Stack<Integer> stack = new Stack<>();

    private int[] getNextTwo() {
        if (this.stack.size() < 2) {
            throw new IllegalStateException("stack must contain at least two elements");
        }

        int a = this.stack.pop();
        int b = this.stack.pop();
        return new int[] {a, b};
    }

    private void load(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException("'value' must be greater or equal zero");
        }

        this.stack.push(value);
    }

    private void add() {
        int[] next = this.getNextTwo();
        this.stack.push(next[0] + next[1]);
    }

    private void sub() {
        int[] next = this.getNextTwo();
        this.stack.push(next[0] - next[1]);
    }

    private void mul() {
        int[] next = this.getNextTwo();
        this.stack.push(next[0] * next[1]);
    }

    private void div() {
        int[] next = this.getNextTwo();
        this.stack.push(next[0] / next[1]);
    }

    private void print() {
        System.out.println(this.stack.pop());
    }

    public void run(final String[] sequence) {
        for (String raw : sequence) {
            String command = raw.strip();
            String[] words = command.split(" ");

            String word1 = words[0];
            String word2 = null;
            if (words.length == 2) {
                word2 = words[1];
            }

            switch (word1.toLowerCase()) {
                case "load" -> {
                    if (word2 == null) {
                        throw new IllegalArgumentException("command 'LOAD' must have second word");
                    }

                    int number;
                    try {
                        number = Integer.parseInt(word2);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException(
                                "command 'LOAD' second word must be a valid integer");
                    }

                    this.load(number);
                }
                case "add" -> this.add();
                case "sub" -> this.sub();
                case "mul" -> this.mul();
                case "div" -> this.div();
                case "print" -> this.print();

                default -> throw new IllegalArgumentException("unknown command '" + command + "'");
            }
        }
    }
}
