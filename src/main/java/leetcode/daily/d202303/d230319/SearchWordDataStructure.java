package leetcode.daily.d202303.d230319;

import util.FastReader;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class SearchWordDataStructure {
    public static void main(String[] args) throws Exception {
        input();
    }

    static void input() throws Exception {
        System.setIn(new FileInputStream("src/main/java/leetcode/daily/d230319/input.txt"));
        FastReader reader = new FastReader();
    }
}

class WordDictionary {
    TrieNode trie;

    public WordDictionary() {
        trie = new TrieNode();
    }

    /**
     * Add word in trie structure
     * @param word
     */
    public void addWord(String word) {
        TrieNode node = trie;

        for (char ch : word.toCharArray()) {
            if (!node.children.containsKey(ch)) {
                node.children.put(ch, new TrieNode());
            }
            node = node.children.get(ch);
        }
        node.word = true;
    }

    boolean searchInNode(String word, TrieNode node) {
        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if(!node.children.containsKey(ch)) {
                // if it is .
                if (ch == '.') {
                    for(char x: node.children.keySet()) {
                        TrieNode child = node.children.get(x);
                        if (searchInNode(word.substring(i+1), child)) {
                            return true;
                        }
                    }
                }
                return false;
            }
            else {
                node = node.children.get(ch);
            }
        }
        return node.word;
    }

    public boolean search(String word) {
        return searchInNode(word, trie);
    }


}

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean word = false;
}