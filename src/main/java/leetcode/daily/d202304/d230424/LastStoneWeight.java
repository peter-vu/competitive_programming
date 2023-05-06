package leetcode.daily.d202304.d230424;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LastStoneWeight {
    public static void main(String[] args) {
        int[] stones = {2,7,4,1,8,1};
        Solution solution = new Solution();
        System.out.println(solution.lastStoneWeight(stones));
    }
}

class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for(int i = 0; i < stones.length; i++) {
            pq.offer(stones[i]);
        }
        while(pq.size() >= 2) {
            int x = pq.poll();
            int y = pq.poll();
            if (x != y) {
                pq.offer(Math.abs(x - y));
            }
        }
        if (!pq.isEmpty()) {
            return pq.poll();
        }
        return 0;
    }
}