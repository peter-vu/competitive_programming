package leetcode.weekly.w1055;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ShortestWayFormString {
    static String source;
    static String target;

    static int result;
    public static void main(String[] args) throws Exception {
        input();
        Solution s = new Solution();
        result = s.shortestWay(source, target);
        output();
    }

    static void output() {
        System.out.println(result);
    }

    static void input() throws  Exception {
        System.setIn(new FileInputStream("src/main/java/leetcode/weekly/w1055/input.txt"));
        FastReader reader = new FastReader();
        source = reader.next();
        target = reader.next();
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
    public int shortestWay(String source, String target) {

        // Next occurrence of a character after a given index
        int[][] nextOccurrence = new int[source.length()][26];

        // Base Case
        for (int c = 0; c < 26; c++) {
            nextOccurrence[source.length() - 1][c] = -1;
        }
        nextOccurrence[source.length() - 1][source.charAt(source.length() - 1) - 'a'] = source.length() - 1;

        // Fill using recurrence relation
        for (int idx = source.length() - 2; idx >= 0; idx--) {
            for (int c = 0; c < 26; c++) {
                nextOccurrence[idx][c] = nextOccurrence[idx + 1][c];
            }
            nextOccurrence[idx][source.charAt(idx) - 'a'] = idx;
        }

        // Pointer to the current index in source
        int sourceIterator = 0;

        // Number of times we need to iterate through source
        int count = 1;

        // Find all characters of target in source
        for (char c : target.toCharArray()) {

            // If the character is not present in source
            if (nextOccurrence[0][c - 'a'] == -1) {
                return -1;
            }

            // If we have reached the end of source, or character is not in
            // source after source_iterator, loop back to beginning
            if (sourceIterator == source.length() || nextOccurrence[sourceIterator][c - 'a'] == -1) {
                count++;
                sourceIterator = 0;
            }

            // Next occurrence of character in source after source_iterator
            sourceIterator = nextOccurrence[sourceIterator][c - 'a'] + 1;
        }

        // Return the number of times we need to iterate through source
        return count;
    }
}