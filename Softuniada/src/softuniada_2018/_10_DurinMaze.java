package softuniada_2018;
import java.util.*;
public class _10_DurinMaze {
    // Графът с N стаи
    static List<List<Integer>> graph;
    static boolean[] visited;
    static List<Integer> keyRooms = new ArrayList<>();

    // Функция за извършване на DFS
    public static void dfs(int current, int target, Set<Integer> path, Set<Integer> visitedNodes) {
        // Добавяме текущата стая в пътя
        visitedNodes.add(current);
        path.add(current);

        // Ако стигнем до съкровищницата
        if (current == target) {
            // Добавяме всички стаи от пътя в keyRooms
            keyRooms.addAll(path);
        }

        // Разглеждаме съседите на текущата стая
        for (int neighbor : graph.get(current)) {
            if (!visitedNodes.contains(neighbor)) {
                dfs(neighbor, target, new HashSet<>(path), new HashSet<>(visitedNodes));
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Въвеждаме броя на стаите (N) и тунелите (M)
        int N = sc.nextInt();
        int M = sc.nextInt();

        // Създаваме графа
        graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        // Въвеждаме тунелите
        for (int i = 0; i < M; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        // Изпълняваме DFS от стая 0 (предверие) до стая N-1 (съкровищница)
        Set<Integer> path = new HashSet<>();
        Set<Integer> visitedNodes = new HashSet<>();
        dfs(0, N - 1, path, visitedNodes);

        // Пресмятаме тайния код (сумата на индексите на ключовите стаи)
        int secretCode = 0;
        for (int room : keyRooms) {
            if (room != 0 && room != N - 1) { // Изключваме предверието и съкровищницата
                secretCode += room;
            }
        }

        // Извеждаме тайния код
        System.out.println(secretCode);

        // Извеждаме ключовите стаи
        boolean first = true;
        for (int room : keyRooms) {
            if (room != 0 && room != N - 1) { // Изключваме предверието и съкровищницата
                if (!first) {
                    System.out.print("->");
                }
                System.out.print(room);
                first = false;
            }
        }
    }
}
