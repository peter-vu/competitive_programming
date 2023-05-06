package leetcode.daily.d202304.d230413;

import java.util.LinkedList;

public class ValidateStackSequence {
}

class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int N = pushed.length;
        LinkedList<Integer> stack = new LinkedList<>();

        int j = 0;
        for (int x: pushed) {
            stack.addFirst(x);
            while (!stack.isEmpty() && j < N && stack.getFirst().equals(popped[j])) {
                stack.removeFirst();
                j++;
            }
        }
        return j == N;
    }
}