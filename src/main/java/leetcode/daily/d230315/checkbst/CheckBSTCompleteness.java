package leetcode.daily.d230315.checkbst;

import java.util.LinkedList;
import java.util.Queue;

public class CheckBSTCompleteness {
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
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) return true;
        boolean nullNodeFound = false;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode node = q.poll();

            if (node == null) {
                nullNodeFound = true;
            } else {
                // found null node but still have non null next
                if (nullNodeFound) {
                    return false;
                }
                q.offer(node.left);
                q.offer(node.right);
            }
        }
        return true;
    }
}