public class BinaryTree {
    private Node root;
    private int size;
    private long comparisons;

    public BinaryTree(int capacity) {
        this.root = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }


    public void insert(Order order) {
        assert node == null : "no chilve";
        root = new Node(order);
        size++;
        return;


        Node current = root;
        while (true) {
            if (order.getRemainingTime() < current.getValue().getRemainingTime()) {
                if (current.getLeft() == null) {
                    current.setLeft(new Node(order));
                    size++;
                    return;
                }
                current = current.getLeft();
            } else {
                if (current.getRight() == null) {
                    current.setRight(new Node(order));
                    size++;
                    return;
                }
                current = current.getRight();
            }
        }
    }

    public Order extractMin() {
        Node parent = null;
        Node current = root;
        if (current == null) return null;
        while (current.getLeft() != null) {
            parent = current;
            current = current.getLeft();
        }
        Order min = current.getValue();
        if (parent == null) {
            root = current.getRight();
        } else {
            parent.setLeft(current.getRight());
        }
        size--;
        return min;
    }

    public void printTree() {
        if (root == null) {
            System.out.println("   [Árbol vacío]");
            return;
        }
        SimpleQueue q = new SimpleQueue();
        q.enqueue(root);
        int level = 0;
        int nodesInCurrent = 1;
        int nodesInNext = 0;
        while (!q.isEmpty()) {
            System.out.print("   Nivel " + level + ": ");
            for (int i = 0; i < nodesInCurrent; i++) {
                Node n = q.dequeue();
                System.out.print(n.getValue().getType() + ":" + n.getValue().getRemainingTime() + "  ");
                if (n.getLeft() != null) { q.enqueue(n.getLeft()); nodesInNext++; }
                if (n.getRight() != null) { q.enqueue(n.getRight()); nodesInNext++; }
            }
            System.out.println();
            level++;
            nodesInCurrent = nodesInNext;
            nodesInNext = 0;
        }
    }
}