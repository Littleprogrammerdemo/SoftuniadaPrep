package softuniada_2024;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class _04_Chess {
    // Стандартни движения на коня в шах
    static int[] dx = {2, 2, -2, -2, 1, 1, -1, -1};
    static int[] dy = {1, -1, 1, -1, 2, -2, 2, -2};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Въвеждаме входа
        int N = scanner.nextInt(); // Размер на шахматната дъска
        int R = scanner.nextInt(); // Начална позиция на реда
        int C = scanner.nextInt(); // Начална позиция на колоната
        int T = scanner.nextInt(); // Целева позиция на реда
        int F = scanner.nextInt(); // Целева позиция на колоната

        // Призив на BFS
        System.out.println(minKnightMoves(N, R, C, T, F));
    }

    public static int minKnightMoves(int N, int R, int C, int T, int F) {
        // Мрежа, за да проверим дали клетката е посещавана
        boolean[][] visited = new boolean[N][N];

        // Потребители на BFS
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{R, C, 0}); // Добавяме началната позиция и брой на ходовете
        visited[R][C] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0]; // Текущ ред
            int y = current[1]; // Текуща колона
            int moves = current[2]; // Броят на направените ходове

            // Ако сме стигнали до целта, връщаме броя на ходовете
            if (x == T && y == F) {
                return moves;
            }

            // Преглеждаме всички възможни ходове на коня
            for (int i = 0; i < 8; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];

                // Проверяваме дали новата позиция е валидна
                if (newX >= 0 && newX < N && newY >= 0 && newY < N && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    queue.offer(new int[]{newX, newY, moves + 1});
                }
            }
        }

        return -1; // Не трябва да се случи, защото винаги има решение
    }
}
