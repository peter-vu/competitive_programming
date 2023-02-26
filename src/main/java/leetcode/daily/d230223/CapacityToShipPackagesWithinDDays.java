package leetcode.daily.d230223;

public class CapacityToShipPackagesWithinDDays {
    public static void main(String[] args) {
        int[] weight = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int days = 5;
        Solution solution = new Solution();
        System.out.println(solution.shipWithinDays(weight, days));
    }
}

class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int left = 0;
        int right = 0;
        for (int i : weights) {
            left = Math.max(left, i);
            right += i;
        }
        int mid;
        int ans = right;
        while (left <= right) {
            mid = (left + right) / 2;
            if (check(weights, days, mid)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    boolean check(int[] weights, int days, int capacity) {
        int requiredDays = 1;
        int currWeight = 0;
        for (int i : weights) {
            if (currWeight + i > capacity) {
                requiredDays++;
                currWeight = 0;
            }
            currWeight += i;
        }
        if (requiredDays > days) return false;
        return true;
    }
}