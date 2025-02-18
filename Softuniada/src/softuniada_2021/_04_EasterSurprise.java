package softuniada_2021;
import java.util.*;
public class _04_EasterSurprise {
    static char[][] grid;
    static int rows, cols;
    static char initialSymbol, symbolFound;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Четем размерите на матрицата
        rows = scanner.nextInt();
        cols = scanner.nextInt();
        scanner.nextLine();

        // Инициализираме матрицата
        grid = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            grid[i] = scanner.nextLine().replace(" ", "").toCharArray();
        }

        // Четем символа за маркиране
        symbolFound = scanner.next().charAt(0);
        scanner.nextLine();

        // Четем началните координати
        int startRow = scanner.nextInt();
        int startCol = scanner.nextInt();

        // Запазваме началния символ
        initialSymbol = grid[startRow][startCol];

        // Изпълняваме BFS за маркиране на всички свързани клетки
        bfs(startRow, startCol);

        // Принтираме резултата
        for (char[] row : grid) {
            System.out.println(row);
        }
    }

    private static void bfs(int startRow, int startCol) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startRow, startCol});
        grid[startRow][startCol] = symbolFound;

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] position = queue.poll();
            int r = position[0];
            int c = position[1];

            for (int[] d : directions) {
                int newRow = r + d[0];
                int newCol = c + d[1];

                if (isValid(newRow, newCol)) {
                    grid[newRow][newCol] = symbolFound;
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && r < rows && c >= 0 && c < cols && grid[r][c] == initialSymbol;
    }
}
