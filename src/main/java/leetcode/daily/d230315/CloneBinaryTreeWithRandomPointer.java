package leetcode.daily.d230315;

import java.util.*;

public class CloneBinaryTreeWithRandomPointer {
}

class Solution {
    // Hashmap to map old tree's nodes with new tree's nodes.
    private HashMap<Node, NodeCopy> newOldPairs = new HashMap<>();

    private NodeCopy deepCopy(Node root) {
        if (root == null) {
            return null;
        }
        NodeCopy newRoot = new NodeCopy(root.val);
        // Deep copy left subtree and attach it on new node's left.
        newRoot.left = deepCopy(root.left);
        // Deep copy right subtree and attach it on new node's right.
        newRoot.right = deepCopy(root.right);
        // Store the new node and old node's pair in hash map.
        newOldPairs.put(root, newRoot);
        return newRoot;
    }

    private void mapRandomPointers(Node oldNode) {
        if (oldNode == null) {
            return;
        }
        NodeCopy newNode = newOldPairs.get(oldNode);
        Node oldNodeRandom = oldNode.random;
        NodeCopy newNodeRandom = newOldPairs.get(oldNodeRandom);
        // Map newNode with it's respective random node.
        newNode.random = newNodeRandom;
        // Traverse on rest nodes to map their respective new node's random pointers.
        mapRandomPointers(oldNode.left);
        mapRandomPointers(oldNode.right);
    }

    public NodeCopy copyRandomBinaryTree(Node root) {
        // Create a new tree for each node of old tree and map new node with old respective node.
        NodeCopy newRoot = deepCopy(root);
        // We will assign random pointers of new tree to respective correct new nodes.
        mapRandomPointers(root);
        return newRoot;
    }
}

class Node {
    int val;
    Node left;
    Node right;
    Node random;

    Node() {
    }

    Node(int val) {
        this.val = val;
    }

    Node(int val, Node left, Node right, Node random) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.random = random;
    }
}

class NodeCopy {
    int val;
    NodeCopy left;
    NodeCopy right;
    NodeCopy random;

    NodeCopy() {
    }

    NodeCopy(int val) {
        this.val = val;
    }

    NodeCopy(int val, NodeCopy left, NodeCopy right, NodeCopy random) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.random = random;
    }
}

