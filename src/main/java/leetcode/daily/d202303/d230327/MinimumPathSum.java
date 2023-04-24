package leetcode.daily.d202303.d230327;

public class MinimumPathSum {
}

class Solution {
    int[][] min;
    public int minPathSum(int[][] grid) {
        int height = grid.length;
        int width = grid[0].length;
        min = new int[height][width];
        min[0][0] = grid[0][0];

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if (i > 0 && j > 0) {
                    min[i][j] = Math.min(min[i-1][j], min[i][j-1]) + grid[i][j];
                }
                else if (i > 0) {
                    min[i][j] = min[i-1][j] + grid[i][j];
                }
                else if (j > 0) {
                    min[i][j] = min[i][j-1] + grid[i][j];
                }
            }
        }


        return min[height-1][width-1];
    }
}