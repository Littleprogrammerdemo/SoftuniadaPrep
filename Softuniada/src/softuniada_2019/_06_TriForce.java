package softuniada_2019;
import java.util.*;

public class _06_TriForce {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int P = Integer.parseInt(scanner.nextLine());
        double R = Double.parseDouble(scanner.nextLine());
        scanner.close();

        List<String> triangles = new ArrayList<>();

        for (int a = P / 2; a > 0; a--) {
            for (int b = a; b > 0; b--) {
                int c = P - a - b;
                if (c > 0 && isValidTriangle(a, b, c, R)) {
                    triangles.add(a + "." + b + "." + c);
                }
            }
        }

        for (String triangle : triangles) {
            System.out.println(triangle);
        }
    }

    private static boolean isValidTriangle(int a, int b, int c, double R) {
        if (a + b <= c || a + c <= b || b + c <= a) {
            return false;
        }

        // Calculate semi-perimeter
        double s = (a + b + c) / 2.0;

        // Calculate the area using Heron's formula
        double area = Math.sqrt(s * (s - a) * (s - b) * (s - c));

        // Calculate circumradius using R = (a * b * c) / (4 * area)
        double calculatedR = (a * b * c) / (4.0 * area);

        return Math.abs(calculatedR - R) < 1e-6;
    }
}
