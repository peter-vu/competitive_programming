package leetcode.medium.reverse_integer;

import java.util.Objects;
import java.util.Scanner;

public class ReverseInteger {
    public static void main(String... args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        Solution solution = new Solution();
        System.out.println(solution.reverse(x));
    }
}

class Solution {
    private static final String MAX_POS_INTEGER_STR = "2147483648";
    private static final String MIN_NEG_INTEGER_STR = "2147483647";

    public int reverse(int x) {
        boolean isPositive = true;
        String xStr = String.valueOf(x);
        if (xStr.startsWith("-")) {
            isPositive = false;
        }
        if (isPositive) {
            return processPositive(xStr);
        } else {
            return processNegative(xStr);
        }
    }

    public int processPositive(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        if (sb.length() > MAX_POS_INTEGER_STR.length()
                || (sb.length() == MAX_POS_INTEGER_STR.length() && sb.toString().compareTo(MAX_POS_INTEGER_STR) > 0)
        ) {
            return 0;
        } else return (Integer.parseInt(sb.toString()));
    }

    public int processNegative(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i > 0; i--) {
            sb.append(s.charAt(i));
        }
        if (sb.length() > MIN_NEG_INTEGER_STR.length()
                || (sb.length() == MIN_NEG_INTEGER_STR.length() && sb.toString().compareTo(MIN_NEG_INTEGER_STR) > 0)) {
            return 0;
        } else {
            return Integer.parseInt("-" + sb.toString());
        }

    }
}

class Test {
    private String name;

    public Test setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Test setAddress(String address) {
        this.address = address;
        return this;
    }

    private String address;

    public Test(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Test test)) return false;
        return getName().equals(test.getName()) && getAddress().equals(test.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAddress());
    }

    @Override
    public String toString() {
        return "Test{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}