package softuniada_2016;
import java.util.*;
public class _07_StarClusters {
    static class Cluster {
        String name;
        List<double[]> stars = new ArrayList<>();

        public Cluster(String name, double x, double y) {
            this.name = name;
            stars.add(new double[]{x, y});
        }

        public void addStar(double x, double y) {
            stars.add(new double[]{x, y});
        }

        public double[] computeCenter() {
            double sumX = 0, sumY = 0;
            for (double[] star : stars) {
                sumX += star[0];
                sumY += star[1];
            }
            return new double[]{sumX / stars.size(), sumY / stars.size()};
        }

        public int size() {
            return stars.size();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int C = Integer.parseInt(scanner.nextLine().trim()); // Number of clusters

        Map<String, Cluster> clusters = new TreeMap<>(); // Sorted by name

        // Read cluster names and initial representative stars
        for (int i = 0; i < C; i++) {
            String[] parts = scanner.nextLine().split(" ");
            String clusterName = parts[0];
            double[] coords = parseCoordinates(parts[1]);
            clusters.put(clusterName, new Cluster(clusterName, coords[0], coords[1]));
        }

        // Read other stars until "end"
        while (scanner.hasNext()) {
            String line = scanner.nextLine().trim();
            if (line.equals("end")) break;

            String[] stars = line.split(" ");
            for (String star : stars) {
                double[] coords = parseCoordinates(star);
                assignStarToCluster(clusters, coords[0], coords[1]);
            }
        }

        // Compute and print results
        for (Cluster cluster : clusters.values()) {
            double[] center = cluster.computeCenter();
            System.out.printf("%s (%d, %d) -> %d stars%n",
                    cluster.name, round(center[0]), round(center[1]), cluster.size());
        }

        scanner.close();
    }

    // Parse coordinates in the format (X, Y)
    private static double[] parseCoordinates(String coordStr) {
        String[] parts = coordStr.replace("(", "").replace(")", "").split(",");
        return new double[]{Double.parseDouble(parts[0]), Double.parseDouble(parts[1])};
    }

    // Assign a star to the nearest cluster
    private static void assignStarToCluster(Map<String, Cluster> clusters, double x, double y) {
        String nearestCluster = null;
        double minDistance = Double.MAX_VALUE;

        for (Cluster cluster : clusters.values()) {
            double[] center = cluster.computeCenter();
            double distance = Math.hypot(center[0] - x, center[1] - y);

            if (distance < minDistance) {
                minDistance = distance;
                nearestCluster = cluster.name;
            }
        }

        if (nearestCluster != null) {
            clusters.get(nearestCluster).addStar(x, y);
        }
    }

    // Banker's rounding (round half to even)
    private static int round(double value) {
        return (int) Math.rint(value);
    }
}
