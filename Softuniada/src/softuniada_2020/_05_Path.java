package softuniada_2020;
import java.util.Scanner;
import java.util.TreeSet;
public class _05_Path {
    static TreeSet<String> uniquePaths = new TreeSet<>();

    public static void generatePaths(char[] path, int index) {
        if (index == path.length) {
            uniquePaths.add(new String(path));
            return;
        }

        if (path[index] == '*') {
            for (char direction : new char[]{'S', 'L', 'R'}) {
                path[index] = direction;
                generatePaths(path, index + 1);
            }
            path[index] = '*'; // Връщаме обратно символа за следващата итерация
        } else {
            generatePaths(path, index + 1);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();

        generatePaths(input.toCharArray(), 0);

        System.out.println(uniquePaths.size());
        for (String path : uniquePaths) {
            System.out.println(path);
        }
    }
}
