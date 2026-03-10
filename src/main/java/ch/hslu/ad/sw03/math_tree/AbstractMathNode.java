package ch.hslu.ad.sw03.math_tree;

public abstract class AbstractMathNode implements INode {

    private INode left;
    private INode right;

    public AbstractMathNode(final INode left, final INode right) {
        this.left = left;
        this.right = right;
    }

    public INode getLeft() {
        return this.left;
    }

    public INode getRight() {
        return this.right;
    }

    public void setLeft(final INode left) {
        this.left = left;
    }

    public void setRight(final INode right) {
        this.right = right;
    }
}
