package leetcode.daily.p230212.minimumfuelcost;

import java.util.*;
import java.util.stream.IntStream;

public class MinimumFuelCost {
    public static void main(String[] args) {
        List<int[][]> roadsList = generateRoadsList();
        List<Integer> seatsList = generateSeatsList();
        List<Long> expectedResultList = generateResult();

        for (int i = 0; i < roadsList.size(); i++) {
            int[][] roads = roadsList.get(i);
            int seats = seatsList.get(i);
            long expectedResult = expectedResultList.get(i);
            Solution2 solution = new Solution2();
            long result = solution.minimumFuelCost(roads, seats);
            System.out.println("roads: " + formatArrays(roads));
            System.out.println("seats: " + seats);
            System.out.println("expected result vs result: " + expectedResult + " - " + result);
        }

    }

    private static String formatArrays(int[][] roads) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < roads.length; i++) {
            sb.append("[").append(roads[i][0]).append(",").append(roads[i][1]).append("]");
            if (i < roads.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    private static List<Integer> generateSeatsList() {
        return Arrays.asList(5, 2, 1, 5, 1, 2, 26);
    }

    private static List<int[][]> generateRoadsList() {
        int[][] roads1 = {{0, 1}, {0, 2}, {0, 3}};
        int[][] roads2 = {{3, 1}, {3, 2}, {1, 0}, {0, 4}, {0, 5}, {4, 6}};
        int[][] roads3 = {};
        int[][] roads4 = {{0, 1}, {0, 2}, {1, 3}, {1, 4}};
        int[][] roads5 = {{0, 1}, {0, 2}, {3, 2}, {0, 4}, {1, 5}, {5, 6}, {3, 7}};
        int[][] roads6 = {{0, 1}, {2, 1}, {3, 2}, {4, 2}, {4, 5}, {6, 0}, {5, 7}, {8, 4}, {9, 2}};
        int[][] roads7 = {{0,1},{2,0},{3,2},{3,4},{2,5},{6,4},{6,7},{8,2},{9,0},{3,10},{1,11},{5,12},{6,13},{6,14},{15,10},{16,0},{14,17},{12,18},{19,6},{20,17},{14,21},{12,22},{23,20},{24,11},{25,15},{26,7},{17,27},{15,28},{5,29},{30,8},{31,1},{32,12},{33,29},{34,5},{35,27},{36,30},{37,31},{20,38},{16,39},{40,6},{28,41},{42,30},{43,2},{12,44},{45,17},{5,46},{47,6}};
        return Arrays.asList(
                roads1, roads2, roads3, roads4, roads5, roads6, roads7
        );
    }

    private static List<Long> generateResult() {
        return Arrays.asList(3L, 7L, 0L, 4L, 13L, 16L, 48L);
    }
}

// failed test 7
//class Solution {
//    int dimension;
//    int maxCapacity;
//    int[] capacity;
//    int[] parent;
//    List<Integer>[] adjList;
//    int[] distance;
//    boolean[] visited;
//    List<Integer> leafNodes;
//    long totalCost;
//
//
//    public long minimumFuelCost(int[][] roads, int seats) {
//        initialize(roads, seats);
//        calculatePath();
//        bfs();
//        return totalCost;
//    }
//
//    private void bfs() {
//        // reinit visited
//        visited = new boolean[dimension];
//        visited[0] = true;
//        LinkedList<Integer> queue = new LinkedList<>();
//        leafNodes.forEach(node -> {
//            visited[node] = true;
//            queue.offer(node);
//        });
//        while (!queue.isEmpty()) {
//            int currNode = queue.poll();
//            // if some child is not processed, we should not process parent
//            boolean isGoodToGo = true;
//            for(int next: adjList[currNode]) {
//                if (parent[currNode] != next) {
//                    if (queue.contains(next)){
//                        isGoodToGo = false;
//                        break;
//                    }
//                }
//            }
//            if(!isGoodToGo) {
//                queue.offer(currNode);
//            }
//            else {
//                int currCapacity = capacity[currNode];
//                // if current capacity is more than maxCapacity, it means there are more guests than seats, we need to add cost for max capacity
//                while (currCapacity > maxCapacity) {
//                    totalCost += distance[currNode];
//                    currCapacity -= maxCapacity;
//                }
//                if (currNode != 0) totalCost++;
//
//                int parentNode = parent[currNode];
//                capacity[parentNode] += currCapacity;
//                if (!visited[parentNode]) {
//                    visited[parentNode] = true;
//                    queue.offer(parentNode);
//                }
//
//            }
//        }
//    }
//
//    private void calculatePath() {
//        dfs(0, 0, 0);
//    }
//
//    private void dfs(int parentNode, int node, int weight) {
//        visited[node] = true;
//        distance[node] = weight;
//        parent[node] = parentNode;
//        List<Integer> unvisitedNode = adjList[node].stream().filter(i -> !visited[i]).toList();
//        if (unvisitedNode.isEmpty()) {
//            leafNodes.add(node);
//        } else {
//            unvisitedNode.forEach(i -> dfs(node, i, weight + 1));
//        }
//    }
//
//    private void initialize(int[][] roads, int seats) {
//        // initialize adjacency list
//        dimension = roads.length + 1;
//        adjList = new ArrayList[dimension];
//        IntStream.range(0, dimension).forEach(i -> adjList[i] = new ArrayList<>());
//        IntStream.range(0, dimension - 1).forEach(i -> {
//            int from = roads[i][0];
//            int to = roads[i][1];
//            adjList[from].add(to);
//            adjList[to].add(from);
//        });
//
//        distance = new int[dimension];
//        visited = new boolean[dimension];
//        leafNodes = new ArrayList<>();
//        this.maxCapacity = seats;
//        totalCost = 0L;
//        capacity = new int[dimension];
//        IntStream.range(0, dimension).forEach(i -> capacity[i] = 1);
//        parent = new int[dimension];
//    }
//}

class Solution2 {
    int dimension;
    int maxCapacity;
    int[] capacity;
    int[] parent;
    List<Integer>[] adjList;
    int[] distance;
    boolean[] visited;
    List<Integer> leafNodes;
    long totalCost;


    public long minimumFuelCost(int[][] roads, int seats) {
        initialize(roads, seats);
        calculatePath();
        bfs();
        return totalCost;
    }

    private void bfs() {
        // reinit visited
        visited = new boolean[dimension];
        visited[0] = true;
        // element is node and height
        // element with bigger height should be processed first
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });
//        LinkedList<Integer> queue = new LinkedList<>();
        leafNodes.forEach(node -> {
            visited[node] = true;
            queue.offer(new int[]{node, distance[node]});
        });
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int currNode = curr[0];
            // if some child is not processed, we should not process parent
            boolean isGoodToGo = true;
                int currCapacity = capacity[currNode];
                // if current capacity is more than maxCapacity, it means there are more guests than seats, we need to add cost for max capacity
                while (currCapacity > maxCapacity) {
                    totalCost += distance[currNode];
                    currCapacity -= maxCapacity;
                }
                if (currNode != 0) totalCost++;

                int parentNode = parent[currNode];
                capacity[parentNode] += currCapacity;
                if (!visited[parentNode]) {
                    visited[parentNode] = true;
                    queue.offer(new int[]{parentNode, distance[parentNode]});
                }
        }
    }

    private void calculatePath() {
        dfs(0, 0, 0);
    }

    private void dfs(int parentNode, int node, int weight) {
        visited[node] = true;
        distance[node] = weight;
        parent[node] = parentNode;
        List<Integer> unvisitedNode = adjList[node].stream().filter(i -> !visited[i]).toList();
        if (unvisitedNode.isEmpty()) {
            leafNodes.add(node);
        } else {
            unvisitedNode.forEach(i -> dfs(node, i, weight + 1));
        }
    }

    private void initialize(int[][] roads, int seats) {
        // initialize adjacency list
        dimension = roads.length + 1;
        adjList = new ArrayList[dimension];
        IntStream.range(0, dimension).forEach(i -> adjList[i] = new ArrayList<>());
        IntStream.range(0, dimension - 1).forEach(i -> {
            int from = roads[i][0];
            int to = roads[i][1];
            adjList[from].add(to);
            adjList[to].add(from);
        });

        distance = new int[dimension];
        visited = new boolean[dimension];
        leafNodes = new ArrayList<>();
        this.maxCapacity = seats;
        totalCost = 0L;
        capacity = new int[dimension];
        IntStream.range(0, dimension).forEach(i -> capacity[i] = 1);
        parent = new int[dimension];
    }
}