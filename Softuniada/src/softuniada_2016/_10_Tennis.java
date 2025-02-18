package softuniada_2016;
import java.util.*;
public class _10_Tennis {

    static class Graph {
        private Map<String, List<String>> adjList = new HashMap<>();

        // Add connection between players
        public void addConnection(String player1, String player2) {
            adjList.putIfAbsent(player1, new ArrayList<>());
            adjList.putIfAbsent(player2, new ArrayList<>());
            adjList.get(player1).add(player2);
            adjList.get(player2).add(player1);
        }

        // Get all players (nodes)
        public Set<String> getPlayers() {
            return adjList.keySet();
        }

        // Get connections (edges) for a player
        public List<String> getConnections(String player) {
            return adjList.get(player);
        }
    }

    // Main logic for finding maximum matchings using DFS
    static class MaximumMatching {
        private Map<String, String> match = new HashMap<>();
        private Set<String> visited = new HashSet<>();
        private Graph graph;

        public MaximumMatching(Graph graph) {
            this.graph = graph;
        }

        // DFS search for augmenting path
        private boolean dfs(String player) {
            for (String neighbor : graph.getConnections(player)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    if (!match.containsKey(neighbor) || dfs(match.get(neighbor))) {
                        match.put(neighbor, player);
                        match.put(player, neighbor);
                        return true;
                    }
                }
            }
            return false;
        }

        // Find maximum matching
        public int findMaxMatching() {
            int matchCount = 0;
            for (String player : graph.getPlayers()) {
                visited.clear();
                if (!match.containsKey(player) && dfs(player)) {
                    matchCount++;
                }
            }
            return matchCount;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Graph graph = new Graph();

        // Reading the players
        sc.nextLine(); // Skip "People:"
        List<String> players = new ArrayList<>();
        String line;
        while (!(line = sc.nextLine()).equals("Connections:")) {
            players.add(line);
        }

        // Reading the connections
        while (!(line = sc.nextLine()).equals("END")) {
            String[] parts = line.split(" - ");
            String player1 = parts[0].trim();
            String player2 = parts[1].trim();
            graph.addConnection(player1, player2);
        }

        // Initialize maximum matching
        MaximumMatching maximumMatching = new MaximumMatching(graph);
        int result = maximumMatching.findMaxMatching();

        // Output the result
        System.out.println(result);
    }
}
