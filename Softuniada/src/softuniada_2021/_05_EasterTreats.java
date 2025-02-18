package softuniada_2021;
import java.util.*;
public class _05_EasterTreats {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Четем входа
        String[] inputNumbers = scanner.nextLine().split(", ");
        int k = Integer.parseInt(scanner.nextLine());

        List<Integer> nums = new ArrayList<>();
        for (String num : inputNumbers) {
            nums.add(Integer.parseInt(num));
        }

        // Проверка дали можем да разделим лакомствата в равни опаковки
        String result = canPartition(nums, k);
        System.out.println(result);
    }

    private static String canPartition(List<Integer> nums, int k) {
        int totalSum = nums.stream().mapToInt(Integer::intValue).sum();

        if (totalSum % k != 0) {
            return "Packaging is not possible!";
        }

        int target = totalSum / k;
        nums.sort(Collections.reverseOrder()); // Сортиране за оптимизация

        int[] packages = new int[k];

        if (!backtrack(nums, packages, 0, target)) {
            return "Packaging is not possible!";
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < k; i++) {
            result.append("Package ").append(i).append(" is \n");
            for (int j = 0; j < nums.size(); j++) {
                if (packages[j] == i) {
                    result.append(nums.get(j)).append("\n");
                }
            }
            result.append("\n");
        }
        return result.toString();
    }

    private static boolean backtrack(List<Integer> nums, int[] packages, int index, int target) {
        if (index == nums.size()) {
            return true;
        }

        int num = nums.get(index);
        for (int i = 0; i < packages.length; i++) {
            if (packages[i] + num <= target) {
                packages[i] += num;
                if (backtrack(nums, packages, index + 1, target)) {
                    return true;
                }
                packages[i] -= num;
            }
            if (packages[i] == 0) break; // Оптимизация: ако първата кутия е празна, няма смисъл да пробваме следващите
        }
        return false;
    }
}
