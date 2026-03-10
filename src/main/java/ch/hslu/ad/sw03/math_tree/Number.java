package ch.hslu.ad.sw03.math_tree;

import java.util.List;

public final class Number implements INode {
    private final int value;

    Number(final int value) {
        this.value = value;
    }

    @Override
    public int eval() {
        return this.value;
    }

    @Override
    public String toMathString() {
        return String.valueOf(value);
    }

    @Override
    public List<String> compile() {
        return List.of("LOAD " + this.eval());
    }
}
