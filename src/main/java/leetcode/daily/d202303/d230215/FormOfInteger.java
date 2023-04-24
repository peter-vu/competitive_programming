package leetcode.daily.d202303.d230215;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class FormOfInteger {
    public static void main(String[] args) {
        int[] num = {1, 2, 0, 0};
        int k = 34;
        Solution solution = new Solution();
        List<Integer> result = solution.addToArrayForm(num, k);
        print(result);
    }

    private static void print(List<Integer> result) {
        int length = result.size();
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        IntStream.range(0, length).map(x -> result.get(x)).forEach(x -> sb.append(x).append(","));
        sb.replace(sb.length() - 1, sb.length(), "]");
        System.out.println(sb.toString());
    }
}

class Solution {
    public List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> list1 = new ArrayList<>();
        for (int i = 0; i < num.length; i++) {
            list1.add(num[num.length - 1 - i]);
        }
        List<Integer> list2 = convertToArray(k);
        if (list1.size() < list2.size()) {
            List<Integer> temp = list1;
            list1 = list2;
            list2 = temp;
        }
        LinkedList<Integer> result = new LinkedList<>();
        int r = 0;
        for (int i = 0; i < list1.size(); i++) {
            int right = 0;
            if (i < list2.size()) {
                right = list2.get(i);
            }
            int left = list1.get(i);
            int sum = right + left + r;
            if (sum > 9) {
                r = sum / 10;
                sum %= 10;
            } else {
                r = 0;
            }
            result.add(sum);
        }
        if (r > 0) {
            result.add(r);
        }

        List<Integer> result2 = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            result2.add(result.get(result.size() - 1 - i));
        }
        return result2;
    }

    private List<Integer> convertToArray(int k) {
        LinkedList<Integer> result = new LinkedList<>();
        while (k > 0) {
            result.add(k % 10);
            k /= 10;
        }
        return result;
    }

}


class Solution2 {
    public List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> list1 = new ArrayList<>();
        for (int i = 0; i < num.length; i++) {
            list1.add(num[num.length - 1 - i]);
        }
        List<Integer> list2 = convertToArray(k);
        if (list1.size() < list2.size()) {
            List<Integer> temp = list1;
            list1 = list2;
            list2 = temp;
        }
        LinkedList<Integer> result = new LinkedList<>();
        int r = 0;
        for (int i = 0; i < list1.size(); i++) {
            int right = 0;
            if (i < list2.size()) {
                right = list2.get(i);
            }
            int left = list1.get(i);
            int sum = right + left + r;
            if (sum > 9) {
                r = sum / 10;
                sum %= 10;
            } else {
                r = 0;
            }
            result.add(sum);
        }
        if (r > 0) {
            result.add(r);
        }

        List<Integer> result2 = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            result2.add(result.get(result.size() - 1 - i));
        }
        return result2;
    }

    private List<Integer> convertToArray(int k) {
        LinkedList<Integer> result = new LinkedList<>();
        while (k > 0) {
            result.add(k % 10);
            k /= 10;
        }
        return result;
    }

}
