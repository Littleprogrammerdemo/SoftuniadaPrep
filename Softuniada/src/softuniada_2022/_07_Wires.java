package softuniada_2022;
import java.util.*;
public class _07_Wires {
    static int count = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt(); // Брой жици
        int M = scanner.nextInt(); // Брой зависимости
        scanner.nextLine();

        Map<Integer, List<Integer>> dependencies = new HashMap<>();
        int[] inDegree = new int[N + 1]; // Масив за брой предшественици

        // Инициализация на зависимостите
        for (int i = 1; i <= N; i++) {
            dependencies.put(i, new ArrayList<>());
        }

        // Четене на зависимостите
        for (int i = 0; i < M; i++) {
            int dependent = scanner.nextInt();
            scanner.next(); // Пропускаме символа ">"
            int prerequisite = scanner.nextInt();

            dependencies.get(prerequisite).add(dependent);
            inDegree[dependent]++;
        }

        List<Integer> order = new ArrayList<>();
        boolean[] visited = new boolean[N + 1];
        topologicalSort(N, dependencies, inDegree, visited, order);
        System.out.println(count);
    }

    private static void topologicalSort(int N, Map<Integer, List<Integer>> dependencies, int[] inDegree, boolean[] visited, List<Integer> order) {
        boolean allUsed = true;

        for (int i = 1; i <= N; i++) {
            if (!visited[i] && inDegree[i] == 0) {
                visited[i] = true;
                order.add(i);

                // Намаляваме inDegree за зависимите жици
                for (int dependent : dependencies.get(i)) {
                    inDegree[dependent]--;
                }

                topologicalSort(N, dependencies, inDegree, visited, order);

                // Възстановяване на състоянието за backtracking
                visited[i] = false;
                order.remove(order.size() - 1);
                for (int dependent : dependencies.get(i)) {
                    inDegree[dependent]++;
                }
                allUsed = false;
            }
        }

        if (allUsed) {
            count++;
        }
    }
}
