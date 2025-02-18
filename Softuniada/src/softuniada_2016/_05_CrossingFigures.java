package softuniada_2016;
import java.util.*;
public class _05_CrossingFigures {
    static class Rectangle {
        double Ax, Ay, Bx, By;

        Rectangle(double Ax, double Ay, double Bx, double By) {
            this.Ax = Ax;
            this.Ay = Ay;
            this.Bx = Bx;
            this.By = By;
        }

        // Get rectangle's four corners
        double[][] getCorners() {
            return new double[][] {
                    {Ax, Ay}, {Ax, By}, {Bx, Ay}, {Bx, By}
            };
        }

        // Checks if a point is inside the rectangle
        boolean contains(double x, double y) {
            return (Math.min(Ax, Bx) <= x && x <= Math.max(Ax, Bx)) &&
                    (Math.min(Ay, By) <= y && y <= Math.max(Ay, By));
        }
    }

    static class Circle {
        double Ox, Oy, R;

        Circle(double Ox, double Oy, double R) {
            this.Ox = Ox;
            this.Oy = Oy;
            this.R = R;
        }

        // Checks if a point is inside the circle
        boolean contains(double x, double y) {
            return distance(x, y, Ox, Oy) <= R;
        }
    }

    // Calculate Euclidean distance
    static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = Integer.parseInt(scanner.nextLine().trim()); // Number of test cases

        for (int i = 0; i < T; i++) {
            Object shape1 = parseShape(scanner.nextLine());
            Object shape2 = parseShape(scanner.nextLine());

            Rectangle rect = null;
            Circle circ = null;

            if (shape1 instanceof Rectangle) rect = (Rectangle) shape1;
            if (shape1 instanceof Circle) circ = (Circle) shape1;
            if (shape2 instanceof Rectangle) rect = (Rectangle) shape2;
            if (shape2 instanceof Circle) circ = (Circle) shape2;

            if (rect == null || circ == null) continue;

            System.out.println(determinePosition(rect, circ));
        }

        scanner.close();
    }

    static Object parseShape(String input) {
        String[] parts = input.replaceAll("[^0-9,.-]", "").split(",");
        double[] values = Arrays.stream(parts).mapToDouble(Double::parseDouble).toArray();

        if (input.startsWith("rectangle")) {
            return new Rectangle(values[0], values[1], values[2], values[3]);
        } else {
            return new Circle(values[0], values[1], values[2]);
        }
    }

    static String determinePosition(Rectangle rect, Circle circ) {
        boolean rectInsideCircle = true, circleInsideRect = true, crosses = false;

        // Check if all rectangle corners are inside the circle
        for (double[] corner : rect.getCorners()) {
            if (!circ.contains(corner[0], corner[1])) {
                rectInsideCircle = false;
            }
        }

        // Check if the entire circle is inside the rectangle
        if (!rect.contains(circ.Ox + circ.R, circ.Oy) ||
                !rect.contains(circ.Ox - circ.R, circ.Oy) ||
                !rect.contains(circ.Ox, circ.Oy + circ.R) ||
                !rect.contains(circ.Ox, circ.Oy - circ.R)) {
            circleInsideRect = false;
        }

        // Check if the rectangle and circle intersect
        for (double[] corner : rect.getCorners()) {
            if (circ.contains(corner[0], corner[1])) {
                crosses = true;
                break;
            }
        }

        // Check if any rectangle side intersects with the circle
        if (!crosses) {
            double minX = Math.min(rect.Ax, rect.Bx), maxX = Math.max(rect.Ax, rect.Bx);
            double minY = Math.min(rect.Ay, rect.By), maxY = Math.max(rect.Ay, rect.By);

            if (circ.Ox >= minX && circ.Ox <= maxX) {
                if (distance(circ.Ox, minY, circ.Ox, circ.Oy) <= circ.R ||
                        distance(circ.Ox, maxY, circ.Ox, circ.Oy) <= circ.R) {
                    crosses = true;
                }
            }

            if (circ.Oy >= minY && circ.Oy <= maxY) {
                if (distance(minX, circ.Oy, circ.Ox, circ.Oy) <= circ.R ||
                        distance(maxX, circ.Oy, circ.Ox, circ.Oy) <= circ.R) {
                    crosses = true;
                }
            }
        }

        if (rectInsideCircle) return "Rectangle inside circle";
        if (circleInsideRect) return "Circle inside rectangle";
        if (crosses) return "Rectangle and circle cross";
        return "Rectangle and circle do not cross";
    }
}