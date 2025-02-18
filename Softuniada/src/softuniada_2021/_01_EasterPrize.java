package softuniada_2021;
import java.util.*;
public class _01_EasterPrize {
    // Функция за проверка дали число е просто
    private static boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int M = scanner.nextInt();
        scanner.close();

        List<Integer> primes = new ArrayList<>();

        for (int i = N; i <= M; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }

        // Отпечатване на всички прости числа
        for (int i = 0; i < primes.size(); i++) {
            System.out.print(primes.get(i) + (i < primes.size() - 1 ? " " : "\n"));
        }

        // Отпечатване на общия брой награди
        System.out.println("The total number of prime numbers between " + N + " to " + M + " is " + primes.size());
    }
}
