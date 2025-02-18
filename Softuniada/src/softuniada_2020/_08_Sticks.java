package softuniada_2020;
import java.util.*;
public class _08_Sticks {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        List<int[]> sticks = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] parts = scanner.nextLine().split(" ");
            int a = Integer.parseInt(parts[0]);
            int b = Integer.parseInt(parts[1]);
            sticks.add(new int[]{Math.min(a, b), Math.max(a, b)});
        }

        Set<String> uniqueArrangements = new TreeSet<>();
        permute(sticks, 0, uniqueArrangements);

        System.out.println(uniqueArrangements.size());
        for (String arrangement : uniqueArrangements) {
            System.out.println(arrangement);
        }
    }

    private static void permute(List<int[]> sticks, int index, Set<String> uniqueArrangements) {
        if (index == sticks.size()) {
            uniqueArrangements.add(formatSticks(sticks));
            return;
        }

        for (int i = index; i < sticks.size(); i++) {
            Collections.swap(sticks, i, index);

            permute(sticks, index + 1, uniqueArrangements);

            int[] stick = sticks.get(index);
            int[] flipped = new int[]{stick[1], stick[0]};
            sticks.set(index, flipped);
            permute(sticks, index + 1, uniqueArrangements);
            sticks.set(index, stick);

            Collections.swap(sticks, i, index);
        }
    }

    private static String formatSticks(List<int[]> sticks) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sticks.size(); i++) {
            sb.append("|").append(sticks.get(i)[0]).append("-").append(sticks.get(i)[1]).append("|");
            if (i < sticks.size() - 1) {
                sb.append(" # ");
            }
        }
        return sb.toString();
    }
}
