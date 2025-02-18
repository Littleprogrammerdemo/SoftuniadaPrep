package softuniada_2022;
import java.util.*;
public class _10_AmongUs {
    private static final int MODULO = 1000003;

    // Function to calculate factorial % MODULO
    private static long factorial(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result = (result * i) % MODULO;
        }
        return result;
    }

    // Function to calculate modular inverse using Fermat's little theorem
    private static long modInverse(long a, int mod) {
        return power(a, mod - 2, mod);
    }

    // Function to compute (base^exp) % mod using fast exponentiation
    private static long power(long base, int exp, int mod) {
        long result = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }

    // Function to compute nCr % MODULO
    private static long binomialCoefficient(int n, int r) {
        if (r > n) return 0;
        long numerator = factorial(n);
        long denominator = (factorial(r) * factorial(n - r)) % MODULO;
        return (numerator * modInverse(denominator, MODULO)) % MODULO;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int K = scanner.nextInt();
        scanner.close();

        if (K * 2 < N) {
            System.out.println(0);
            return;
        }

        long totalWays = 0;

        // Try every possible number of people from group 1 (i) and group 2 (N - i)
        for (int i = 0; i <= N; i++) {
            int group1 = i;
            int group2 = N - i;
            if (group1 <= K && group2 <= K) {
                long ways = (binomialCoefficient(N, group1)) % MODULO;
                totalWays = (totalWays + ways) % MODULO;
            }
        }

        // Divide by N to eliminate rotational duplicates (Burnside's Lemma)
        totalWays = (totalWays * modInverse(N, MODULO)) % MODULO;

        System.out.println(totalWays);
    }
}
