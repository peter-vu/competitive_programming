package leetcode.daily.d202303.d230307;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MinimumTimeToCompleteTrips {
    static int[] time;

    static int totalTrips;

    static long result;

    public static void main(String[] args) throws FileNotFoundException {
        input();
        Solution s = new Solution();
        result = s.minimumTime(time, totalTrips);
        output();
    }

    static void input() throws FileNotFoundException {
        System.setIn(new FileInputStream("src/main/java/leetcode/daily/d230307/input.txt"));
        FastReader reader = new FastReader();
        String s = reader.next();
        String[] split = s.split(",");
        time = new int[split.length];
        for (int i = 0; i < time.length; i++) {
            time[i] = Integer.parseInt(split[i]);
        }
        totalTrips = reader.nextInt();
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
    public long minimumTime(int[] time, int totalTrips) {
        int maxTime = Arrays.stream(time).max().orElse(0);
        long left = 1;
        long right = (long) maxTime * totalTrips;
        while (left < right) {
            long mid = (left + right) / 2;
            if (timeEnough(time, mid, totalTrips)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    boolean timeEnough(int[] time, long givenTime, int totalTrips) {
        return Arrays.stream(time).mapToLong(t -> t).map(t -> givenTime / t).sum() >= totalTrips;
    }
}
