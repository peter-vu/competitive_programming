package leetcode.weekly.w335;

import java.io.*;
import java.util.StringTokenizer;

public class PassThePillow {
    static int n;
    static int time;

    static int result;
    public static void main(String[] args) throws FileNotFoundException {
        input();
        Solution s = new Solution();
        result = s.passThePillow(n, time);
        output();
    }
    static void output() {
        System.out.println(result);
    }

    static void input() throws FileNotFoundException {
        System.setIn(new FileInputStream("src/main/java/leetcode/weekly/w335/input.txt"));
        FastReader reader = new FastReader();
        n = reader.nextInt();
        time = reader.nextInt();
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
    public int passThePillow(int n, int time) {
        time = time % (2 * n - 2) + 1;
        if (time <= n) return time;
        else {
            return 2 * n - time;
        }
    }
}