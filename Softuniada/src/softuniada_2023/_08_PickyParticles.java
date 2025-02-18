package softuniada_2023;
import java.util.*;
public class _08_PickyParticles {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        List<List<Integer>> protonPrefs = new ArrayList<>();
        List<List<Integer>> electronPrefs = new ArrayList<>();

        // Четем предпочитанията на протоните
        for (int i = 0; i < n; i++) {
            protonPrefs.add(parsePreferences(scanner.nextLine()));
        }

        // Четем предпочитанията на електроните
        for (int i = 0; i < n; i++) {
            electronPrefs.add(parsePreferences(scanner.nextLine()));
        }

        int[] electronPartners = new int[n]; // електрон -> протон
        int[] protonPartners = new int[n]; // протон -> електрон
        Arrays.fill(electronPartners, -1);
        Arrays.fill(protonPartners, -1);

        Queue<Integer> freeProtons = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            freeProtons.add(i);
        }

        // Създаваме рангове за предпочитанията на електроните
        int[][] electronRanks = new int[n][n];
        for (int e = 0; e < n; e++) {
            List<Integer> preferences = electronPrefs.get(e);
            for (int rank = 0; rank < n; rank++) {
                electronRanks[e][preferences.get(rank)] = rank;
            }
        }

        while (!freeProtons.isEmpty()) {
            int p = freeProtons.poll(); // Взимаме свободен протон

            for (int e : protonPrefs.get(p)) {
                int currentPartner = electronPartners[e];

                if (currentPartner == -1) {
                    // Ако електронът е свободен, сдвояваме го с протона
                    electronPartners[e] = p;
                    protonPartners[p] = e;
                    break;
                } else if (electronRanks[e][p] < electronRanks[e][currentPartner]) {
                    // Ако електронът предпочита този протон пред текущия си партньор
                    electronPartners[e] = p;
                    protonPartners[p] = e;
                    protonPartners[currentPartner] = -1;
                    freeProtons.add(currentPartner); // Освобождаваме стария протон
                    break;
                }
            }
        }

        // Извеждаме резултата, като подреждаме двойките по индекс на протона
        for (int p = 0; p < n; p++) {
            System.out.println(p + " <-> " + protonPartners[p]);
        }
    }

    private static List<Integer> parsePreferences(String input) {
        List<Integer> preferences = new ArrayList<>();
        String[] tokens = input.split("\\s+");
        for (String token : tokens) {
            preferences.add(Integer.parseInt(token));
        }
        return preferences;
    }
}
