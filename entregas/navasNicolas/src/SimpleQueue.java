public class SimpleQueue {
    private NodeValue head;
    private NodeValue tail;

    public boolean isEmpty() { return head == null; }

    public void enqueue(Node node) {
        NodeValue queue = new NodeValue(node);
        if (tail != null) tail.setNext(queue);
        tail = queue;
        if (head == null) head = queue;
    }

    public Node dequeue() {
        if (head == null) return null;
        Node tree = head.getTreeNode();
        head = head.getNext();
        if (head == null) tail = null;
        return tree;
    }
}