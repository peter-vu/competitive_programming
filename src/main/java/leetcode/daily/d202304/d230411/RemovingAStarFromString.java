package leetcode.daily.d202304.d230411;

public class RemovingAStarFromString {
    public static void main(String[] args) {
        String s = "";
    }
}

class Solution {
    public String removeStars(String s) {
        char[] ch = new char[s.length()];
        int j = 0;

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '*') {
                j--;
            } else {
                ch[j++] = c;
            }
        }

        StringBuilder ans = new StringBuilder();
        for(int i = 0; i < j; i++) {
            ans.append(ch[i]);
        }
        return ans.toString();
    }
}