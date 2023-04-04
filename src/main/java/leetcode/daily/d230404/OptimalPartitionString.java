package leetcode.daily.d230404;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OptimalPartitionString {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.partitionString("abacaba"));
    }
}

class Solution {
    public int partitionString(String s) {
        int cnt = 0;
        Set<Integer> map = new HashSet<>();
        for(int i = 0; i < s.length(); i++) {
            int cur = s.charAt(i);
            if(map.contains(cur)) {
                cnt++;
                map.clear();
            }
            map.add(cur);
        }
        if(!map.isEmpty()) {
            cnt++;
        }
        return cnt;
    }
}