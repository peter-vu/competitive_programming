package leetcode.daily.d202304.d230409;

import java.util.*;

public class LargestColorInGraph {
}

class Solution {
    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        Map<Integer, List<Integer>> adj = new HashMap<>();
        int[] indegree = new int[n];

        for (int[] edge : edges) {
            adj.computeIfAbsent(edge[0], k -> new ArrayList<Integer>()).add(edge[1]);
            indegree[edge[1]]++;
        }

        int[][] count = new int[n][26];
        Queue<Integer> q = new LinkedList<>();

        // Push all the nodes with indegree zero in the queue.
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        int answer = 1, nodesSeen = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
            answer = Math.max(answer, ++count[node][colors.charAt(node) - 'a']);
            nodesSeen++;

            if (!adj.containsKey(node)) {
                continue;
            }

            for (int neighbor : adj.get(node)) {
                for (int i = 0; i < 26; i++) {
                    // Try to update the frequency of colors for the neighbor to include paths
                    // that use node->neighbor edge.
                    count[neighbor][i] = Math.max(count[neighbor][i], count[node][i]);
                }

                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    q.offer(neighbor);
                }
            }
        }

        return nodesSeen < n ? -1 : answer;
    }
}


// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

