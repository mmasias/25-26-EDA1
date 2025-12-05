public class Node {
    private Order value;
    private Node left;
    private Node right;

    public Node(Order value) {
        this.value = value;
    }

    public Order getValue() {
        return value;
    }

    public void setValue(Order value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}