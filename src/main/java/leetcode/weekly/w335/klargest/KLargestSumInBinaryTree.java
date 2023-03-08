package leetcode.weekly.w335.klargest;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KLargestSumInBinaryTree {
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
    Map<Integer, Long> levelToSumMap;

    public long kthLargestLevelSum(TreeNode root, int k) {
        Comparator<Long> compare = Comparator.comparing(Long::longValue).reversed();
        levelToSumMap = new HashMap<>();
        compute(root, 1);
        List<Long> sumList = levelToSumMap.values().stream().sorted(compare)
        .collect(Collectors.toList());
        if (sumList.size() < k) return -1;
        return sumList.get(k - 1);
    }

    void compute(TreeNode root, int level) {
        if (root != null) {
            Long currSum = levelToSumMap.getOrDefault(level, 0L);
            currSum += root.val;
            levelToSumMap.put(level, currSum);
            if (root.left != null) compute(root.left, level + 1);
            if (root.right != null) compute(root.right, level + 1);
        }
    }
}