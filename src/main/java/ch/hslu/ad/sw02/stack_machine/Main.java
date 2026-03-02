package ch.hslu.ad.sw02.stack_machine;

public class Main {

    private static final String[] TASK_A_SEQUENCE =
            new String[] {"LOAD 2", "LOAD 3", "ADD", "LOAD 4", "MUL", "PRINT"};
    private static final String[] TASK_B_SEQUENCE =
            new String[] {"LOAD 4", "LOAD 5", "ADD", "LOAD 2", "LOAD 3", "ADD", "MUL", "PRINT"};
    private static final String[] TASK_C_SEQUENCE =
            new String[] {"LOAD 4", "LOAD 7", "SUB", "LOAD 6", "DIV", "LOAD 5", "MUL", "PRINT"};

    static void main() {
        StackMachine stackMachine = new StackMachine();

        System.out.print("a) ");
        stackMachine.run(TASK_A_SEQUENCE);
        System.out.print("b) ");
        stackMachine.run(TASK_B_SEQUENCE);
        System.out.print("c) ");
        stackMachine.run(TASK_C_SEQUENCE);
    }
}
