import java.util.HashMap;
import java.util.Map;

public class BinaryTreeCanonical {
    private static Map<Integer, BinaryTreeNode> nodeMap = new HashMap<>();

    static class BinaryTreeNode {
        int key;
        BinaryTreeNode left, right;
        Integer cachedHash; // can speed up hash function computation hugely

        public BinaryTreeNode(int k, BinaryTreeNode l, BinaryTreeNode r) {
            this.cachedHash = -1; // sets cachedHash to a value that will not be in nodeMap
            this.key = k;
            this.left = l;
            this.right = r;

            getCanonical(this); // sets cachedHash for this instance
        }

        @Override
        public int hashCode(){
            return cachedHash;
        }

        @Override
        public boolean equals(Object obj){
            try {
                BinaryTreeNode node = (BinaryTreeNode)obj;
                return node.key == this.key
                        && node.left == this.left
                        && node.right == this.right;
            }
            catch (Exception ex) {
                return false;
            }
        }
    }

    static BinaryTreeNode getCanonical(BinaryTreeNode n) {
        // if the cachedHash is greater than -1 we can use it to get the node from nodeMap
        if (n.cachedHash >= 0 && nodeMap.containsValue(n)){
            n = nodeMap.get(n.cachedHash);
        }
        else if (nodeMap.containsValue(n)){
            // when n's cachedHash has not been set we can try to find an instance in nodeMap
            // that matches n and set n equal to the nodeMap instance

            for (Map.Entry<Integer, BinaryTreeNode> set : nodeMap.entrySet()){
                if (set.getValue().equals(n)){
                    n = set.getValue();
                    break;
                }
            }
        }
        else {
            // set the cachedHash for n and add it to the nodeMap

            n.cachedHash = nodeMap.size();
            nodeMap.put(n.cachedHash, n);
        }

        return n;
    }

    static int numberOfFlyweightNodes() {
        return nodeMap.size();
    }
}
