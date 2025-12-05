    public class SimpleQueue {
        private NodeValue head;
        private NodeValue tail;
        public boolean isEmpty() { return head == null; }
        public void enqueue(Node n) {
            NodeValue q = new NodeValue(n);
            if (tail != null) tail.next = q;
            tail = q;
            if (head == null) head = q;
        }
        public Node dequeue() {
            if (head == null) return null;
            Node t = head.treeNode;
            head = head.next;
            if (head == null) tail = null;
            return t;
        }
    }