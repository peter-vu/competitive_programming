package leetcode.daily.d230228;

import com.sun.source.tree.Tree;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.out;

public class FindDuplicateTree {
    static TreeNode root;

    public static void main(String[] args) throws Exception {
        input();
        Solution solution = new Solution();
        List<TreeNode> duplicateSubtrees = solution.findDuplicateSubtrees(root);
        output(duplicateSubtrees);
    }

    private static void input() throws Exception {
        System.setIn(new FileInputStream("src/main/java/leetcode/daily/d230228/input.txt"));
        FastReader reader = new FastReader();
        String s = reader.next();
        String[] split = s.split(",");
        int length = split.length;
        root = buildTree(split, 1, length);
    }

    static TreeNode buildTree(String[] split, int idx, int length) {
        out.println("idx: " + idx);

        TreeNode node = null;
        if (idx <= length) {
            String s = split[idx - 1];
            if (!s.equals("null")) {
                node = new TreeNode(Integer.parseInt(s));
                TreeNode left = buildTree(split, idx * 2, length);
                TreeNode right = buildTree(split, idx * 2 + 1, length);
                node.left = left;
                node.right = right;
            }
        }
        return node;
    }

    static void output(List<TreeNode> list) {
        for (TreeNode treeNode : list) {
            out.println(treeNode);
        }
    }
}

class FastReader {
    BufferedReader br;
    StringTokenizer st;

    public FastReader() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }

    public double nextDouble() {
        return Double.parseDouble(next());
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


class Solution {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new LinkedList<>();
        traverse(root, new HashMap<>(), res);
        return res;
    }

    String traverse(TreeNode node, Map<String, Integer> cnt, List<TreeNode> res) {
        if (node == null) return "";
        String representation = "(" + traverse(node.left, cnt, res) + ")" +
                node.val + "(" + traverse(node.right, cnt, res) + ")";
        cnt.put(representation, cnt.getOrDefault(representation, 0) + 1);
        if (cnt.get(representation) == 2) {
            res.add(node);
        }
        return representation;
    }
}