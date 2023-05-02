package leetcode.daily.d232305.d02;

import java.util.Arrays;

public class SingOfProductOfAnArray {
}

class Solution {
    public int arraySign(int[] nums) {
        int negativeCnt = 0;
        boolean isZero = false;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                isZero = true;
                break;
            } else if (nums[i] < 0) {
                negativeCnt++;
            }
        }
        if (isZero) return 0;
        if (negativeCnt % 2 == 0) return 1;
        return -1;
    }
}