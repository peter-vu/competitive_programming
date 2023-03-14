package leetcode.daily.d230314;

import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.Deque;

public class SumRootToLeafNumbers {
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}


class Solution {
    public int sumNumbers(TreeNode root) {
        int rootToLeaf = 0, currNumber = 0;
        Deque<AbstractMap.SimpleImmutableEntry<TreeNode, Integer>> stack = new ArrayDeque();
        stack.push(new AbstractMap.SimpleImmutableEntry(root, 0));

        while (!stack.isEmpty()) {
            AbstractMap.SimpleImmutableEntry<TreeNode, Integer> p = stack.pop();
            root = p.getKey();
            currNumber = p.getValue();

            if (root != null) {
                currNumber = currNumber * 10 + root.val;
                // if it's a leaf, update root-to-leaf sum
                if (root.left == null && root.right == null) {
                    rootToLeaf += currNumber;
                } else {
                    stack.push(new AbstractMap.SimpleImmutableEntry(root.right, currNumber));
                    stack.push(new AbstractMap.SimpleImmutableEntry(root.left, currNumber));
                }
            }
        }
        return rootToLeaf;
    }
}

class Solution2 {
    public int sumNumbers(TreeNode root) {
        return sumNumbers(root, 0);
    }

    int sumNumbers(TreeNode node, int currSum) {
        int sum = 0;
        if (node == null) {
            sum = currSum * 10;
        } else if (node.left == null && node.right == null) {
            sum = currSum * 10 + node.val;
        } else if (node.left == null) {
            sum = sumNumbers(node.right, currSum * 10 + node.val);
        } else if (node.right == null) {
            sum = sumNumbers(node.left, currSum * 10 + node.val);
        } else {
            sum = sumNumbers(node.left, currSum * 10 + node.val) + sumNumbers(node.right, currSum * 10 + node.val);
        }
        return sum;
    }
}