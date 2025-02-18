package softuniada_2019;
import java.util.*;
public class _08_Rooks {
    static final int MODULO = 1_000_001;
    static long[] fact;

    // Compute factorials modulo MODULO
    static void computeFactorials(int maxN) {
        fact = new long[maxN + 1];
        fact[0] = 1;
        for (int i = 1; i <= maxN; i++) {
            fact[i] = (fact[i - 1] * i) % MODULO;
        }
    }

    // Modular exponentiation (a^b % mod) using fast exponentiation
    static long modPow(long base, long exp, long mod) {
        long result = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) result = (result * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }

    // Compute modular inverse using Fermat's theorem
    static long modInverse(long num, int mod) {
        return modPow(num, mod - 2, mod);
    }

    // Compute nCr % MODULO
    static long combination(int n, int r) {
        if (r > n) return 0;
        return (fact[n] * modInverse((fact[r] * fact[n - r]) % MODULO, MODULO)) % MODULO;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int X = scanner.nextInt();
        int Y = scanner.nextInt();
        int N = scanner.nextInt();
        scanner.close();

        if (N > X || N > Y) {
            System.out.println(0);
            return;
        }

        // Precompute factorials
        computeFactorials(Math.max(X, Y));

        // Compute C(X, N) * C(Y, N) * N! % MODULO
        long result = (combination(X, N) * combination(Y, N)) % MODULO;
        result = (result * fact[N]) % MODULO;

        System.out.println(result);
    }
}
