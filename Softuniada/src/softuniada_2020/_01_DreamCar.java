package softuniada_2020;
import java.util.Scanner;
public class _01_DreamCar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Четем входните данни
        double N = scanner.nextDouble(); // Стартова заплата
        double M = scanner.nextDouble(); // Месечни разходи
        double X = scanner.nextDouble(); // Увеличение на заплатата всеки месец
        double Y = scanner.nextDouble(); // Цена на колата
        int T = scanner.nextInt();       // Брой месеци

        double savings = 0; // Спестени пари

        // Изчисляваме спестяванията за T месеца
        for (int i = 0; i < T; i++) {
            savings += (N - M); // Заплатата минус разходите
            N += X; // Увеличение на заплатата
        }

        // Проверяваме дали събраните пари са достатъчни
        if (savings >= Y) {
            System.out.println("Have a nice ride!");
        } else {
            System.out.println("Work harder!");
        }

        scanner.close();
    }
}
