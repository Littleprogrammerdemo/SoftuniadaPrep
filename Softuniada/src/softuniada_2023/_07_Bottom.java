package softuniada_2023;

import java.util.*;

public class _07_Bottom {
    static class Edge {
        int u, v, weight;
        Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    static int[] parent, rank;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int V = scanner.nextInt();
        int E = scanner.nextInt();

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < E; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int weight = scanner.nextInt();
            edges.add(new Edge(u, v, weight));
        }
        scanner.close();

        // Сортиране на ребрата по тегло
        edges.sort(Comparator.comparingInt(e -> e.weight));

        // Бинарно търсене за минималното D
        int left = 2, right = 100001, result = right;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (isConnected(V, edges, mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(result);
    }

    static boolean isConnected(int V, List<Edge> edges, int D) {
        parent = new int[V];
        rank = new int[V];
        for (int i = 0; i < V; i++) parent[i] = i;

        for (Edge edge : edges) {
            if (edge.weight >= D) break;
            union(edge.u, edge.v);
        }

        int root = find(0);
        for (int i = 1; i < V; i++) {
            if (find(i) != root) return false;
        }
        return true;
    }

    static int find(int node) {
        if (parent[node] != node) {
            parent[node] = find(parent[node]);
        }
        return parent[node];
    }

    static void union(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);

        if (rootU != rootV) {
            if (rank[rootU] > rank[rootV]) {
                parent[rootV] = rootU;
            } else if (rank[rootU] < rank[rootV]) {
                parent[rootU] = rootV;
            } else {
                parent[rootV] = rootU;
                rank[rootU]++;
            }
        }
    }
}
