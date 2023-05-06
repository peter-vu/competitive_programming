package leetcode.daily.d202303.d230325;

import java.util.HashMap;
import java.util.Map;

public class CountUnreachablePair {
    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        int n = 7;
        int[][] edges = {{0,2},{0,5},{2,4},{1,6},{5,4}};
        long result = solution.countPairs(n, edges);
        System.out.println(result);
    }
}

class UnionFind {
    int[] parent;
    int[] rank;

    public UnionFind(int size) {
        rank = new int[size];
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int xroot = find(x);
        int yroot = find(y);
        if (xroot != yroot) {
            if (rank[xroot] < rank[yroot]) {
                parent[xroot] = yroot;
            } else if (rank[xroot] > rank[yroot]) {
                parent[yroot] = xroot;
            }
            // increase rank of 1 side
            else {
                parent[yroot] = xroot;
                rank[xroot]++;
            }
        }
    }
}

class Solution {
    public long countPairs(int n, int[][] edges) {
        UnionFind dsu = new UnionFind(n);
        for (int[] edge : edges) {
            dsu.union(edge[0], edge[1]);
        }
        Map<Integer, Integer> componentSize = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int parent = dsu.find(i);
            if (componentSize.containsKey(parent)) {
                componentSize.put(parent, componentSize.get(parent) + 1);
            } else {
                componentSize.put(parent, 1);
            }
        }

        long numberOfPaths = 0;
        long remainingNodes = n;

        for (int size : componentSize.values()) {
            numberOfPaths += size * (remainingNodes - size);
            remainingNodes -= size;
        }
        return numberOfPaths;
    }
}
