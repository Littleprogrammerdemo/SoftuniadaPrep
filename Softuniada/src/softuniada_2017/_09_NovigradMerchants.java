package softuniada_2017;
import java.util.*;
public class _09_NovigradMerchants {
    private static final int MOD = 1000000000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read number of cities (N) and roads (M)
        int N = sc.nextInt();
        int M = sc.nextInt();

        // Create the graph as an adjacency list
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // Read the roads
        for (int i = 0; i < M; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph.get(u).add(v);
        }

        // Initialize DP table and visited arrays
        long[] dp = new long[N + 1]; // dp[i] will store the number of paths to city i
        boolean[] visited = new boolean[N + 1]; // for cycle detection
        boolean[] onStack = new boolean[N + 1]; // to detect if the node is currently in the recursion stack
        boolean[] inCycle = new boolean[N + 1]; // to mark nodes that are part of cycles

        // Check for cycles using DFS
        boolean hasCycle = false;
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                if (dfsCycleDetection(i, graph, visited, onStack, inCycle)) {
                    hasCycle = true;
                    break;
                }
            }
        }

        // If there are infinite paths, print "infinite"
        if (hasCycle) {
            System.out.println("infinite");
            return;
        }

        // Use DFS to count paths from Novigrad (city 1) to Oxenfurt (city N)
        dp[1] = 1; // There is 1 way to start from Novigrad (city 1)
        boolean[] visitedForPathCounting = new boolean[N + 1];
        dfsCountPaths(1, graph, dp, visitedForPathCounting);

        // Check if there are infinite paths to any other city
        boolean infinitePathsExist = false;
        for (int i = 1; i <= N; i++) {
            if (inCycle[i] && dp[i] > 0) {
                infinitePathsExist = true;
                break;
            }
        }

        // Output the result
        if (dp[N] == 0) {
            System.out.println("0 no");
        } else if (infinitePathsExist) {
            System.out.println(dp[N] % MOD + " yes");
        } else {
            System.out.println(dp[N] % MOD + " no");
        }
    }

    // Helper function for DFS to detect cycles
    private static boolean dfsCycleDetection(int node, List<List<Integer>> graph, boolean[] visited, boolean[] onStack, boolean[] inCycle) {
        visited[node] = true;
        onStack[node] = true;

        for (int neighbor : graph.get(node)) {
            if (onStack[neighbor]) {
                inCycle[neighbor] = true;
                return true;
            }
            if (!visited[neighbor] && dfsCycleDetection(neighbor, graph, visited, onStack, inCycle)) {
                inCycle[neighbor] = true;
                return true;
            }
        }

        onStack[node] = false;
        return false;
    }

    // Helper function to count the number of distinct paths using DFS
    private static void dfsCountPaths(int node, List<List<Integer>> graph, long[] dp, boolean[] visitedForPathCounting) {
        visitedForPathCounting[node] = true;

        for (int neighbor : graph.get(node)) {
            if (!visitedForPathCounting[neighbor]) {
                dfsCountPaths(neighbor, graph, dp, visitedForPathCounting);
            }
            dp[neighbor] = (dp[neighbor] + dp[node]) % MOD;
        }
    }
}
