package softuniada_2018;
import java.util.*;
public class _06_Asterods {
    // Съхранява координатите на четири посоки за DFS/BFS (нагоре, надолу, наляво, надясно)
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    // Проверява дали дадената позиция е в границите на полето
    static boolean isValid(int x, int y, int N, int M) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }

    // Извършва DFS за да намери площта на свързан компонент (астероид)
    static int dfs(int[][] field, boolean[][] visited, int x, int y, int N, int M) {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{x, y});
        visited[x][y] = true;
        int area = 0;

        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            int cx = current[0], cy = current[1];
            area++;

            // Преглеждаме съседите на текущата клетка
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i], ny = cy + dy[i];
                if (isValid(nx, ny, N, M) && field[nx][ny] == 1 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    stack.push(new int[]{nx, ny});
                }
            }
        }

        return area;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String size = sc.nextLine();
            if (size.equals("end")) break;

            // Разделяме размера на N и M
            String[] dimensions = size.split("x");
            int N = Integer.parseInt(dimensions[0]);
            int M = Integer.parseInt(dimensions[1]);

            int[][] field = new int[N][M];
            boolean[][] visited = new boolean[N][M];

            // Четем полето
            for (int i = 0; i < N; i++) {
                String row = sc.nextLine();
                for (int j = 0; j < M; j++) {
                    field[i][j] = row.charAt(j) - '0'; // Преобразуваме символите в числа 0 или 1
                }
            }

            // За да съхраняваме площите на астероидите
            Map<Integer, Integer> asteroidSizes = new TreeMap<>(Collections.reverseOrder());

            // Търсим всички свързани компоненти (астероиди)
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (field[i][j] == 1 && !visited[i][j]) {
                        int area = dfs(field, visited, i, j, N, M);
                        asteroidSizes.put(area, asteroidSizes.getOrDefault(area, 0) + 1);
                    }
                }
            }

            // Извеждаме резултата
            int totalAsteroids = 0;
            for (Map.Entry<Integer, Integer> entry : asteroidSizes.entrySet()) {
                System.out.println(entry.getValue() + "x" + entry.getKey());
                totalAsteroids += entry.getValue();
            }

            // Общ брой астероиди
            System.out.println("Total: " + totalAsteroids);
        }

        sc.close();
    }
}
