package leetcode.daily.d202304.d230426;

public class AddDigits {
}

class Solution {
    public int addDigits(int num) {
        while(num > 9) {
            num = sum(num);
        }
        return num;
    }

    int sum(int num) {
        int cnt = 0;
        while(num > 0) {
            cnt += num%10;
            num/=10;
        }
        return cnt;
    }
}