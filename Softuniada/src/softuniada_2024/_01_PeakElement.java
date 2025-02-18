package softuniada_2024;
import java.util.Scanner;
public class _01_PeakElement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        String[] parts = input.split(" ");

        int[] nums = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            nums[i] = Integer.parseInt(parts[i]);
        }

        System.out.println(findPeak(nums));

        scanner.close();
    }

    public static int findPeak(int[] nums) {
        int peak = Integer.MIN_VALUE;

        if (nums.length == 1) {
            return nums[0];
        }
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                peak = Math.max(peak, nums[i]);
            }
        }
        if (nums[0] > nums[1]) {
            peak = Math.max(peak, nums[0]);
        }
        if (nums[nums.length - 1] > nums[nums.length - 2]) {
            peak = Math.max(peak, nums[nums.length - 1]);
        }

        return peak;
    }
}
