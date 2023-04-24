package leetcode.daily.d202304.d230412;

import java.util.LinkedList;

public class SimplifyPath {
}

class Solution {
    public String simplifyPath(String path) {

        // Initialize a stack
        LinkedList<String> stack = new LinkedList<String>();
        String[] components = path.split("/");

        // Split the input string on "/" as the delimiter
        // and process each portion one by one
        for (String directory : components) {

            // A no-op for a "." or an empty string
            if (directory.equals(".") || directory.isEmpty()) {
                continue;
            } else if (directory.equals("..")) {

                // If the current component is a "..", then
                // we pop an entry from the stack if it's non-empty
                if (!stack.isEmpty()) {
                    stack.removeLast();
                }
            } else {

                // Finally, a legitimate directory name, so we add it
                // to our stack
                stack.addLast(directory);
            }
        }

        // Stich together all the directory names together
        StringBuilder result = new StringBuilder();
        for (String dir : stack) {
            result.append("/");
            result.append(dir);
        }

        return result.length() > 0 ? result.toString() : "/" ;
    }
}