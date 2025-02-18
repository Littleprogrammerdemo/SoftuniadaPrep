package softuniada_2024;
import java.util.*;
public class _07_SpecialNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Въвеждаме стойностите на N и M
        int N = scanner.nextInt();
        int M = scanner.nextInt();

        // Обхождаме всички числа от N до M
        for (int i = N; i <= M; i++) {
            if (isSpecialNumber(i)) {
                System.out.println(i);
            }
        }
    }

    // Функция за проверка дали числото е специално
    public static boolean isSpecialNumber(int num) {
        String numStr = String.valueOf(num); // Преобразуваме числото в низ
        for (int i = 0; i < numStr.length() - 1; i++) {
            int digit1 = numStr.charAt(i) - '0'; // Взимаме цифрата на първата позиция
            int digit2 = numStr.charAt(i + 1) - '0'; // Взимаме цифрата на следващата позиция
            if (Math.abs(digit1 - digit2) != 1) { // Проверяваме дали разликата е 1
                return false; // Ако не е, числото не е специално
            }
        }
        return true; // Ако всички разлики между съседни цифри са 1, числото е специално
    }
}