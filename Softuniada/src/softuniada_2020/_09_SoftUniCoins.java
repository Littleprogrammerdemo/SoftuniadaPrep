package softuniada_2020;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class _09_SoftUniCoins {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long P = scanner.nextLong();
        scanner.close();

        ArrayList<Long> coins = new ArrayList<>();
        long k = 0;

        // Генериране на възможните монети
        while (true) {
            long coin1 = (long) Math.pow(10, k);
            long coin2 = 25 * (long) Math.pow(100, k);

            if (coin1 > P && coin2 > P) {
                break;
            }

            if (coin1 <= P) {
                coins.add(coin1);
            }
            if (coin2 <= P) {
                coins.add(coin2);
            }

            k++;
        }

        // Сортиране в низходящ ред
        Collections.sort(coins, Collections.reverseOrder());

        // Жаден алгоритъм за минимален брой монети
        long count = 0;
        for (long coin : coins) {
            if (P == 0) {
                break;
            }
            count += P / coin;
            P %= coin;
        }

        System.out.println(count);
    }
}
