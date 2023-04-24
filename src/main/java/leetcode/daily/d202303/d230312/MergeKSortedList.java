package leetcode.daily.d202303.d230312;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MergeKSortedList {
    static ListNode[] lists;
    static ListNode result;

    public static void main(String[] args) throws Exception {
        input();
        Solution s = new Solution();
        result = s.mergeKLists(lists);
        output();
    }

    static void input() throws Exception {
        System.setIn(new FileInputStream("src/main/java/leetcode/daily/d230312/input.txt"));
        FastReader reader = new FastReader();
        int lines = reader.nextInt();
        List<ListNode> nodes = new ArrayList<>();
        for (int i = 0; i < lines; i++) {
            String s = reader.next();
            ListNode temp = buildListNode(s);
            nodes.add(temp);
        }

        lists = nodes.stream().toArray(ListNode[]::new);
    }

    private static ListNode buildListNode(String s) {
        ListNode head = null;
        ListNode curr = null;
        String[] split = s.split(",");
        for (int i = 0; i < split.length; i++) {
            int val = Integer.parseInt(split[i]);
            ListNode node = new ListNode(val);
            if (head == null) {
                head = node;
                curr = head;
            } else {
                curr.next = node;
                curr = curr.next;
            }
        }
        return head;
    }

    static void output() {
        ListNode curr = result;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
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

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        // [0] = value, [1] = index in list
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) return o1[0] - o2[0];
                return o1[1] - o2[1];
            }
        });
        // enqueue
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null)
                pq.offer(new int[]{lists[i].val, i});
        }
        // now just loop
        ListNode headNode = null;
        ListNode curNode = null;
        while (!pq.isEmpty()) {
            // poll
            int[] curr = pq.poll();
            int val = curr[0];
            int idx = curr[1];
            ListNode node = new ListNode(val);
            if (headNode == null) {
                headNode = node;
                curNode = headNode;
            } else {
                curNode.next = node;
                curNode = curNode.next;
            }
            //
            lists[idx] = lists[idx].next;
            if (lists[idx] != null) {
                pq.offer(new int[]{lists[idx].val, idx});
            }
        }
        return headNode;
    }
}