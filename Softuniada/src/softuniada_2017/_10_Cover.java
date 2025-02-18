package softuniada_2017;
import java.util.*;
public class _10_Cover {
    static class Soldier {
        int x, y;
        Soldier(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class ShelterPoint {
        int x, y;
        ShelterPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // Функция за изчисляване на Евклидово разстояние
    static double distance(Soldier s, ShelterPoint sh) {
        return Math.sqrt(Math.pow(s.x - sh.x, 2) + Math.pow(s.y - sh.y, 2));
    }

    // Проверка дали за дадено време можем да разпределим всички войници
    static boolean canAssignSoldiers(double timeLimit, Soldier[] soldiers, ShelterPoint[] shelters, int M, int C) {
        // Създаване на мрежа на разстоянията
        int S = soldiers.length;
        boolean[][] graph = new boolean[S][M];  // Свързаност войник - убежище

        // Попълване на графа със стойности дали войник може да стигне до дадено убежище в зададеното време
        for (int i = 0; i < S; i++) {
            for (int j = 0; j < M; j++) {
                if (distance(soldiers[i], shelters[j]) <= timeLimit) {
                    graph[i][j] = true;
                }
            }
        }

        // Мрежа за потока
        int source = S + M;  // Източник
        int sink = source + 1;  // Канал за потока
        int totalVertices = sink + 1;

        // Формиране на поточна мрежа
        int[][] capacity = new int[totalVertices][totalVertices];

        // Свързване източник -> войници
        for (int i = 0; i < S; i++) {
            capacity[source][i] = 1; // Всеки войник има капацитет 1 (т.е. може да отиде само в едно убежище)
        }

        // Свързване на убежища -> канала
        for (int j = 0; j < M; j++) {
            capacity[S + j][sink] = C; // Всяко убежище има капацитет C
        }

        // Свързване войници -> убежища
        for (int i = 0; i < S; i++) {
            for (int j = 0; j < M; j++) {
                if (graph[i][j]) {
                    capacity[i][S + j] = 1; // Всеки войник може да отиде само в едно убежище
                }
            }
        }

        // Функция за намиране на максимален поток
        int maxFlow = edmondsKarp(capacity, source, sink);
        return maxFlow == S; // Ако всички войници са разпределени, то трябва да получим максимален поток равен на S
    }

    // Алгоритъм на Едмондс-Карп за максимален поток
    static int edmondsKarp(int[][] capacity, int source, int sink) {
        int n = capacity.length;
        int[][] flow = new int[n][n];
        int[] parent = new int[n];
        int maxFlow = 0;

        while (true) {
            Arrays.fill(parent, -1);
            parent[source] = -2;
            Queue<Integer> queue = new LinkedList<>();
            queue.add(source);

            while (!queue.isEmpty()) {
                int u = queue.poll();
                for (int v = 0; v < n; v++) {
                    if (parent[v] == -1 && capacity[u][v] - flow[u][v] > 0) {
                        parent[v] = u;
                        if (v == sink) break;
                        queue.add(v);
                    }
                }
            }

            if (parent[sink] == -1) break;

            int augPathFlow = Integer.MAX_VALUE;
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                augPathFlow = Math.min(augPathFlow, capacity[u][v] - flow[u][v]);
            }

            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                flow[u][v] += augPathFlow;
                flow[v][u] -= augPathFlow;
            }

            maxFlow += augPathFlow;
        }

        return maxFlow;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Вход
        int S = sc.nextInt();
        int M = sc.nextInt();
        int C = sc.nextInt();
        sc.nextLine();  // Пропуск на новия ред

        Soldier[] soldiers = new Soldier[S];
        ShelterPoint[] shelters = new ShelterPoint[M];

        for (int i = 0; i < S; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            soldiers[i] = new Soldier(x, y);
        }

        for (int i = 0; i < M; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            shelters[i] = new ShelterPoint(x, y);
        }

        // Бинарно търсене върху времето
        double left = 0, right = 2828.427;  // Максимално разстояние за две крайни точки (макс 2000 по 2)
        double result = right;

        while (right - left > 1e-6) {
            double mid = (left + right) / 2;
            if (canAssignSoldiers(mid, soldiers, shelters, M, C)) {
                result = mid;
                right = mid;
            } else {
                left = mid;
            }
        }

        // Извеждаме резултата със 6 знака след десетичната запетая
        System.out.printf("%.6f\n", result);
    }
}
