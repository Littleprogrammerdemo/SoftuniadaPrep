package softuniada_2019;
import java.util.*;
public class _10_Plants {
    static class State {
        int[] treeRoots;
        int[] fungusGrowth;
        int radius;

        // Constructor
        public State(int[] treeRoots, int[] fungusGrowth, int radius) {
            this.treeRoots = treeRoots;
            this.fungusGrowth = fungusGrowth;
            this.radius = radius;
        }

        // Check if a player can grow in a given channel
        boolean canGrowInChannel(int channel, boolean isTree) {
            int maxGrowth = radius - (isTree ? treeRoots[channel] : fungusGrowth[channel]);
            return maxGrowth > 0;
        }

        // Simulate the game assuming the tree grows in channel `channel` by `growth`
        State growTree(int channel, int growth) {
            int[] newTreeRoots = Arrays.copyOf(treeRoots, treeRoots.length);
            newTreeRoots[channel] += growth;
            return new State(newTreeRoots, fungusGrowth, radius);
        }

        // Simulate the game assuming the fungus grows in channel `channel` by `growth`
        State growFungus(int channel, int growth) {
            int[] newFungusGrowth = Arrays.copyOf(fungusGrowth, fungusGrowth.length);
            newFungusGrowth[channel] += growth;
            return new State(treeRoots, newFungusGrowth, radius);
        }

        // Check if the fungus cannot grow anywhere
        boolean fungusCannotGrow() {
            for (int i = 0; i < treeRoots.length; i++) {
                if (canGrowInChannel(i, false)) {
                    return false;
                }
            }
            return true;
        }

        // Check if the tree cannot grow anywhere
        boolean treeCannotGrow() {
            for (int i = 0; i < treeRoots.length; i++) {
                if (canGrowInChannel(i, true)) {
                    return false;
                }
            }
            return true;
        }

        // Check if the game is in a losing state for the fungus
        boolean isFungusLosing() {
            if (fungusCannotGrow()) {
                return true; // Fungus cannot grow, tree wins
            }
            // If it's fungus's turn and it has no optimal move, it's losing
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input Parsing
        int N = sc.nextInt(); // number of channels
        int[] treeRoots = new int[N];
        int[] fungusGrowth = new int[N];

        for (int i = 0; i < N; i++) {
            treeRoots[i] = sc.nextInt();
            fungusGrowth[i] = sc.nextInt();
        }

        int radius = sc.nextInt();

        // Initialize the game state
        State initialState = new State(treeRoots, fungusGrowth, radius);

        // Try every possible tree growth
        int bestChannel = -1;
        int bestGrowth = -1;
        for (int i = 0; i < N; i++) {
            for (int growth = 1; growth <= radius - treeRoots[i]; growth++) {
                // Simulate the tree growing
                State newState = initialState.growTree(i, growth);

                // If the fungus has no way to grow after this move, tree wins
                if (newState.isFungusLosing()) {
                    if (bestChannel == -1 || growth > bestGrowth || (growth == bestGrowth && i < bestChannel)) {
                        bestChannel = i;
                        bestGrowth = growth;
                    }
                }
            }
        }

        // Output the result
        if (bestChannel == -1) {
            System.out.println("---");
        } else {
            System.out.println("grow " + bestChannel + " by " + bestGrowth);
        }

        sc.close();
    }
}
