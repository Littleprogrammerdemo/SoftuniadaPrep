package softuniada_2024;
import java.util.Arrays;
import java.util.Scanner;
public class _03_LargestNumbers {
    public static void main(String[] args) {
        // Скенер за въвеждане на входа
        Scanner scanner = new Scanner(System.in);

        // Четем масива от цели числа
        String[] input = scanner.nextLine().split(" ");
        int[] numbers = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            numbers[i] = Integer.parseInt(input[i]);
        }

        // Четем стойността на K
        int K = Integer.parseInt(scanner.nextLine());

        // Сортираме масива в низходящ ред, за да вземем най-големите K числа
        Arrays.sort(numbers);

        // Извличаме последните K числа от сортирания масив (най-големите)
        int[] largestK = new int[K];
        for (int i = 0; i < K; i++) {
            largestK[i] = numbers[numbers.length - 1 - i];
        }

        // Сортираме тези K числа във възходящ ред
        Arrays.sort(largestK);

        // Отпечатваме най-големите K числа
        for (int num : largestK) {
            System.out.println(num);
        }

        // Затваряме скенера
        scanner.close();
    }
}
