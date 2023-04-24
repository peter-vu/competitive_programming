package leetcode.daily.d202303.d230219;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BTSZigZag {
}

class Solution {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }

        List<List<Integer>> results = new ArrayList<>();
        LinkedList<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.addLast(root);
        nodeQueue.addLast(null);

        LinkedList<Integer> level_list = new LinkedList<>();
        boolean is_order_left = true;

        while (!nodeQueue.isEmpty()) {
            TreeNode currNode = nodeQueue.pollFirst();
            if (currNode != null) {
                if (is_order_left) {
                    level_list.addLast(currNode.val);
                } else {
                    level_list.addFirst(currNode.val);
                }

                if (currNode.left != null) {
                    nodeQueue.addLast(currNode.left);
                }
                if (currNode.right != null) {
                    nodeQueue.addLast(currNode.right);
                }
            } else {
                results.add(level_list);
                level_list = new LinkedList<>();
                if (!nodeQueue.isEmpty()) {
                    nodeQueue.addLast(null);
                }
                is_order_left = !is_order_left;
            }
        }
        return results;
    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
