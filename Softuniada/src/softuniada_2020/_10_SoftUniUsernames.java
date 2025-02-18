package softuniada_2020;
import java.util.Scanner;
public class _10_SoftUniUsernames {
    static final int MOD = 1_000_000_007;

    // Бързо модулно степенуване (a^b % mod)
    static long modPow(long a, long b, long mod) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) res = res * a % mod;
            a = a * a % mod;
            b >>= 1;
        }
        return res;
    }

    // Факториел по модул
    static long[] fact, invFact;

    static void precomputeFactorials(int n) {
        fact = new long[n + 1];
        invFact = new long[n + 1];

        fact[0] = invFact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }
        invFact[n] = modPow(fact[n], MOD - 2, MOD);
        for (int i = n - 1; i > 0; i--) {
            invFact[i] = invFact[i + 1] * (i + 1) % MOD;
        }
        invFact[0] = 1;
    }

    // Комбинации C(n, k) по модул
    static long comb(int n, int k) {
        if (k > n || k < 0) return 0;
        return fact[n] * invFact[k] % MOD * invFact[n - k] % MOD;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt(); // Дължина на името
        int D = scanner.nextInt(); // Минимум цифри
        int L = scanner.nextInt(); // Минимум малки букви
        int U = scanner.nextInt(); // Минимум главни букви
        scanner.close();

        if (D + L + U > N) {
            System.out.println(0);
            return;
        }

        precomputeFactorials(N);

        long result = 0;
        for (int d = D; d <= N; d++) {
            for (int l = L; l <= N - d; l++) {
                int u = N - d - l;
                if (u < U) continue;

                long ways = comb(N, d) * comb(N - d, l) % MOD * comb(N - d - l, u) % MOD;
                ways = ways * modPow(10, d, MOD) % MOD;
                ways = ways * modPow(30, l, MOD) % MOD;
                ways = ways * modPow(30, u, MOD) % MOD;

                result = (result + ways) % MOD;
            }
        }

        System.out.println(result);
    }
}
