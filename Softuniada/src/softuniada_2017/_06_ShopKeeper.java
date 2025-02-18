package softuniada_2017;
import java.util.*;
public class _06_ShopKeeper {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Set<Integer> stock = new HashSet<>();
        for (String s : scanner.nextLine().split(" ")) {
            stock.add(Integer.parseInt(s));
        }

        List<Integer> orders = new ArrayList<>();
        for (String o : scanner.nextLine().split(" ")) {
            orders.add(Integer.parseInt(o));
        }

        int changes = 0;
        for (int i = 0; i < orders.size(); i++) {
            int order = orders.get(i);
            if (!stock.contains(order)) {
                if (i == orders.size() - 1) { // Last order, no lookahead needed
                    System.out.println("impossible");
                    return;
                }
                // Look ahead to determine the best item to swap out
                Map<Integer, Integer> frequency = new HashMap<>();
                for (int j = i + 1; j < orders.size(); j++) {
                    frequency.put(orders.get(j), frequency.getOrDefault(orders.get(j), 0) + 1);
                }

                int leastNeeded = -1, minFreq = Integer.MAX_VALUE;
                for (int s : stock) {
                    int freq = frequency.getOrDefault(s, 0);
                    if (freq < minFreq) {
                        minFreq = freq;
                        leastNeeded = s;
                    }
                }

                if (leastNeeded == -1) {
                    System.out.println("impossible");
                    return;
                }

                stock.remove(leastNeeded);
                stock.add(order);
                changes++;
            }
        }

        System.out.println(changes);
    }
}
