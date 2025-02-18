package softuniada_2021;
import java.util.*;
public class _07_Yacht {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int P = Integer.parseInt(scanner.nextLine()); // Брой промени в скоростта
        int[] changes = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray(); // Промени в скоростта
        int V = Integer.parseInt(scanner.nextLine()); // Начална скорост
        int M = Integer.parseInt(scanner.nextLine()); // Максимална скорост

        int result = getMaxSpeed(P, changes, V, M);
        System.out.println(result);
    }

    private static int getMaxSpeed(int P, int[] changes, int initialSpeed, int maxSpeed) {
        Set<Integer> possibleSpeeds = new HashSet<>();
        possibleSpeeds.add(initialSpeed);

        for (int change : changes) {
            Set<Integer> newSpeeds = new HashSet<>();

            for (int speed : possibleSpeeds) {
                int increased = speed + change;
                int decreased = speed - change;

                if (increased <= maxSpeed) {
                    newSpeeds.add(increased);
                }
                if (decreased >= 0) {
                    newSpeeds.add(decreased);
                }
            }

            if (newSpeeds.isEmpty()) {
                return -1; // Ако няма валидни скорости, значи не можем да изпълним завоите
            }
            possibleSpeeds = newSpeeds;
        }

        return Collections.max(possibleSpeeds); // Връщаме максималната възможна скорост
    }
}
