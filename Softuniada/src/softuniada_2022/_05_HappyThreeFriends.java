package softuniada_2022;
import java.util.*;
public class _05_HappyThreeFriends {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cases = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < cases; i++) {
            int[] bottles = Arrays.stream(scanner.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            System.out.println(canDivideEqually(bottles) ? "Yes" : "No");
        }
    }

    private static boolean canDivideEqually(int[] bottles) {
        int totalSum = Arrays.stream(bottles).sum();
        if (totalSum % 3 != 0) return false;

        int target = totalSum / 3;
        return canPartition(bottles, 0, 0, 0, target);
    }

    private static boolean canPartition(int[] bottles, int index, int sum1, int sum2, int target) {
        if (index == bottles.length) {
            return sum1 == target && sum2 == target;
        }

        int value = bottles[index];

        // Опитваме да добавим бутилката към първата група
        if (sum1 + value <= target && canPartition(bottles, index + 1, sum1 + value, sum2, target)) {
            return true;
        }

        // Опитваме да добавим бутилката към втората група
        if (sum2 + value <= target && canPartition(bottles, index + 1, sum1, sum2 + value, target)) {
            return true;
        }

        // Опитваме да добавим бутилката към третата група (implicit)
        return sum1 == target && sum2 == target;
    }
}
