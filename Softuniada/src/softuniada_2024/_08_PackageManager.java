package softuniada_2024;
import java.util.*;

public class _08_PackageManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Въвеждаме брой пакети и връзки
        int N = scanner.nextInt();
        int M = scanner.nextInt();

        // Граф за представяне на зависимостите и степен на влизане
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[N];

        // Инициализиране на графа
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        // Въвеждаме всички зависимости
        for (int i = 0; i < M; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            graph.get(u).add(v);
            inDegree[v]++;
        }

        // Приготвяме опашка за Kahn's Algorithm
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        // Резултата за топологично сортиране
        List<Integer> topologicalOrder = new ArrayList<>();

        // Процес на топологично сортиране
        while (!queue.isEmpty()) {
            int node = queue.poll();
            topologicalOrder.add(node);

            // Намаляваме степента на влизане на съседите
            for (int neighbor : graph.get(node)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // Ако не всички пакети са включени в топологичната подредба, има цикъл
        if (topologicalOrder.size() != N) {
            System.out.println("circular dependency");
        } else {
            // Отпечатваме подредбата
            for (int i = 0; i < topologicalOrder.size(); i++) {
                System.out.print(topologicalOrder.get(i) + " ");
            }
        }
    }
}
