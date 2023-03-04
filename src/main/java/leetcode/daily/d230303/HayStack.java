package leetcode.daily.d230303;

import java.io.*;
import java.util.StringTokenizer;

public class HayStack {
    static String haystack;
    static String needle;

    public static void main(String[] args) throws FileNotFoundException {
        input();
        Solution solution = new Solution();
        int result = solution.strStr(haystack, needle);
        output(result);
    }

    private static void output(int result) throws FileNotFoundException {
        System.out.println(result);
    }

    private static void input() throws FileNotFoundException {
        System.setIn(new FileInputStream("src/main/java/leetcode/daily/d230303/input.txt"));
        FastReader fastReader = new FastReader();
        haystack = fastReader.next();
        needle = fastReader.next();
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
    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }
}