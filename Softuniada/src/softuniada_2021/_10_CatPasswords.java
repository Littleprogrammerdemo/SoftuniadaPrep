package softuniada_2021;
import java.util.Scanner;
public class _10_CatPasswords {
    static final int MODULO = 1_000_000_007;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int P = Integer.parseInt(scanner.nextLine()); // Дължина на паролата
        int C = Integer.parseInt(scanner.nextLine()); // Минимален брой цифри
        int M = Integer.parseInt(scanner.nextLine()); // Минимален брой малки букви
        int G = Integer.parseInt(scanner.nextLine()); // Минимален брой главни букви

        int other = P - (C + M + G); // Оставащи свободни позиции
        if (other < 0) {
            System.out.println(0); // Невъзможен случай
            return;
        }

        // Изчисляваме броя на разпределенията: P! / (C! * M! * G! * other!)
        long waysToArrange = factorialMod(P);
        waysToArrange = (waysToArrange * modInverse(factorialMod(C))) % MODULO;
        waysToArrange = (waysToArrange * modInverse(factorialMod(M))) % MODULO;
        waysToArrange = (waysToArrange * modInverse(factorialMod(G))) % MODULO;
        waysToArrange = (waysToArrange * modInverse(factorialMod(other))) % MODULO;

        // Брой начини за попълване на символите
        long numberChoices = (powMod(10, C) * powMod(30, M) % MODULO) * powMod(30, G) % MODULO;
        numberChoices = (numberChoices * powMod(70, other)) % MODULO;

        // Финален резултат
        long result = (waysToArrange * numberChoices) % MODULO;
        System.out.println(result);
    }

    // Факториел по модул MODULO
    private static long factorialMod(int n) {
        long res = 1;
        for (int i = 2; i <= n; i++) {
            res = (res * i) % MODULO;
        }
        return res;
    }

    // Бързо степенуване (a^b % MODULO)
    private static long powMod(long base, int exp) {
        long result = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % MODULO;
            }
            base = (base * base) % MODULO;
            exp >>= 1;
        }
        return result;
    }

    // Модулно обратен елемент (x^-1 % MODULO) чрез Ферма
    private static long modInverse(long x) {
        return powMod(x, MODULO - 2);
    }
}
