package leetcode.daily.d202303.d230220;

public class SearchInsertPosition {
}

class Solution {
    public int searchInsert(int[] nums, int target) {
        int length = nums.length;
        return binarySearch(nums, target, 0, length - 1);
    }

    int binarySearch(int[] nums, int target, int start, int end) {
        if (nums[end] < target) return end + 1;
        else if (end <= start) {
            return end;
        }
        int mid = (end - start) / 2 + start;
        if (nums[mid] == target) return mid;
        else if (nums[mid] > target) {
            return binarySearch(nums, target, 0, mid);
        } else {
            return binarySearch(nums, target, mid + 1, end);
        }
    }
}