package leetcode.daily.d232305.d03;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class FindDifferenceBetweenTwoArray {
}

class Solution {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            set1.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            set2.add(nums2[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            set1.remove(nums2[i]);
        }
        for (int i = 0; i < nums1.length; i++) {
            set2.remove(nums1[i]);
        }

        List<Integer> list1 = new LinkedList<>();
        List<Integer> list2 = new LinkedList<>();

        Iterator<Integer> iterator1 = set1.iterator();
        Iterator<Integer> iterator2 = set2.iterator();
        while (iterator1.hasNext()) list1.add(iterator1.next());
        while (iterator2.hasNext()) list2.add(iterator2.next());


        return Arrays.asList(list1, list2);
    }
}

class Solution3 {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            set1.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            set2.add(nums2[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            set1.remove(nums2[i]);
        }
        for (int i = 0; i < nums1.length; i++) {
            set2.remove(nums1[i]);
        }

        List<Integer> list1 = set1.stream().toList();
        List<Integer> list2 = set2.stream().toList();

        return Arrays.asList(list1, list2);
    }
}

class Solution2 {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Set<Integer> set1 = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        Set<Integer> set2 = Arrays.stream(nums2).boxed().collect(Collectors.toSet());
        List<Integer> list1 = set1.stream().filter(x -> !set2.contains(x)).collect(Collectors.toList());
        List<Integer> list2 = set2.stream().filter(x -> !set1.contains(x)).collect(Collectors.toList());
        return Arrays.asList(list1, list2);
    }
}