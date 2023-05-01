package leetcode.daily.d232305.d01;

public class AverageSalaryExcludeMinMax {

}

class Solution {
    public double average(int[] salary) {
        int min = 1_000_001;
        int max = 0;
        int sum = 0;
        for (int i = 0; i < salary.length; i++) {
            min = Math.min(min, salary[i]);
            max = Math.max(max, salary[i]);
            sum += salary[i];
        }

        return (double) (sum - min - max) / (salary.length - 2);
    }
}
