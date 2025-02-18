package softuniada_2023;
import java.util.*;
public class _05_BiggestFormedNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] numbers = scanner.nextLine().split(" ");
        scanner.close();

        Arrays.sort(numbers, (a, b) -> (b + a).compareTo(a + b));

        // Ако най-голямото число е 0, връщаме "0"
        if (numbers[0].equals("0")) {
            System.out.println("0");
            return;
        }

        StringBuilder result = new StringBuilder();
        for (String num : numbers) {
            result.append(num);
        }

        System.out.println(result.toString());
    }
}
