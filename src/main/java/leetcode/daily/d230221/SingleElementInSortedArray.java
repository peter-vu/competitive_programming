package leetcode.daily.d230221;

public class SingleElementInSortedArray {
}

class Solution {
    public int singleNonDuplicate(int[] nums) {
        int length = nums.length;
        return binarySearch(nums, 0, length - 1);
    }

    int binarySearch(int[] nums, int startIndex, int endIndex) {
        if (startIndex == endIndex) return nums[startIndex];
        int midIndex = (endIndex - startIndex) / 2 + startIndex;
        if (midIndex % 2 == 1) midIndex--;
        System.out.println("Search: " + startIndex + " - " + endIndex + ", mid: " + midIndex);
        if (nums[midIndex] == nums[midIndex + 1]) {
            return binarySearch(nums, midIndex + 2, endIndex);
        }
        return binarySearch(nums, startIndex, midIndex);
    }
}