package ch.hslu.ad.sw03.math_tree;

import ch.hslu.ad.sw02.stack_machine.StackMachine;
import java.util.List;

public class Main {

    static void main() {
        INode n =
                new Mul(
                        new Add(new Number(10), new Number(2)),
                        new Add(new Number(2), new Mul(new Number(3), new Number(8))));
        System.out.println(n.toMathString());
        System.out.println(n.eval());

        List<String> tasks = n.compile();
        tasks.add("PRINT");
        System.out.println(tasks);

        StackMachine stackMachine = new StackMachine();
        stackMachine.run(tasks.toArray(new String[0]));
    }
}
