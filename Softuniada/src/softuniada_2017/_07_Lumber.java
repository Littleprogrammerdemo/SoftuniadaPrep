package softuniada_2017;
import java.util.*;

public class _07_Lumber {
    static class Log {
        int ax, ay, bx, by;

        Log(int ax, int ay, int bx, int by) {
            this.ax = ax;
            this.ay = ay;
            this.bx = bx;
            this.by = by;
        }

        boolean touches(Log other) {
            // Проверка дали два дънера се докосват или пресичат
            return !(this.bx < other.ax || this.ax > other.bx || this.by > other.ay || this.ay < other.by);
        }
    }

    static void dfs(int u, boolean[] visited, List<Integer>[] graph) {
        visited[u] = true;
        for (int v : graph[u]) {
            if (!visited[v]) {
                dfs(v, visited, graph);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.nextLine();

        Log[] logs = new Log[n];
        for (int i = 0; i < n; i++) {
            int ax = scanner.nextInt();
            int ay = scanner.nextInt();
            int bx = scanner.nextInt();
            int by = scanner.nextInt();
            logs[i] = new Log(ax, ay, bx, by);
        }

        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // Създаване на граф
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (logs[i].touches(logs[j])) {
                    graph[i].add(j);
                    graph[j].add(i);
                }
            }
        }

        // Обработка на заявките
        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt() - 1;
            int y = scanner.nextInt() - 1;
            boolean[] visited = new boolean[n];
            dfs(x, visited, graph);

            if (visited[y]) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
