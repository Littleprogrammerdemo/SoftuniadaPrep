package softuniada_2021;
import java.util.*;
public class _09_BinarySorting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int a = Integer.parseInt(scanner.nextLine()); // Брой нули
        int b = Integer.parseInt(scanner.nextLine()); // Брой единици
        long n = Long.parseLong(scanner.nextLine()); // Търсеният индекс

        System.out.println(findNthBinaryString(a, b, n));
    }

    // Функция за намиране на n-тото число с a нули и b единици
    private static String findNthBinaryString(int zeros, int ones, long n) {
        StringBuilder result = new StringBuilder();

        while (zeros > 0 || ones > 0) {
            // Ако няма останали нули, добавяме 1
            if (zeros == 0) {
                result.append("1");
                ones--;
                continue;
            }
            // Ако няма останали единици, добавяме 0
            if (ones == 0) {
                result.append("0");
                zeros--;
                continue;
            }

            // Брой комбинации, които започват с 0
            long count = binomial(zeros + ones - 1, zeros - 1);

            if (n <= count) {
                result.append("0");
                zeros--;
            } else {
                result.append("1");
                ones--;
                n -= count;
            }
        }

        return result.toString();
    }

    // Функция за изчисляване на биномиален коефициент C(n, k)
    private static long binomial(int n, int k) {
        if (k > n || k < 0) return 0;
        if (k == 0 || k == n) return 1;

        long res = 1;
        for (int i = 1; i <= k; i++) {
            res = res * (n - i + 1) / i;
        }
        return res;
    }
}
