package leetcode.daily.d232305.d22;

import java.util.*;

public class TopKFrequency {
}

class Solution {

    public int[] topKFrequent(int[] nums, int k) {
        if (k == nums.length) {
            return nums;
        }

        int MIN = 10_000;
        for(int i = 0; i < nums.length; i++) {
            nums[i] += MIN;
        }
        int[] frequency = new int[MIN * 2 + 1];
        for(int i = 0; i < nums.length; i++){
            frequency[nums[i]]++;
        }

        Map<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < frequency.length; i++) {
            if(frequency[i] > 0)
                count.put(i, frequency[i]);
        }

        Queue<Integer> heap = new PriorityQueue<>(
                (n1, n2) -> count.get(n1) - count.get(n2));

        for (int n : count.keySet()) {
            heap.add(n);
            if (heap.size() > k) heap.poll();
        }

        int[] top = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            top[i] = heap.poll() - MIN;
        }
        return top;
    }
}