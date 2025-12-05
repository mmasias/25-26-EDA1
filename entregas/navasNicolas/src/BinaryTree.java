public class BinaryTree {
    private Node root;
    private int size;
    private long comparisons;

    public BinaryTree(int capacity) {
        this.root = null;
        this.size = 0;
        this.comparisons = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public long getComparisons() {
        return comparisons;
    }

    public void insert(Order order) {
        if (root == null) {
            root = new Node(order);
            size++;
            return;
        }

        Node current = root;
        while (true) {
            comparisons++;
            if (order.getRemainingTime() < current.value.getRemainingTime()) {
                if (current.left == null) {
                    current.left = new Node(order);
                    size++;
                    return;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(order);
                    size++;
                    return;
                }
                current = current.right;
            }
        }
    }

    public Order extractMin() {
        Node parent = null;
        Node current = root;
        while (current.left != null) {
            comparisons++;
            parent = current;
            current = current.left;
        }
        Order min = current.value;

        if (parent == null) {
            root = current.right;
        } else {
            parent.left = current.right;
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
                System.out.print(n.value.getType() + ":" + n.value.getRemainingTime() + "  ");
                if (n.left != null) { q.enqueue(n.left); nodesInNext++; }
                if (n.right != null) { q.enqueue(n.right); nodesInNext++; }
            }
            System.out.println();
            level++;
            nodesInCurrent = nodesInNext;
            nodesInNext = 0;
        }
    }
}