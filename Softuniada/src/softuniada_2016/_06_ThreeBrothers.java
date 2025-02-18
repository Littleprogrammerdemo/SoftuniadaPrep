package softuniada_2016;
import java.util.*;
public class _06_ThreeBrothers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim()); // Number of test cases

        for (int i = 0; i < n; i++) {
            int[] presents = Arrays.stream(scanner.nextLine().trim().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            System.out.println(canSplitIntoThreeEqualParts(presents) ? "Yes" : "No");
        }

        scanner.close();
    }

    private static boolean canSplitIntoThreeEqualParts(int[] presents) {
        int sum = Arrays.stream(presents).sum();

        // If sum is not divisible by 3, a fair split is impossible
        if (sum % 3 != 0) return false;

        int target = sum / 3;
        Arrays.sort(presents); // Sorting helps optimize backtracking

        return canPartition(presents, presents.length - 1, new int[3], target);
    }

    private static boolean canPartition(int[] presents, int index, int[] partitions, int target) {
        if (index < 0) {
            return partitions[0] == target && partitions[1] == target && partitions[2] == target;
        }

        int present = presents[index];

        // Try adding the present to one of the three partitions
        for (int i = 0; i < 3; i++) {
            if (partitions[i] + present <= target) {
                partitions[i] += present;

                if (canPartition(presents, index - 1, partitions, target)) {
                    return true;
                }

                partitions[i] -= present; // Backtrack
            }

            // Optimization: if the first partition is empty, avoid redundant checks
            if (partitions[i] == 0) break;
        }

        return false;
    }
}
