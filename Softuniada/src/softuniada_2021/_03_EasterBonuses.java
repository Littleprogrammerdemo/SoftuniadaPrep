package softuniada_2021;
import java.util.*;
public class _03_EasterBonuses {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> bonuses = new LinkedHashMap<>();
        int totalBonuses = 0;

        while (true) {
            String name = scanner.nextLine();
            if (name.equals("stop")) break;

            String[] tokens = scanner.nextLine().split(", ");
            int[] numbers = Arrays.stream(tokens).mapToInt(Integer::parseInt).toArray();

            int totalProduct = 1;
            int zeroCount = 0;

            for (int num : numbers) {
                if (num != 0) {
                    totalProduct *= num;
                } else {
                    zeroCount++;
                }
            }

            int sumBonus = 0;
            for (int num : numbers) {
                if (zeroCount > 1) {
                    sumBonus += 0;
                } else if (zeroCount == 1) {
                    sumBonus += (num == 0) ? totalProduct : 0;
                } else {
                    sumBonus += totalProduct / num;
                }
            }

            bonuses.put(name, sumBonus);
            totalBonuses += sumBonus;
        }

        bonuses.forEach((name, bonus) ->
                System.out.println(name + " has a bonus of " + bonus + " lv."));
        System.out.println("The amount of all bonuses is " + totalBonuses + " lv.");
    }
}
