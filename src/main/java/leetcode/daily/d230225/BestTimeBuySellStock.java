package leetcode.daily.d230225;

import java.io.*;
import java.util.StringTokenizer;

public class BestTimeBuySellStock {
    static int[] prices;

    public static void main(String[] args) throws FileNotFoundException {
        input();
        Solution solution = new Solution();
        int result = solution.maxProfit(prices);
        output(result);
    }

    static void input() throws FileNotFoundException {
        System.setIn(new FileInputStream("src/main/java/leetcode/daily/d230225/input.txt"));
        FastReader reader = new FastReader();
        String s = reader.next();
        String[] split = s.split(",");
        prices = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            prices[i] = Integer.parseInt(split[i]);
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
    public int maxProfit(int[] prices) {
        int min = 10001;
        int maxProfit = 0;
        for(int i = 0; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - min);
        }
        return maxProfit;
    }

    public int maxProfit3(int[] prices) {
        int[] maxFromRight = new int[prices.length];

        maxFromRight[prices.length - 1] = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0; i--) {
            maxFromRight[i] = Math.max(maxFromRight[i + 1], prices[i]);
        }
        int maxProfit = 0;
        int min = 10001;
        for (int i = 0; i < prices.length - 1; i++) {
            min = Math.min(min, prices[i]);
            int profit = maxFromRight[i + 1] - min;
            maxProfit = Math.max(maxProfit, profit);
        }
        return maxProfit;
    }

    public int maxProfit2(int[] prices) {
        int[] minToLeft = new int[prices.length];
        int[] maxFromRight = new int[prices.length];
        // populate minToLeft
        minToLeft[0] = prices[0];
        for (int i = 1; i < prices.length; i++) {
            minToLeft[i] = Math.min(minToLeft[i - 1], prices[i]);
        }
        maxFromRight[prices.length - 1] = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0; i--) {
            maxFromRight[i] = Math.max(maxFromRight[i + 1], prices[i]);
        }
        int maxProfit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            int profit = maxFromRight[i + 1] - minToLeft[i];
            maxProfit = Math.max(maxProfit, profit);
        }
        return maxProfit;
    }
}