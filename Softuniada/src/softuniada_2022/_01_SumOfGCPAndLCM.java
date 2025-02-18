package softuniada_2022;
import java.util.Scanner;
public class _01_SumOfGCPAndLCM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();

        int gcd = findGCD(N, M);
        int lcm = (N * M) / gcd;

        System.out.println(gcd + lcm);
    }

    private static int findGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
