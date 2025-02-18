package softuniada_2023;
import java.util.*;
public class _10_HighTechRacing {
    static class Edge {
        int to, weight;
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class Node implements Comparable<Node> {
        int id, dist;
        List<Integer> path;

        Node(int id, int dist, List<Integer> path) {
            this.id = id;
            this.dist = dist;
            this.path = new ArrayList<>(path);
        }

        @Override
        public int compareTo(Node other) {
            if (this.dist != other.dist) return Integer.compare(this.dist, other.dist);
            return Integer.compare(this.path.size(), other.path.size());
        }
    }

    static int N, M;
    static List<List<Edge>> graph = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        M = scanner.nextInt();
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int weight = scanner.nextInt();
            graph.get(from).add(new Edge(to, weight));
            graph.get(to).add(new Edge(from, weight)); // двупосочен път
        }

        // Намери първия най-кратък път
        List<Integer> firstPath = dijkstra(0, N - 1, new HashSet<>());
        if (firstPath.isEmpty()) {
            System.out.println("No path found");
            return;
        }

        // Изтрий използваните пътища
        Set<String> usedEdges = new HashSet<>();
        for (int i = 0; i < firstPath.size() - 1; i++) {
            int u = firstPath.get(i), v = firstPath.get(i + 1);
            usedEdges.add(u + "," + v);
            usedEdges.add(v + "," + u);
        }

        // Намери втория най-кратък път
        List<Integer> secondPath = dijkstra(0, N - 1, usedEdges);
        if (secondPath.isEmpty()) {
            System.out.println("No second path found");
            return;
        }

        // Отпечатване в желания ред
        printPath(firstPath);
        printPath(secondPath);
    }

    private static List<Integer> dijkstra(int start, int end, Set<String> blockedEdges) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0, Arrays.asList(start)));
        Map<Integer, Integer> dist = new HashMap<>();
        dist.put(start, 0);

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.id;

            if (u == end) return current.path;

            for (Edge edge : graph.get(u)) {
                int v = edge.to, weight = edge.weight;
                if (blockedEdges.contains(u + "," + v)) continue;

                int newDist = current.dist + weight;
                if (!dist.containsKey(v) || newDist < dist.get(v)) {
                    dist.put(v, newDist);
                    List<Integer> newPath = new ArrayList<>(current.path);
                    newPath.add(v);
                    pq.add(new Node(v, newDist, newPath));
                }
            }
        }
        return new ArrayList<>();
    }

    private static void printPath(List<Integer> path) {
        System.out.println(String.join(" -> ", path.stream().map(String::valueOf).toArray(String[]::new)));
    }
}
