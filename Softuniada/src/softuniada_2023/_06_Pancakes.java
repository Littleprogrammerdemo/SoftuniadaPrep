package softuniada_2023;
import java.util.*;
public class _06_Pancakes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        scanner.close();

        int n = input.length;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        findMaxSubarray(arr);
    }

    private static void findMaxSubarray(int[] arr) {
        int maxSum = Integer.MIN_VALUE, currentSum = 0;
        int start = 0, end = 0, tempStart = 0;
        int maxLength = 0;

        for (int i = 0; i < arr.length; i++) {
            currentSum += arr[i];

            if (currentSum > maxSum || (currentSum == maxSum && (i - tempStart > maxLength))) {
                maxSum = currentSum;
                start = tempStart;
                end = i;
                maxLength = i - tempStart;
            }

            if (currentSum < 0) {
                currentSum = 0;
                tempStart = i + 1;
            }
        }

        System.out.println(maxSum + " " + start + " " + end);
    }
}
