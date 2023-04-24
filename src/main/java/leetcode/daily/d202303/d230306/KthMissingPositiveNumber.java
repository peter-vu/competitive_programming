package leetcode.daily.d202303.d230306;

import java.io.*;
import java.util.StringTokenizer;

public class KthMissingPositiveNumber {
    static int[] arr;

    static int k;

    static int result;

    public static void main(String[] args) throws FileNotFoundException {
        input();
        Solution s = new Solution();
        result = s.findKthPositive(arr, k);
        output();
    }

    static void input() throws FileNotFoundException {
        System.setIn(new FileInputStream("src/main/java/leetcode/daily/d230306/input.txt"));
        FastReader reader = new FastReader();
        String s = reader.next();
        String[] split = s.split(",");
        arr = new int[split.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(split[i]);
        }
        k = reader.nextInt();
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
    public int findKthPositive(int[] arr, int k) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int pivot = left + (right - left) / 2;
            // continue to search on the right.
            if (arr[pivot] - pivot - 1 < k) {
                left = pivot + 1;
                // Otherwise, go left.
            } else {
                right = pivot - 1;
            }
        }
        return left + k;
    }
}