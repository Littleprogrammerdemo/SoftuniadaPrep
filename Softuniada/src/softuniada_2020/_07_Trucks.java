package softuniada_2020;
import java.util.*;
public class _07_Trucks {
    static class Edge {
        String to;
        int capacity;

        Edge(String to, int capacity) {
            this.to = to;
            this.capacity = capacity;
        }
    }

    static Map<String, List<Edge>> graph = new HashMap<>();

    public static void addEdge(String from, String to, int capacity) {
        graph.putIfAbsent(from, new ArrayList<>());
        graph.putIfAbsent(to, new ArrayList<>());  // Уверяваме се, че върхът съществува
        graph.get(from).add(new Edge(to, capacity));
    }

    public static int bfs(String source, String target, Map<String, Edge> parentMap) {
        Queue<String> queue = new LinkedList<>();
        queue.add(source);

        Map<String, Integer> capacityMap = new HashMap<>();
        capacityMap.put(source, Integer.MAX_VALUE);

        while (!queue.isEmpty()) {
            String node = queue.poll();

            for (Edge edge : graph.getOrDefault(node, new ArrayList<>())) {
                if (!parentMap.containsKey(edge.to) && edge.capacity > 0) {
                    parentMap.put(edge.to, new Edge(node, edge.capacity));
                    int flow = Math.min(capacityMap.get(node), edge.capacity);
                    capacityMap.put(edge.to, flow);

                    if (edge.to.equals(target)) {
                        return flow;
                    }

                    queue.add(edge.to);
                }
            }
        }
        return 0;
    }

    public static int maxTrucks(String source, String target) {
        int maxFlow = 0;
        Map<String, Edge> parentMap = new HashMap<>();

        int flow;
        while ((flow = bfs(source, target, parentMap)) > 0) {
            maxFlow += flow;

            String cur = target;
            while (!cur.equals(source)) {
                Edge edge = parentMap.get(cur);
                String prev = edge.to;

                // Намаляваме капацитета по използвания път
                for (Edge e : graph.get(prev)) {
                    if (e.to.equals(cur)) {
                        e.capacity -= flow;
                        break;
                    }
                }

                cur = prev;
            }

            parentMap.clear();  // Изчистваме за следващия BFS
        }

        return maxFlow;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String startCity = scanner.nextLine();
        String endCity = scanner.nextLine();
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String from = scanner.next();
            String to = scanner.next();
            int capacity = scanner.nextInt();
            addEdge(from, to, capacity);
        }

        System.out.println(maxTrucks(startCity, endCity));
        scanner.close();
    }
}
