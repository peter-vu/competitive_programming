package leetcode.daily.d202303.d230301.quicksort;

import java.io.*;
import java.util.StringTokenizer;

public class SortAnArrayQuickSort {
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
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    void quickSort(int[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);
            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    int partition(int[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = begin - 1;
        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, end);

        return i + 1;
    }

    void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
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