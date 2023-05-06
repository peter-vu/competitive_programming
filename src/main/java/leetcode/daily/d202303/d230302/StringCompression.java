package leetcode.daily.d202303.d230302;

import java.io.*;
import java.util.StringTokenizer;

public class StringCompression {
    static char[] chars;

    public static void main(String[] args) throws FileNotFoundException {
        input();
        Solution solution = new Solution();
        int result = solution.compress(chars);
        output(result);
    }

    private static void output(int result) throws FileNotFoundException {
        System.out.println(result);
    }

    private static void input() throws FileNotFoundException {
        System.setIn(new FileInputStream("src/main/java/leetcode/daily/d230302/input.txt"));
        FastReader fastReader = new FastReader();
        String s = fastReader.next();
        chars = s.toCharArray();
    }
}

class Solution {
    public int compress(char[] chars) {
        StringBuilder sb = new StringBuilder();
        char prev = chars[0];
        char curr;
        int cnt = 1;
        sb.append(prev);
        for (int i = 1; i < chars.length; i++) {
            curr = chars[i];
            if (curr == prev) {
                cnt++;
            } else {
                if (cnt > 1) sb.append(cnt);
                // reset
                prev = curr;
                sb.append(prev);
                cnt = 1;
            }
        }
        if (cnt > 1) sb.append(cnt);
        char[] newChars = sb.toString().toCharArray();
        for(int i = 0; i < newChars.length; i++){
            chars[i] = newChars[i];
        }
        return sb.toString().length();
    }
}

class FastReader {
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