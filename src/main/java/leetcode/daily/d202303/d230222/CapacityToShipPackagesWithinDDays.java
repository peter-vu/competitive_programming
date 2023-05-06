package leetcode.daily.d202303.d230222;

import java.io.*;
import java.util.StringTokenizer;

public class CapacityToShipPackagesWithinDDays {
    static int[] weights;
    static int days;
    public static void main(String[] args) throws FileNotFoundException {
        input();
        Solution s = new Solution();
        int cnt = s.shipWithinDays(weights, days);
        output(cnt);
    }

    static void input() throws FileNotFoundException {
        System.setIn(new FileInputStream("src/main/java/leetcode/daily/d230222/input.txt"));
        FastReader reader = new FastReader();
        String s = reader.next();
        String[] split = s.split(",");
        weights = new int[split.length];
        for(int i = 0; i < split.length; i++) {
            weights[i] = Integer.parseInt(split[i]);
        }
        s = reader.next();
        days = Integer.parseInt(s);
    }

    static void output(int cnt) {
        System.out.println(cnt);
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
    public int shipWithinDays(int[] weights, int days) {
        int left = 0;
        int right = 0;
        for (int i : weights) {
            left = Math.max(left, i);
            right += i;
        }
        int mid;
        int ans = right;
        while (left <= right) {
            mid = (left + right) / 2;
            if (check(weights, days, mid)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    boolean check(int[] weights, int days, int capacity) {
        int requiredDays = 1;
        int currWeight = 0;
        for (int i : weights) {
            if (currWeight + i > capacity) {
                requiredDays++;
                currWeight = 0;
            }
            currWeight += i;
        }
        if (requiredDays > days) return false;
        return true;
    }
}