package softuniada_2018;
import java.util.*;
public class _09_Mafia {
    // Клас, който съхранява информация за графа
    static class Graph {
        int N; // Броя на компютрите
        int[][] capacity; // Мрежови капацитети
        int[][] flow; // Текущ поток

        public Graph(int N) {
            this.N = N;
            capacity = new int[N][N];
            flow = new int[N][N];
        }

        // Добавяме ръб (връзка) с капацитет
        public void addEdge(int u, int v, int cap) {
            capacity[u][v] = cap;
            capacity[v][u] = cap; // Тъй като връзката е двупосочна
        }

        // Функция за намиране на максимален поток с помощта на BFS (алгоритъм на Едмондс-Карп)
        public int bfs(int source, int sink, int[] parent) {
            boolean[] visited = new boolean[N];
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(source);
            visited[source] = true;
            parent[source] = -1;

            while (!queue.isEmpty()) {
                int u = queue.poll();

                for (int v = 0; v < N; v++) {
                    if (!visited[v] && capacity[u][v] - flow[u][v] > 0) {
                        queue.offer(v);
                        parent[v] = u;
                        if (v == sink) {
                            return 1;
                        }
                        visited[v] = true;
                    }
                }
            }
            return 0;
        }

        // Алгоритъм за намиране на максимален поток (метод на Едмондс-Карп)
        public int edmondsKarp(int source, int sink) {
            int maxFlow = 0;
            int[] parent = new int[N];

            // Продължаваме докато има път от source до sink
            while (bfs(source, sink, parent) == 1) {
                // Търсим минималния капацитет по пътя
                int pathFlow = Integer.MAX_VALUE;
                for (int v = sink; v != source; v = parent[v]) {
                    int u = parent[v];
                    pathFlow = Math.min(pathFlow, capacity[u][v] - flow[u][v]);
                }

                // Актуализираме потоците по всички ръбове от пътя
                for (int v = sink; v != source; v = parent[v]) {
                    int u = parent[v];
                    flow[u][v] += pathFlow;
                    flow[v][u] -= pathFlow; // Обратно движение
                }

                maxFlow += pathFlow;
            }

            return maxFlow;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int groupCount = 1;

        while (true) {
            int N = sc.nextInt();
            if (N == 0) break;

            Graph graph = new Graph(N);

            while (true) {
                String edge = sc.next();
                if (edge.equals("end")) break;

                String[] parts = edge.split("-");
                int A = Integer.parseInt(parts[0]) - 1;
                String[] temp = parts[1].split(" ");
                int B = Integer.parseInt(temp[0]) - 1;
                int P = Integer.parseInt(temp[1]);

                graph.addEdge(A, B, P);
            }

            // Изчисляваме максималния поток между компютър 1 (0) и N (N-1)
            int maxFlow = graph.edmondsKarp(0, N - 1);

            System.out.println("Group " + groupCount + ": " + maxFlow);
            groupCount++;
        }
    }
}
