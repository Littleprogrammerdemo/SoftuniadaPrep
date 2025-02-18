package softuniada_2021;
import java.util.Scanner;
public class _02_EasterEggs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();

        for (int row = n; row >= 1; row--) {
            // Добавяме отстъпи за центриране
            System.out.print(" ".repeat(n - row));

            // Генерираме числовия шаблон за текущия ред
            for (int i = 1; i <= row; i++) {
                System.out.print(i);
            }
            for (int i = row - 1; i >= 1; i--) {
                System.out.print(i);
            }

            // Нов ред за следващия ред в триъгълника
            System.out.println();
        }
    }
}
