package softuniada_2024;
import java.util.*;
public class _10_CompanyHierarchy {
    static int hireCost, retrainCost, fireCost;
    static Map<String, List<String>> treeA = new HashMap<>(), treeB = new HashMap<>();
    static Set<String> positionsA = new HashSet<>(), positionsB = new HashSet<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        hireCost = sc.nextInt();
        retrainCost = sc.nextInt();
        fireCost = sc.nextInt();
        sc.nextLine();

        positionsA.addAll(Arrays.asList(sc.nextLine().split(" ")));
        positionsB.addAll(Arrays.asList(sc.nextLine().split(" ")));

        int edgesA = sc.nextInt(); sc.nextLine();
        buildTree(sc, treeA, edgesA);

        int edgesB = sc.nextInt(); sc.nextLine();
        buildTree(sc, treeB, edgesB);

        System.out.println(minCostToTransform());
    }

    static void buildTree(Scanner sc, Map<String, List<String>> tree, int edges) {
        for (int i = 0; i < edges; i++) {
            String[] parts = sc.nextLine().split(" ");
            tree.computeIfAbsent(parts[0], k -> new ArrayList<>()).add(parts[1]);
        }
    }

    static int minCostToTransform() {
        Set<String> toRemove = new HashSet<>(positionsA);
        toRemove.removeAll(positionsB);

        Set<String> toAdd = new HashSet<>(positionsB);
        toAdd.removeAll(positionsA);

        int cost = 0;
        for (String pos : toRemove) cost += pos.length() * fireCost;
        for (String pos : toAdd) cost += pos.length() * hireCost;

        for (String posA : positionsA) {
            if (positionsB.contains(posA)) continue;
            int minRenameCost = Integer.MAX_VALUE;
            for (String posB : positionsB) {
                int renameCost = calculateEditDistance(posA, posB);
                minRenameCost = Math.min(minRenameCost, renameCost);
            }
            cost += minRenameCost;
        }

        return cost;
    }

    static int calculateEditDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i <= a.length(); i++) dp[i][0] = i * fireCost;
        for (int j = 0; j <= b.length(); j++) dp[0][j] = j * hireCost;

        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + retrainCost,
                            Math.min(dp[i - 1][j] + fireCost, dp[i][j - 1] + hireCost));
                }
            }
        }
        return dp[a.length()][b.length()];
    }
}