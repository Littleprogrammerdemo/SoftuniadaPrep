package softuniada_2019;

import java.util.*;

public class _07_Undefined {
    static class BusinessOwner {
        char owner;
        List<Integer> businesses = new ArrayList<>();
        List<String> pairs = new ArrayList<>();

        BusinessOwner(char owner) {
            this.owner = owner;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = Integer.parseInt(scanner.nextLine());
        Map<Character, BusinessOwner> owners = new LinkedHashMap<>();
        List<int[]> leftovers = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String[] parts = scanner.nextLine().split(" -> ");
            char owner = parts[0].charAt(0);
            BusinessOwner bo = new BusinessOwner(owner);
            for (String s : parts[1].split(", ")) {
                bo.businesses.add(Integer.parseInt(s));
            }
            owners.put(owner, bo);
        }
        scanner.close();

        int totalMinedValue = 0;
        for (BusinessOwner bo : owners.values()) {
            bo.businesses.sort(Collections.reverseOrder());
            while (bo.businesses.size() > 1) {
                int b1 = bo.businesses.remove(0);
                int b2 = bo.businesses.remove(0);
                int value = Math.abs(b1 - b2);
                totalMinedValue += value;
                bo.pairs.add(b1 + " <-> " + b2);
            }
            if (!bo.businesses.isEmpty()) {
                leftovers.add(new int[]{bo.owner, bo.businesses.get(0)});
            }
        }

        for (BusinessOwner bo : owners.values()) {
            System.out.print(bo.owner + " | ");
            if (bo.pairs.isEmpty()) {
                System.out.println("none");
            } else {
                System.out.println(String.join(", ", bo.pairs));
            }
        }

        if (leftovers.size() > 1) {
            leftovers.sort((a, b) -> Integer.compare(b[1] + b[1], a[1] + a[1]));
            for (int i = 0; i < leftovers.size() - 1; i += 2) {
                System.out.println((char) leftovers.get(i)[0] + "" + leftovers.get(i)[1] + " <-> " +
                        (char) leftovers.get(i + 1)[0] + "" + leftovers.get(i + 1)[1]);
            }
        }

        System.out.println(totalMinedValue);
    }
}
