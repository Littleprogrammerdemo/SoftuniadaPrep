package softuniada_2018;
import java.util.Scanner;
public class _05_CircumscribedCircle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String[] circleData = scanner.nextLine().split(", ");
            String[] triangleData = scanner.nextLine().split(", ");

            double Ox = Double.parseDouble(circleData[0].split(" ")[1]);
            double Oy = Double.parseDouble(circleData[1]);
            double R = Double.parseDouble(circleData[2]);

            double Ax = Double.parseDouble(triangleData[0].split(" ")[1]);
            double Ay = Double.parseDouble(triangleData[1]);
            double Bx = Double.parseDouble(triangleData[2]);
            double By = Double.parseDouble(triangleData[3]);
            double Cx = Double.parseDouble(triangleData[4]);
            double Cy = Double.parseDouble(triangleData[5]);

            boolean isCircumscribed = checkCircumscribed(Ox, Oy, R, Ax, Ay, Bx, By, Cx, Cy);
            boolean isCenterInside = isPointInsideTriangle(Ox, Oy, Ax, Ay, Bx, By, Cx, Cy);

            if (isCircumscribed && isCenterInside) {
                System.out.println("The circle is circumscribed and the center is inside.");
            } else if (isCircumscribed) {
                System.out.println("The circle is circumscribed and the center is outside.");
            } else if (isCenterInside) {
                System.out.println("The circle is not circumscribed and the center is inside.");
            } else {
                System.out.println("The circle is not circumscribed and the center is outside.");
            }
        }
        scanner.close();
    }

    private static boolean checkCircumscribed(double Ox, double Oy, double R,
                                              double Ax, double Ay, double Bx, double By, double Cx, double Cy) {
        double dA = distance(Ox, Oy, Ax, Ay);
        double dB = distance(Ox, Oy, Bx, By);
        double dC = distance(Ox, Oy, Cx, Cy);

        return Math.abs(dA - R) < 0.01 && Math.abs(dB - R) < 0.01 && Math.abs(dC - R) < 0.01;
    }

    private static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    private static boolean isPointInsideTriangle(double px, double py,
                                                 double Ax, double Ay, double Bx, double By, double Cx, double Cy) {
        double area = triangleArea(Ax, Ay, Bx, By, Cx, Cy);
        double area1 = triangleArea(px, py, Bx, By, Cx, Cy);
        double area2 = triangleArea(Ax, Ay, px, py, Cx, Cy);
        double area3 = triangleArea(Ax, Ay, Bx, By, px, py);

        return Math.abs(area - (area1 + area2 + area3)) < 0.01;
    }

    private static double triangleArea(double x1, double y1, double x2, double y2, double x3, double y3) {
        return Math.abs((x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2.0);
    }
}
