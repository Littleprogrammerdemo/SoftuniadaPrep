package softuniada_2017;
import java.util.Scanner;
public class _01_SumTo13 {
    public static void main(String[] args) {
        // Създаваме скенер за въвеждане на данни
        Scanner scanner = new Scanner(System.in);

        // Четем три числа
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();

        // Пробваме всички възможни комбинации от знаци
        boolean possible = false;
        for (int signA : new int[]{-1, 1}) {
            for (int signB : new int[]{-1, 1}) {
                for (int signC : new int[]{-1, 1}) {
                    // Изчисляваме сумата със съответните знаци
                    int sum = signA * a + signB * b + signC * c;
                    if (sum == 13) {
                        possible = true;
                        break;
                    }
                }
                if (possible) break;
            }
            if (possible) break;
        }

        // Извеждаме резултата
        if (possible) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        // Затваряме скенера
        scanner.close();
    }
}
