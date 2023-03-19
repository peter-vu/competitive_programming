package leetcode.daily.d200213.addbinary;

public class AddBinary {
    public static void main(String[] args) {
        String a = "10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101";
        String b = "110101001011101110001111100110001010100001101011101010000011011011001011101111001100000011011110011";
        Solution solution = new Solution();
        System.out.println(solution.addBinary(a, b));
    }
}

class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        // makes a as longest string
        if (a.length() < b.length()) {
            String c = a;
            a = b;
            b = c;
        }
        int lengthDiff = a.length() - b.length();
        b = "0".repeat(lengthDiff) + b;
        char remainder = '0';
        for (int i = a.length() - 1; i >= 0; i--) {
            String sum = addBinarySingle(a.charAt(i), b.charAt(i), remainder);
            remainder = sum.charAt(0);
            sb.append(sum.charAt(1));
        }
        if (remainder == '1') sb.append(remainder);
        return sb.reverse().toString();
    }

    private String addBinarySingle(char a, char b, char c) {
        String s = "" + a + b + c;
        return switch (s) {
            case "000" -> "00";
            case "001", "010", "100" -> "01";
            case "110", "101", "011" -> "10";
            case "111" -> "11";
            default -> "";
        };
    }
}