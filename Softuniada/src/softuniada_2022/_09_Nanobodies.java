package softuniada_2022;
import java.util.*;
public class _09_Nanobodies {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int M = scanner.nextInt();
        int N = scanner.nextInt();

        int[][] distances = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                distances[i][j] = scanner.nextInt();
            }
        }

        Set<Integer> selected = new HashSet<>();
        selected.add(0);

        while (selected.size() < M) {
            int bestCell = -1;
            int bestMinDist = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                if (!selected.contains(i)) {
                    int minDist = Integer.MAX_VALUE;
                    for (int s : selected) {
                        minDist = Math.min(minDist, distances[i][s]);
                    }

                    if (minDist > bestMinDist) {
                        bestMinDist = minDist;
                        bestCell = i;
                    }
                }
            }

            selected.add(bestCell);
        }

        List<Integer> result = new ArrayList<>(selected);
        Collections.sort(result);
        for (int i = 0; i < result.size(); i++) {
            System.out.print((result.get(i) + 1) + (i < result.size() - 1 ? " " : "\n"));
        }
    }
}
