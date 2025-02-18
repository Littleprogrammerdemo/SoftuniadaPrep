package softuniada_2024;
import java.util.*;
public class _09_NightWatch {
    static class Edge {
        int u, v, weight;

        Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Четем входа
        int N = scanner.nextInt();  // брой локации
        int M = scanner.nextInt();  // брой пътища

        // Създаваме графа и списък на пътищата
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int weight = scanner.nextInt();
            edges.add(new Edge(u, v, weight));
        }

        int X = scanner.nextInt();  // стартова локация

        // Инициализация на разстоянията с безкрайност
        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[X] = 0;

        // Белман-Форд за намиране на най-кратките разстояния
        for (int i = 1; i < N; i++) {
            for (Edge edge : edges) {
                if (dist[edge.u] != Integer.MAX_VALUE && dist[edge.u] + edge.weight < dist[edge.v]) {
                    dist[edge.v] = dist[edge.u] + edge.weight;
                }
            }
        }

        // Събиране на пътищата, които ни интересуват
        List<Edge> result = new ArrayList<>();
        for (Edge edge : edges) {
            if (dist[edge.u] != Integer.MAX_VALUE && dist[edge.u] + edge.weight == dist[edge.v]) {
                result.add(edge);
            }
        }

        // Подреждане на пътищата
        result.sort((e1, e2) -> {
            if (e2.weight != e1.weight) return Integer.compare(e2.weight, e1.weight);  // намаляващо разстояние
            if (e1.u != e2.u) return Integer.compare(e1.u, e2.u);  // нарастващо начална локация
            return Integer.compare(e1.v, e2.v);  // нарастващо крайна локация
        });

        // Отпечатваме пътищата
        for (Edge edge : result) {
            System.out.println(edge.u + " " + edge.v + " " + edge.weight);
        }
    }
}
