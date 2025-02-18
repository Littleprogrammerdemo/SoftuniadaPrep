package softuniada_2022;
import java.util.*;

public class _08_Satelites {
    static Map<String, Set<String>> graph = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = Integer.parseInt(scanner.nextLine()); // Брой връзки

        // Четене на връзките и изграждане на графа
        for (int i = 0; i < N; i++) {
            String[] satellites = scanner.nextLine().split(" ");
            String sat1 = satellites[0], sat2 = satellites[1];

            graph.putIfAbsent(sat1, new HashSet<>());
            graph.putIfAbsent(sat2, new HashSet<>());

            graph.get(sat1).add(sat2);
            graph.get(sat2).add(sat1);
        }

        Set<Set<String>> nexuses = new HashSet<>();

        // Търсим всички възможни нексуси
        for (String sat : graph.keySet()) {
            if (graph.get(sat).size() == 3) {
                List<String> neighbors = new ArrayList<>(graph.get(sat));
                if (graph.get(neighbors.get(0)).contains(neighbors.get(1)) &&
                        graph.get(neighbors.get(1)).contains(neighbors.get(2)) &&
                        graph.get(neighbors.get(2)).contains(neighbors.get(0))) {

                    Set<String> nexus = new TreeSet<>(Arrays.asList(sat, neighbors.get(0), neighbors.get(1), neighbors.get(2)));
                    nexuses.add(nexus);
                }
            }
        }

        // Намираме най-малкия нексус по размер и азбучен ред
        Set<String> smallestNexus = null;
        for (Set<String> nexus : nexuses) {
            if (smallestNexus == null || nexus.size() < smallestNexus.size() ||
                    (nexus.size() == smallestNexus.size() && String.join("", nexus).compareTo(String.join("", smallestNexus)) < 0)) {
                smallestNexus = nexus;
            }
        }

        // Отпечатваме резултата
        if (smallestNexus != null) {
            List<String> sortedNexus = new ArrayList<>(smallestNexus);
            Collections.sort(sortedNexus);
            for (String sat : sortedNexus) {
                for (String neighbor : graph.get(sat)) {
                    if (smallestNexus.contains(neighbor)) {
                        System.out.println(sat + " <-> " + neighbor);
                    }
                }
            }
        }
    }
}
