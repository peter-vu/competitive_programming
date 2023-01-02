package leetcode.easy.detect_capital;

import java.util.Locale;
import java.util.Scanner;

public class DetectCapital {
    public static void main(String[] args) throws Exception {
        // input
        Scanner sc = new Scanner(System.in);
        String word = sc.next();
        Solution solution = new Solution();
        System.out.println(solution.detectCapitalUse(word));
    }

}

class Solution {
    public boolean detectCapitalUse(String word) {
        String upperCase = word.toUpperCase();
        String lowerCase = word.toLowerCase();
        String firstUpperLetter = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();

//        System.out.println("upperCase: " + upperCase);
//        System.out.println("lowerCase: " + lowerCase);
//        System.out.println("firstUpperLetter: " + firstUpperLetter);

        return upperCase.equals(word) || lowerCase.equals(word) || firstUpperLetter.equals(word);
    }
}