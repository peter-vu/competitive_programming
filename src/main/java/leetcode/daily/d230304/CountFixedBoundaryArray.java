package leetcode.daily.d230304;

import java.io.*;
import java.util.StringTokenizer;

public class CountFixedBoundaryArray {
    static int[] nums;
    static int minK;
    static int maxK;

    static long result;

    public static void main(String[] args) throws FileNotFoundException {
        input();
        Solution s = new Solution();
        result = s.countSubarrays(nums, minK, maxK);
        output();
    }

    static void input() throws FileNotFoundException {
        System.setIn(new FileInputStream("src/main/java/leetcode/daily/d230304/input.txt"));
        FastReader reader = new FastReader();
        String s = reader.next();
        String[] split = s.split(",");
        nums = new int[split.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(split[i]);
        }
        minK = reader.nextInt();
        maxK = reader.nextInt();
    }

    static void output() {
        System.out.println(result);
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

class Solution {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        long answer = 0L;
        int minPosition = -1, maxPosition = -1, leftBound = -1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < minK || nums[i] > maxK) {
                leftBound = i;
            }
            if (nums[i] == minK) {
                minPosition = i;
            }
            if (nums[i] == maxK) {
                maxPosition = i;
            }
            answer += Math.max(0, Math.min(maxPosition, minPosition) - leftBound);

        }
        return answer;
    }
}