package leetcode.weekly.w422;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class ValidWordSquare {
    static List<String> wordList;
    static boolean result;
    public static void main(String[] args) throws Exception {
        input();
        Solution s = new Solution();
        result = s.validWordSquare(wordList);
        output();
    }

    static void output() {
        System.out.println(result);
    }

    static void input() throws FileNotFoundException {
        System.setIn(new FileInputStream("src/main/java/leetcode/weekly/w422/input.txt"));
        FastReader reader = new FastReader();
        wordList = new ArrayList<>();
        int k = reader.nextInt();
        for(int i = 0; i < k; i++) {
            wordList.add(reader.next());
        }
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}

class Solution {
    public boolean validWordSquare(List<String> words) {
        // quick check
        int maxWordLength = words.stream().map(String::length).max(Integer::max).orElse(0);
        if(maxWordLength != words.size()) return false;

        List<String> verticalWordList = buildVerticalWordList(words);
        for(int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            String vWord = verticalWordList.get(i);
            if(!word.equals(vWord)) {
                return false;
            }
        }
        return true;
    }

    List<String> buildVerticalWordList(List<String> words) {
        int length = words.size();
        List<String> vWords = new ArrayList<>();
        for(int i = 0; i < length; i++) {
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < words.size(); j++) {
                String currWords = words.get(j);
                if(currWords.length() > i) {
                    sb.append(currWords.charAt(i));
                }
            }
            vWords.add(sb.toString());
        }
        return vWords;
    }
}