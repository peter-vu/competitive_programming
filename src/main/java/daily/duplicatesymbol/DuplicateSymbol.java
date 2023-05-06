package daily.duplicatesymbol;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class DuplicateSymbol {
    static int N;
    static int X, Y, Z; // Insert, Delete, Copy
    static int result;

    public static void main(String[] args) throws Exception {
        input();
        Solution s = new Solution();
        result = s.solve(N, X, Y, Z);
        output();
        Solution2 s2 = new Solution2();
        result = s2.solve(N, X, Y, Z);
        output();
    }

    static void output() {
        System.out.println(result);
    }

    static void input() throws FileNotFoundException {
        System.setIn(new FileInputStream("src/main/java/daily/duplicatesymbol/input.txt"));
        FastReader reader = new FastReader();
        N = reader.nextInt();
        X = reader.nextInt();
        Y = reader.nextInt();
        Z = reader.nextInt();
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
    public int solve(int N, int X, int Y, int Z) {
        int[] d = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            d[i] = Integer.MAX_VALUE;
        }
        // next step: visit delete, insert and copy, get smallest cost and continue
        // [0] = index, [1] = cost
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[1] != o2[1]) return o1[1] - o2[1];
            return o1[0] - o2[0];
        });
        d[1] = X;
        pq.offer(new int[]{1, X});
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currNode = curr[0];
            int currCost = curr[1];
            if (currNode == N) {
                break;
            }
            // generate next 3
            // insert
            if (currNode < N) {
                if (d[currNode + 1] > currCost + X) {
                    d[currNode + 1] = currCost + X;
                    addToQueue(pq, d, currNode + 1);
                }
            }
            // delete
            if (currNode > 1) {
                if (d[currNode - 1] > currCost + Y) {
                    d[currNode - 1] = currCost + Y;
                    addToQueue(pq, d, currNode - 1);
                }
            }
            // copy
            if (currNode <= N / 2) {
                if (d[currNode * 2] > currCost + Z) {
                    d[currNode * 2] = currCost + Z;
                    addToQueue(pq, d, currNode * 2);
                }
            }
        }
        return d[N];
    }

    void addToQueue(PriorityQueue<int[]> pq, int[] d, int index) {
        pq.offer(new int[]{index, d[index]});
    }
}

/**
 * int findTotalTime_dp(int N, int X, int Y, int Z) {
 * std::vector<int> dp(N + 1);
 * dp[1] = X;
 * for (int i = 2; i <= N; ++i) dp[i] = std::min(dp[i - 1] + X, dp[(i + 1) / 2] + Z + i % 2 * Y);
 * return dp[N];
 * }
 */
class Solution2 {
    public int solve(int N, int X, int Y, int Z) {
        int[] dp = new int[N + 1];
        dp[1] = X;
        for (int i = 2; i <= N; ++i) {
            dp[i] = Math.min(dp[i - 1] + X, dp[(i + 1) / 2] + Z + i % 2 * Y);
        }
        return dp[N];
    }

}

