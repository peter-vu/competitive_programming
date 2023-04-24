package leetcode.daily.d202303.d230321;

import util.FastReader;

import java.io.FileInputStream;
import java.util.Arrays;

public class NumberOfZeroFilledSubArray {
    static FastReader reader;

    public static void main(String[] args) throws Exception {
        setup();
        Solution solution = new Solution();
        int T = reader.nextInt();
        for (int i = 0; i < T; i++) {
            String s = reader.next();
            int[] nums = buildArray(s);
            long result = solution.zeroFilledSubarray(nums);
            System.out.println(result);
        }
    }

    static int[] buildArray(String s) {
        String[] split = s.split(",");
        int[] num = Arrays.stream(split).mapToInt(Integer::parseInt).toArray();
        return num;
    }

    static void setup() throws Exception {
        System.setIn(new FileInputStream("src/main/java/leetcode/daily/d230321/input.txt"));
        reader = new FastReader();
    }
}


class Solution {
    public long zeroFilledSubarray(int[] nums) {
        int cnt = 0;
        long result = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                cnt++;
            } else {
                result += (long) cnt * (cnt + 1) / 2;
                cnt = 0;
            }
        }
        result += (long) cnt * (cnt + 1) / 2;
        return result;
    }
}
