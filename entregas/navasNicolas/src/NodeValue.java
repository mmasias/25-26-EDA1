public class NodeValue {
     private Node treeNode;
     private NodeValue next;
 
     public NodeValue(Node t) {
         this.treeNode = t;
     }
 
     public Node getTreeNode() {
         return treeNode;
     }
 
     public void setTreeNode(Node treeNode) {
         this.treeNode = treeNode;
     }

     public NodeValue getNext() {
         return next;
     }
 
     public void setNext(NodeValue next) {
         this.next = next;
     }
 }