package softuniada_2020;
import java.util.*;
public class _03_DeckShuffle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        List<Integer> deck = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            deck.add(i);
        }

        scanner.nextLine(); // Move to the next line
        String[] indices = scanner.nextLine().split(" ");

        for (String index : indices) {
            int X = Integer.parseInt(index);
            deck = shuffle(deck, X);
        }

        for (int card : deck) {
            System.out.print(card + " ");
        }
    }

    private static List<Integer> shuffle(List<Integer> deck, int X) {
        List<Integer> left = new ArrayList<>(deck.subList(0, X));
        List<Integer> right = new ArrayList<>(deck.subList(X, deck.size()));

        List<Integer> shuffled = new ArrayList<>();
        int i = 0, j = 0;
        while (i < left.size() || j < right.size()) {
            if (i < left.size()) {
                shuffled.add(left.get(i++));
            }
            if (j < right.size()) {
                shuffled.add(right.get(j++));
            }
        }
        return shuffled;
    }
}
