package leetcode.daily.d202303.d230117;

import java.util.LinkedList;
import java.util.List;

public class MinDistanceBetweenBSTNodes {
}

class Solution {
    List<Integer> list = new LinkedList<>();

    public int minDiffInBST(TreeNode root) {
        dfs(root);
        int min = 10001;
        for (int i = 0; i < list.size() - 1; i++) {
            min = Math.min(min, list.get(i + 1) - list.get(i));
        }
        return min;
    }

    void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        list.add(root.val);
        dfs(root.right);
    }
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
