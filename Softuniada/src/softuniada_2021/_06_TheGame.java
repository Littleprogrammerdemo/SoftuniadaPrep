package softuniada_2021;
import java.util.*;
public class _06_TheGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String scrambledName = scanner.nextLine();
        String targetName = scanner.nextLine();

        int result = minOperations(scrambledName, targetName);

        if (result == -1) {
            System.out.println("The name cannot be transformed!");
        } else {
            System.out.printf("The minimum operations required to convert \"%s\" to \"%s\" are %d%n", scrambledName, targetName, result);
        }
    }

    private static int minOperations(String scrambled, String target) {
        if (!isAnagram(scrambled, target)) {
            return -1; // Ако двете думи нямат същите букви, няма решение
        }

        int operations = 0;
        List<Character> scrambledList = new LinkedList<>();
        for (char c : scrambled.toCharArray()) {
            scrambledList.add(c);
        }

        for (int i = target.length() - 1; i >= 0; i--) {
            char targetChar = target.charAt(i);
            int index = scrambledList.indexOf(targetChar);

            if (index == -1) return -1; // Няма такава буква, невъзможна трансформация

            operations += index; // Разместваме буквата отпред
            scrambledList.remove(index);
            scrambledList.add(0, targetChar); // Преместваме в началото
        }

        return operations;
    }

    private static boolean isAnagram(String a, String b) {
        char[] arrA = a.toCharArray();
        char[] arrB = b.toCharArray();
        Arrays.sort(arrA);
        Arrays.sort(arrB);
        return Arrays.equals(arrA, arrB);
    }
}
