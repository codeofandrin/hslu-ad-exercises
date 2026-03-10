package ch.hslu.ad.sw03.math_tree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Add extends AbstractMathNode {

    public Add(final INode left, final INode right) {
        super(left, right);
    }

    @Override
    public int eval() {
        return this.getLeft().eval() + this.getRight().eval();
    }

    @Override
    public String toMathString() {
        return String.format(
                "(%s+%s)", this.getLeft().toMathString(), this.getRight().toMathString());
    }

    @Override
    public List<String> compile() {
        List<String> list =
                new ArrayList<>(
                        Stream.concat(
                                        this.getLeft().compile().stream(),
                                        this.getRight().compile().stream())
                                .toList());
        list.add("ADD");
        return list;
    }
}
