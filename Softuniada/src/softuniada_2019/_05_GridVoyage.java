package softuniada_2019;
import java.util.*;
public class _05_GridVoyage {
    static int[][] grid;
    static int N;
    static int x, y;
    static final Map<String, int[]> directions = Map.of(
            "up", new int[]{-1, 0}, "down", new int[]{1, 0},
            "left", new int[]{0, -1}, "right", new int[]{0, 1}
    );
    static final Map<String, String[]> turnOrder = Map.of(
            "up", new String[]{"left", "right"},
            "down", new String[]{"right", "left"},
            "left", new String[]{"down", "up"},
            "right", new String[]{"up", "down"}
    );

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = Integer.parseInt(scanner.nextLine());
        grid = new int[N][N];

        // Read starting position
        String[] startPos = scanner.nextLine().split(" ");
        x = Integer.parseInt(startPos[0]);
        y = Integer.parseInt(startPos[1]);

        String input;
        while (!(input = scanner.nextLine()).equals("eastern odyssey")) {
            String[] parts = input.split(" ");
            int destX = Integer.parseInt(parts[0]);
            int destY = Integer.parseInt(parts[1]);
            String direction = parts[2];
            int stamina = Integer.parseInt(parts[3]);

            if (!moveToDestination(destX, destY, direction, stamina)) {
                System.out.println("Voyage impossible");
            }
        }
        scanner.close();
        printMatrix();
    }

    static boolean moveToDestination(int destX, int destY, String direction, int stamina) {
        int rests = 0;
        int currentX = x, currentY = y;
        List<int[]> path = new ArrayList<>();

        while (currentX != destX || currentY != destY) {
            for (int i = 0; i < stamina; i++) {
                int[] move = directions.get(direction);
                currentX += move[0];
                currentY += move[1];
                if (currentX < 0 || currentX >= N || currentY < 0 || currentY >= N) {
                    return false; // Out of bounds
                }
                path.add(new int[]{currentX, currentY});
                if (currentX == destX && currentY == destY) break;
            }
            if (currentX == destX && currentY == destY) break;
            rests++;
            direction = changeDirection(currentX, currentY, destX, destY, direction);
            if (direction == null) return false;
        }

        System.out.println(rests);
        for (int[] step : path) {
            grid[step[0]][step[1]]++;
        }
        x = currentX;
        y = currentY;
        return true;
    }

    static String changeDirection(int currentX, int currentY, int destX, int destY, String currentDirection) {
        for (String newDir : turnOrder.get(currentDirection)) {
            int[] move = directions.get(newDir);
            int testX = currentX + move[0];
            int testY = currentY + move[1];
            if (testX >= 0 && testX < N && testY >= 0 && testY < N) {
                return newDir;
            }
        }
        return null;
    }

    static void printMatrix() {
        for (int[] row : grid) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}
