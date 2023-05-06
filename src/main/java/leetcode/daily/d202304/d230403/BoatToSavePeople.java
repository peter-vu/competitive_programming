package leetcode.daily.d202304.d230403;

import java.util.Arrays;

public class BoatToSavePeople {
    public static void main(String[] args) {

    }
}

class Solution {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int i = 0, j = people.length - 1;
        int ans = 0;

        while (i <= j) {
            ans++;
            if (people[i] + people[j] <= limit)
                i++;
            j--;
        }

        return ans;
    }
}