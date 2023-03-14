package leetcode.daily.d230314;

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