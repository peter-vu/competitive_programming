package leetcode.medium.zizag_conversion;

public class ZigZagConversion {
    public static void main(String[] args) throws Exception {
        String words = "AB";
        int numRows = 1;
        Solution solution = new Solution();
        String result = solution.convert(words, numRows);
        System.out.println(result);
    }
}

class Solution {
    private int currIdx = 0;
    private static final int UP = 1;
    private static final int DOWN = 0;
    private int direction = UP;
    public String convert(String s, int numRows) {
        StringBuilder[] sb = new StringBuilder[numRows];
        for(int i = 0; i < numRows; i++) {
            sb[i] = new StringBuilder();
        }
        for(int i = 0; i < s.length(); i++) {
            sb[currIdx].append(s.charAt(i));
            updateState(numRows);
        }
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < numRows; i++) {
            result.append(sb[i].toString());
        }
        return result.toString();
    }

    private void updateState(int numRows) {
        // handle special case
        if(numRows == 1) return;
        switch (direction) {
            case UP -> {
                if (currIdx < numRows - 1) {
                    currIdx++;
                } else {
                    currIdx--;
                    direction = DOWN;
                }
            }
            case DOWN -> {
                if (currIdx > 0) {
                    currIdx--;
                } else {
                    currIdx++;
                    direction = UP;
                }
            }
            default -> { }
        }
    }
}