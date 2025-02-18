package softuniada_2023;
import java.math.BigInteger;
import java.util.Scanner;
public class _01_RowInPascalsTriangle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();

        printPascalsTriangleRow(n);
    }

    private static void printPascalsTriangleRow(int n) {
        BigInteger[] row = new BigInteger[n + 1];
        row[0] = BigInteger.ONE;

        for (int i = 1; i <= n; i++) {
            row[i] = row[i - 1].multiply(BigInteger.valueOf(n - i + 1)).divide(BigInteger.valueOf(i));
        }

        for (int i = 0; i <= n; i++) {
            System.out.print(row[i] + (i < n ? " " : ""));
        }
    }
}
