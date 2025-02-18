package softuniada_2018;
import java.util.*;
public class _07_Car {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int C = sc.nextInt();  // Броят на промените в скоростта
        int[] changes = new int[C];  // Промените в скоростта
        for (int i = 0; i < C; i++) {
            changes[i] = sc.nextInt();
        }

        int B = sc.nextInt();  // Първоначална скорост
        int M = sc.nextInt();  // Максимална скорост

        // Множество от възможни скорости след всяка промяна
        Set<Integer> possibleSpeeds = new HashSet<>();
        possibleSpeeds.add(B);

        // За всяка промяна на скоростта
        for (int i = 0; i < C; i++) {
            Set<Integer> nextSpeeds = new HashSet<>();
            for (int speed : possibleSpeeds) {
                int newSpeed1 = speed + changes[i];
                int newSpeed2 = speed - changes[i];

                if (newSpeed1 >= 0 && newSpeed1 <= M) nextSpeeds.add(newSpeed1);
                if (newSpeed2 >= 0 && newSpeed2 <= M) nextSpeeds.add(newSpeed2);
            }

            possibleSpeeds = nextSpeeds;
            if (possibleSpeeds.isEmpty()) {
                System.out.println(-1);
                return;
            }
        }

        // Най-голямата възможна скорост
        System.out.println(Collections.max(possibleSpeeds));
    }
}
