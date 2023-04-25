package leetcode.daily.d202304.d230425;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class SmallestNumberInfiniteSet {
}

class SmallestInfiniteSet {
    Set<Integer> set;
    PriorityQueue<Integer> pq;


    public SmallestInfiniteSet() {
        set = new HashSet<>();
        pq = new PriorityQueue<>(Comparator.naturalOrder());
        for(int i = 1; i <= 1000; i++) {
            set.add(i);
            pq.offer(i);
        }
    }

    public int popSmallest() {
        int n = pq.poll();
        set.remove(n);
        return n;
    }

    public void addBack(int num) {
        if(!set.contains(num)) {
            set.add(num);
            pq.offer(num);
        }
    }
}

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * SmallestInfiniteSet obj = new SmallestInfiniteSet();
 * int param_1 = obj.popSmallest();
 * obj.addBack(num);
 */