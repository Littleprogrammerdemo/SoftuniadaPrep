package softuniada_2020;
import java.util.*;

public class _06_MinInequality {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        int n = scanner.nextInt();
        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }

        Arrays.sort(numbers);  // Сортираме списъка

        int minDifference = Integer.MAX_VALUE;
        for (int i = 0; i <= n - k; i++) {
            int diff = numbers[i + k - 1] - numbers[i];  // Разлика между макс и мин в подмасива
            minDifference = Math.min(minDifference, diff);
        }

        System.out.println(minDifference);
        scanner.close();
    }
}
