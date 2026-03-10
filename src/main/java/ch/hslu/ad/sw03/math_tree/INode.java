package ch.hslu.ad.sw03.math_tree;

import java.util.List;

public interface INode {
    int eval();

    String toMathString();

    List<String> compile();
}
