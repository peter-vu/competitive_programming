package leetcode.daily.d230417;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KidsWithGreatesNumberOfCandies {
}

class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = Arrays.stream(candies).max().orElse(0);
        List<Boolean> result = new ArrayList<>();
        for(int i = 0; i < candies.length; i++) {
            if (candies[i] + extraCandies >= max) {
                result.add(true);
            }
            else {
                result.add(false);
            }
        }

        return result;
    }
}