package softuniada_2017;
import java.util.*;
public class _08_ColorCoding {

    // Helper function to check if a color is full or half
    public static String toFullColor(String color) {
        if (color.startsWith("(") && color.endsWith(")")) {
            return color.substring(1, color.length() - 1); // remove the parentheses
        }
        return color;
    }

    // Function to check if the first sequence can be transformed to the second
    public static boolean canTransform(String[] first, String[] second) {
        int i = 0, j = 0;

        while (i < first.length && j < second.length) {
            String currentFirst = toFullColor(first[i]); // Convert the current color to full
            if (currentFirst.equals(second[j])) {
                // If they match, move both pointers
                j++;
            }
            // Always move the pointer for the first sequence
            i++;
        }

        // If we've matched all elements of the second sequence, return true
        return j == second.length;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();  // Read number of test cases
        sc.nextLine();  // Consume the newline after the integer input

        for (int i = 0; i < n; i++) {
            // Read the two sequences
            String[] first = sc.nextLine().split(" ");
            String[] second = sc.nextLine().split(" ");

            // Check if we can transform the first sequence to the second
            if (canTransform(first, second)) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }
        }

        sc.close();
    }
}