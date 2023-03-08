package leetcode.daily.d230308;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class KokoEatsBananas {
    static int[] piles;

    static int h;

    static long result;

    public static void main(String[] args) throws FileNotFoundException {
        input();
        Solution s = new Solution();
        result = s.minEatingSpeed(piles, h);
        output();
    }

    static void input() throws FileNotFoundException {
        System.setIn(new FileInputStream("src/main/java/leetcode/daily/d230308/input.txt"));
        FastReader reader = new FastReader();
        String s = reader.next();
        String[] split = s.split(",");
        piles = new int[split.length];
        for (int i = 0; i < piles.length; i++) {
            piles[i] = Integer.parseInt(split[i]);
        }
        h = reader.nextInt();
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
    public int minEatingSpeed(int[] piles, int h) {
        int maxBananas = Arrays.stream(piles).max().orElse(0);
        int left = 1;
        int right = maxBananas;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (satisfy(piles, mid, h)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    boolean satisfy(int[] piles, long k, int h) {
        long totalHours = Arrays.stream(piles).mapToLong(b -> (long) Math.ceil((double) b / k)).sum();
        return totalHours <= h;
    }
}