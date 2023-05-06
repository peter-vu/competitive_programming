package leetcode.daily.d202303.d230323;

import util.FastReader;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberOperationMakeNetworkConnected {
    static FastReader reader;
    static int N;
    static int[][] connections;

    public static void main(String[] args) throws Exception {
        setup();
        int T = reader.nextInt();
        for (int t = 0; t < T; t++) {
            N = reader.nextInt();
            connections = new int[N][2];
            int size = reader.nextInt();
            for (int i = 0; i < size; i++) {
                String[] s = reader.next().split(",");
                int from = Integer.parseInt(s[0]);
                int to = Integer.parseInt(s[1]);
                connections[i][0] = from;
                connections[i][1] = to;
            }
            Solution solution = new Solution();
            System.out.println(solution.makeConnected(N, connections));
        }
    }

    static void setup() throws Exception {
        System.setIn(new FileInputStream("src/main/java/leetcode/daily/d230323/input.txt"));
        reader = new FastReader();
    }
}

class Solution {
    int n;
    int[][] connections;

    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) return -1;
        this.n = n;
        this.connections = connections;
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int[] connection : connections) {
            adjList.computeIfAbsent(connection[0], k -> new ArrayList<>()).add(connection[1]);
            adjList.computeIfAbsent(connection[1], k -> new ArrayList<>()).add(connection[0]);
        }

        int components = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                components++;
                dfs(i, adjList, visited);
            }
        }
        return components - 1;
    }

    void dfs(int node, Map<Integer, List<Integer>> adjList, boolean[] visited) {
        visited[node] = true;
        if (!adjList.containsKey(node)) return;
        for (int next : adjList.get(node)) {
            if (!visited[next]) {
                dfs(next, adjList, visited);
            }
        }
    }
}
