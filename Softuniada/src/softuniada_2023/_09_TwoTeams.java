package softuniada_2023;
import java.util.*;
public class _09_TwoTeams {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int[] players = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // Намерете LIS (Longest Increasing Subsequence)
        int[] lis = longestIncreasingSubsequence(players);

        // Намерете LDS (Longest Decreasing Subsequence)
        int[] lds = longestDecreasingSubsequence(players);

        // Намерете максималния брой разпределени играчи
        int maxAssigned = 0;
        for (int i = 0; i < n; i++) {
            maxAssigned = Math.max(maxAssigned, lis[i] + lds[i] - 1);
        }

        // Изчисляваме броя на неразпределените играчи
        int unassigned = n - maxAssigned;
        System.out.println(unassigned);
    }

    // Функция за намиране на LIS (Longest Increasing Subsequence)
    private static int[] longestIncreasingSubsequence(int[] arr) {
        int n = arr.length;
        int[] lis = new int[n];
        Arrays.fill(lis, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && lis[i] < lis[j] + 1) {
                    lis[i] = lis[j] + 1;
                }
            }
        }
        return lis;
    }

    // Функция за намиране на LDS (Longest Decreasing Subsequence)
    private static int[] longestDecreasingSubsequence(int[] arr) {
        int n = arr.length;
        int[] lds = new int[n];
        Arrays.fill(lds, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] < arr[j] && lds[i] < lds[j] + 1) {
                    lds[i] = lds[j] + 1;
                }
            }
        }
        return lds;
    }
}
