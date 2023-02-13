package leetcode.daily.p230213.countoddnumber;

public class CoundOddNumbers {
    public static void main(String[] args) {
        int low = 8;
        int high = 10;
        Solution solution = new Solution();
        long result = solution.countOdds(low, high);
        System.out.println(result);

    }
}

class Solution {
    public int countOdds(int low, int high) {
        int result = 0;
        result = (high - low) / 2;
        if (low % 2 == 1 || high % 2 == 1) result ++;
        return result;
    }
}