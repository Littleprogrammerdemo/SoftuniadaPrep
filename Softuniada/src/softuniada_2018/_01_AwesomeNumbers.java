package softuniada_2018;
import java.util.Scanner;
public class _01_AwesomeNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Четем входните числа
        int number = scanner.nextInt();
        int favoriteNumber = scanner.nextInt();

        // Изменливи за броене на условията
        int conditionCount = 0;

        // Проверяваме условията
        if (number % 2 != 0) { // Нечетно число
            conditionCount++;
        }

        if (number < 0) { // Отрицателно число
            conditionCount++;
        }

        if (number % favoriteNumber == 0) { // Делимост без остатък
            conditionCount++;
        }

        // Въз основа на броя на изпълнените условия, извеждаме резултата
        if (conditionCount == 0) {
            System.out.println("boring");
        } else if (conditionCount == 1) {
            System.out.println("awesome");
        } else if (conditionCount == 2) {
            System.out.println("super awesome");
        } else if (conditionCount == 3) {
            System.out.println("super special awesome");
        }

        scanner.close();
    }
}
