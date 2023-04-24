package leetcode.daily.d202303.d230311;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ConvertSortedListToBST {
    static ListNode head;
    static TreeNode tree;

    public static void main(String[] args) throws Exception {
        input();
        Solution solution = new Solution();
        tree = solution.sortedListToBST(head);
        output();
    }

    static void input() throws Exception {
        System.setIn(new FileInputStream("src/main/java/leetcode/daily/d230311/input.txt"));
        FastReader reader = new FastReader();
        String s = reader.next();
        String[] split = s.split(",");
        head = null;
        ListNode curr = null;
        for (int i = 0; i < split.length; i++) {
            int val = Integer.parseInt(split[i]);
            ListNode node = new ListNode(val);
            if (head == null) {
                head = node;
                curr = head;
            } else {
                curr.next = node;
                curr = node;
            }
        }
    }

    static void output() {
        StringBuilder sb = new StringBuilder();
        generateOutput(tree, sb);
        System.out.println(sb);
    }

    static void generateOutput(TreeNode node, StringBuilder sb) {
        if (node == null) {
            return;
        };
        generateOutput(node.left, sb);
        generateOutput(node.right, sb);
        sb.append(node.val).append(" ");
    }

    static class FastReader {
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
}

/**
 * Definition for singly-linked list.
 */
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

/**
 * Definition for a binary tree node.
 */
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
    private ListNode head;

    private int findSize(ListNode head) {
        ListNode ptr = head;
        int c = 0;
        while (ptr != null) {
            ptr = ptr.next;
            c++;
        }
        return c;
    }

    private TreeNode convertListToBST(int l, int r) {
        if (l > r) {
            return null;
        }
        int mid = (l + r) / 2;
        TreeNode left = this.convertListToBST(l, mid - 1);
        TreeNode node = new TreeNode(this.head.val);
        node.left = left;
        this.head = this.head.next;

        node.right = this.convertListToBST(mid + 1, r);
        return node;
    }

    public TreeNode sortedListToBST(ListNode head) {
        int size = this.findSize(head);
        this.head = head;
        return convertListToBST(0, size - 1);
    }
}