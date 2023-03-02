package leetcode.daily.d230224;

import leetcode.daily.d230225.BestTimeBuySellStock;

import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MinimizeDeviationInArray {
    static int[] nums;

    public static void main(String[] args) throws FileNotFoundException {
        input();
        Solution solution = new Solution();
        int result = solution.minimumDeviation(nums);
        output(result);
    }

    static void input() throws FileNotFoundException {
        System.setIn(new FileInputStream("src/main/java/leetcode/daily/d230224/input.txt"));
        FastReader reader = new FastReader();
        String s = reader.next();
        String[] split = s.split(",");
        nums = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            nums[i] = Integer.parseInt(split[i]);
        }
    }

    static void output(int result) {
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
    public int minimumDeviation(int[] nums) {
        PriorityQueue<Integer> evens = new PriorityQueue<>(Collections.reverseOrder());
        int minimum = Integer.MAX_VALUE;
        for(int num: nums) {
            if (num %2 ==0) {
                evens.offer(num);
                minimum = Math.min(minimum, num);
            } else {
                evens.offer(num * 2);
                minimum = Math.min(minimum, num * 2);
            }
        }
        int minDeviation = Integer.MAX_VALUE;

        while (!evens.isEmpty()) {
            int currentValue = evens.poll();
            minDeviation = Math.min(minDeviation, currentValue - minimum);
            if (currentValue %2 ==0) {
                evens.offer(currentValue / 2);
                minimum = Math.min(minimum, currentValue / 2);
            } else {
                // if max is odd, break and return
                break;
            }
        }
        return minDeviation;
    }
}