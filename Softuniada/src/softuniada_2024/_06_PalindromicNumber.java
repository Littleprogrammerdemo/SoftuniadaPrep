package softuniada_2024;
import java.util.*;
public class _06_PalindromicNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Въвеждаме числото
        String input = scanner.nextLine();

        // Преброяваме честотата на всяка цифра
        int[] digitCount = new int[10];
        for (char c : input.toCharArray()) {
            digitCount[c - '0']++;
        }

        // Проверяваме дали може да се образува палиндром
        int oddCount = 0;
        StringBuilder leftPart = new StringBuilder();
        char middleDigit = 0;

        // За всяка цифра проверяваме колко пъти се среща
        for (int i = 9; i >= 0; i--) {
            int count = digitCount[i];
            if (count % 2 != 0) {
                oddCount++;
                middleDigit = (char) (i + '0'); // Ако има нечетна бройка, запазваме тази цифра за средата
            }

            // Добавяме половината от броя на цифрите към лявата част
            for (int j = 0; j < count / 2; j++) {
                leftPart.append(i);
            }
        }

        // Ако има повече от една нечетна цифра, не можем да създадем палиндром
        if (oddCount > 1) {
            System.out.println("No palindromic number available.");
            return;
        }

        // Сглобяваме най-голямото палиндромно число
        StringBuilder rightPart = new StringBuilder(leftPart).reverse();

        // Ако има нечетна цифра, тя ще бъде в средата
        if (middleDigit != 0) {
            System.out.println(leftPart.toString() + middleDigit + rightPart.toString());
        } else {
            System.out.println(leftPart.toString() + rightPart.toString());
        }
    }
}
