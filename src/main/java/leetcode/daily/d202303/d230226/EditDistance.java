package leetcode.daily.d202303.d230226;

import java.io.*;
import java.util.StringTokenizer;

public class EditDistance {
    static String word1;
    static String word2;

    public static void main(String[] args) throws FileNotFoundException {
        input();
        Solution solution = new Solution();
        int result = solution.minDistance(word1, word2);
        output(result);
    }

    static void input() throws FileNotFoundException {
        System.setIn(new FileInputStream("src/main/java/leetcode/daily/d230226/input.txt"));
        FastReader reader = new FastReader();
        word1 = reader.next();
        word2 = reader.next();
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
    public int minDistance(String word1, String word2) {
        int length1 = word1.length();
        int length2 = word2.length();
        if (length1 == 0) return length2;
        if (length2 == 0) return length1;
        int[][] dp = new int[length1 + 1][length2 + 1];
        for (int i = 1; i <= length1; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= length2; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j]
                            , Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                }
            }
        }

        return dp[length1][length2];
    }
}

