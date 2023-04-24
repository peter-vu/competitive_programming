package leetcode.daily.d202303.d230322;

import util.FastReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MinimumScoreBetweenCities {
    static FastReader reader;
    static int n;
    static int[][] roads;

    public static void main(String[] args) throws Exception {
        setup();
        int T = reader.nextInt();
        for (int t = 0; t < T; t++) {
            n = reader.nextInt();
            roads = new int[n][n];
            for (int i = 0; i < n; i++) {
                String s = reader.next();
                String[] split = s.split(",");
                int from = Integer.parseInt(split[0]);
                int to = Integer.parseInt(split[1]);
                int weight = Integer.parseInt(split[2]);
                roads[from - 1][to - 1] = weight;
                roads[to - 1][from - 1] = weight;
            }
            Solution solution = new Solution();
            int road = solution.minScore(n, roads);
            System.out.println(road);
        }
    }

    static void setup() throws FileNotFoundException {
        System.setIn(new FileInputStream("src/main/java/leetcode/daily/d230322/input.txt"));
        reader = new FastReader();
    }
}

class UnionFind {
    int[] parent;
    int[] rank;

    public UnionFind(int size) {
        parent = new int[size];
        for (int i = 0; i < size; i++)
            parent[i] = i;
        rank = new int[size];
    }

    public int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    public void union_set(int x, int y) {
        int xset = find(x), yset = find(y);
        if (xset == yset) {
            return;
        } else if (rank[xset] < rank[yset]) {
            parent[xset] = yset;
        } else if (rank[xset] > rank[yset]) {
            parent[yset] = xset;
        } else {
            parent[yset] = xset;
            rank[xset]++;
        }
    }
}

class Solution {
    public int minScore(int n, int[][] roads) {
        UnionFind dsu = new UnionFind(n + 1);
        int answer = Integer.MAX_VALUE;

        for (int[] road : roads) {
            dsu.union_set(road[0], road[1]);
        }

        for (int[] road : roads) {
            if (dsu.find(1) == dsu.find(road[0])) {
                answer = Math.min(answer, road[2]);
            }
        }

        return answer;
    }
}