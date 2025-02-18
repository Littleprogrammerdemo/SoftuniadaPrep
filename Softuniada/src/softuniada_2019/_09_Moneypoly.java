package softuniada_2019;
import java.util.*;
public class _09_Moneypoly {
    static class Edge implements Comparable<Edge> {
        int u, v, weight;

        Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            if (this.weight != other.weight)
                return Integer.compare(this.weight, other.weight);
            if (Math.min(this.u, this.v) != Math.min(other.u, other.v))
                return Integer.compare(Math.min(this.u, this.v), Math.min(other.u, other.v));
            return Integer.compare(Math.max(this.u, this.v), Math.max(other.u, other.v));
        }
    }

    static class UnionFind {
        int[] parent, rank;

        UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) parent[i] = i;
        }

        int find(int node) {
            if (parent[node] != node)
                parent[node] = find(parent[node]); // Path compression
            return parent[node];
        }

        boolean union(int node1, int node2) {
            int root1 = find(node1), root2 = find(node2);
            if (root1 != root2) {
                if (rank[root1] > rank[root2]) parent[root2] = root1;
                else if (rank[root1] < rank[root2]) parent[root1] = root2;
                else { parent[root2] = root1; rank[root1]++; }
                return true;
            }
            return false;
        }
    }

    static int N;
    static int[] investments;
    static List<List<int[]>> mstGraph;
    static int maxBalance = Integer.MIN_VALUE;
    static List<Integer> bestPath = new ArrayList<>();

    static void buildMST(List<Edge> edges) {
        Collections.sort(edges);
        UnionFind uf = new UnionFind(N);
        mstGraph = new ArrayList<>();
        for (int i = 0; i < N; i++) mstGraph.add(new ArrayList<>());

        for (Edge e : edges) {
            if (uf.union(e.u, e.v)) {
                mstGraph.get(e.u).add(new int[]{e.v, e.weight});
                mstGraph.get(e.v).add(new int[]{e.u, e.weight});
            }
        }
    }

    static void dfs(int node, Set<Integer> visited, int balance, List<Integer> path) {
        visited.add(node);
        balance += investments[node];
        path.add(node);

        if (balance > maxBalance) {
            maxBalance = balance;
            bestPath = new ArrayList<>(path);
            Collections.sort(bestPath);
        }

        for (int[] neighbor : mstGraph.get(node)) {
            int next = neighbor[0];
            if (!visited.contains(next)) {
                dfs(next, new HashSet<>(visited), balance, new ArrayList<>(path));

                // Simulate bankruptcy
                dfs(next, new HashSet<>(visited), 0, new ArrayList<>());
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = Integer.parseInt(scanner.nextLine());
        investments = new int[N];

        for (int i = 0; i < N; i++) {
            String[] parts = scanner.nextLine().split(" ");
            int index = Integer.parseInt(parts[0]);
            int value = Integer.parseInt(parts[1]);
            investments[index] = value;
        }

        List<Edge> edges = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("end")) break;
            String[] parts = line.split(" ");
            int u = Integer.parseInt(parts[0]);
            int v = Integer.parseInt(parts[1]);
            int w = Integer.parseInt(parts[2]);
            edges.add(new Edge(u, v, w));
        }
        scanner.close();

        buildMST(edges);

        dfs(0, new HashSet<>(), 0, new ArrayList<>());

        System.out.println(maxBalance);
        bestPath.forEach(i -> System.out.print(i + " "));
    }
}
