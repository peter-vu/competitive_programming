package leetcode.daily.d230410;

import java.util.LinkedList;

public class ValidParentheses {
    public static void main(String[] args) {
        String s = "()[]{}";
        Solution solution = new Solution();
        System.out.println(solution.isValid(s));
    }
}

class Solution {
    // valid if (), {} and []
    public boolean isValid(String s) {
        char[] ch = new char[s.length()];
        int j = 0;
        for(int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            if(temp == '(' || temp == '{' || temp == '[') {
                ch[j++] = temp;
            }
            else if (temp == ')') {
                if (ch[j] == '(') {
                    j--;
                }
                else {
                    return false;
                }
            }
            else if (temp == ']') {
                if (ch[j] == '[') {
                    j--;
                }
                else {
                    return false;
                }
            } else if (temp == '}') {
                if (ch[j] == '{') {
                    j--;
                }
                else {
                    return false;
                }
            }
        }
        return true;
    }
}