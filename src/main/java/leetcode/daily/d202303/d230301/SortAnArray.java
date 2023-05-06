package leetcode.daily.d202303.d230301;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class SortAnArray {
    static int[] num;

    public static void main(String[] args) throws FileNotFoundException {
        input();
        Solution solution = new Solution();
        long start = System.nanoTime();
        solution.sortArray(num);
        output();
        long end = System.nanoTime();
        System.out.println("Execution time: " + (end - start) / 1_000_000 + " ms.");
    }

    static void output() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < num.length; i++) {
            sb.append(num[i]);
            if (i < num.length - 1) sb.append(",");
        }
        sb.append("]");
        System.out.println(sb.toString());
    }

    static void input() throws FileNotFoundException {
        System.setIn(new FileInputStream("src/main/java/leetcode/daily/d230301/input.txt"));
        FastReader fastReader = new FastReader();
        String s = fastReader.next();
        String[] split = s.split(",");
        num = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            num[i] = Integer.parseInt(split[i]);
        }
    }
}

class Solution {
    public int[] sortArray(int[] nums) {
        countingSort(nums);
        return nums;
    }

    private void countingSort(int[] arr) {
        // Create the counting hash map.
        HashMap<Integer, Integer> counts = new HashMap<>();
        int minVal = arr[0], maxVal = arr[0];

        // Find the minimum and maximum values in the array,
        // and update its count in the hash map.
        for (int i = 0; i < arr.length; i++) {
            minVal = Math.min(minVal, arr[i]);
            maxVal = Math.max(maxVal, arr[i]);
            counts.put(arr[i], counts.getOrDefault(arr[i], 0) + 1);
        }

        int index = 0;
        // Place each element in its correct position in the array.
        for (int val = minVal; val <= maxVal; ++val) {
            // Append all 'val's together if they exist.
            int n = counts.getOrDefault(val, 0);
            while (n-- > 0) {
                arr[index++] = val;
            }
        }
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