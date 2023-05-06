package leetcode.daily.d202304.d230401;

public class BinarySearch {
}

class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] == target) return mid;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (nums[left] == target) {
            return left;
        }
        return -1;
    }
}
