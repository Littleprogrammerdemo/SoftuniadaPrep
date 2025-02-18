package softuniada_2016;
import java.util.*;
public class _09_FastAndFurious {

    static class Road {
        String camera1;
        String camera2;
        double distance;
        double speedLimit;

        public Road(String camera1, String camera2, double distance, double speedLimit) {
            this.camera1 = camera1;
            this.camera2 = camera2;
            this.distance = distance;
            this.speedLimit = speedLimit;
        }
    }

    static class Record {
        String camera;
        String licensePlate;
        int hours;
        int minutes;
        int seconds;

        public Record(String camera, String licensePlate, String time) {
            this.camera = camera;
            this.licensePlate = licensePlate;
            String[] parts = time.split(":");
            this.hours = Integer.parseInt(parts[0]);
            this.minutes = Integer.parseInt(parts[1]);
            this.seconds = Integer.parseInt(parts[2]);
        }

        public double getTimeInHours() {
            return hours + minutes / 60.0 + seconds / 3600.0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read Roads
        Map<String, List<Road>> roads = new HashMap<>();
        scanner.nextLine(); // Skip "Roads:"
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("Records:")) break;
            String[] parts = line.split(" ");
            String camera1 = parts[0];
            String camera2 = parts[1];
            double distance = Double.parseDouble(parts[2]);
            double speedLimit = Double.parseDouble(parts[3]);
            Road road = new Road(camera1, camera2, distance, speedLimit);

            roads.putIfAbsent(camera1, new ArrayList<>());
            roads.putIfAbsent(camera2, new ArrayList<>());
            roads.get(camera1).add(road);
            roads.get(camera2).add(road);
        }

        // Read Records
        Map<String, Map<String, Double>> carTimes = new HashMap<>();
        scanner.nextLine(); // Skip "Records:"
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("End")) break;
            String[] parts = line.split(" ");
            String camera = parts[0];
            String licensePlate = parts[1];
            String time = parts[2];

            Record record = new Record(camera, licensePlate, time);
            carTimes.putIfAbsent(licensePlate, new HashMap<>());
            carTimes.get(licensePlate).put(camera, record.getTimeInHours());
        }

        // For each camera, find the shortest path between all other cameras
        Map<String, Map<String, Double>> shortestTimes = new HashMap<>();
        for (String camera : roads.keySet()) {
            shortestTimes.put(camera, dijkstra(camera, roads));
        }

        // Detect speeding cars
        Set<String> speedingCars = new HashSet<>();
        for (String car : carTimes.keySet()) {
            Map<String, Double> carRecord = carTimes.get(car);
            for (String camera1 : carRecord.keySet()) {
                for (String camera2 : carRecord.keySet()) {
                    if (!camera1.equals(camera2)) {
                        double actualTime = Math.abs(carRecord.get(camera2) - carRecord.get(camera1));
                        double minTime = shortestTimes.get(camera1).get(camera2);
                        if (actualTime < minTime) {
                            speedingCars.add(car);
                            break;
                        }
                    }
                }
            }
        }

        // Output speeding cars in alphabetical order
        List<String> result = new ArrayList<>(speedingCars);
        Collections.sort(result);
        for (String car : result) {
            System.out.println(car);
        }
    }

    // Dijkstra's algorithm to find the shortest time from a camera
    public static Map<String, Double> dijkstra(String start, Map<String, List<Road>> roads) {
        Map<String, Double> distances = new HashMap<>();
        PriorityQueue<String> queue = new PriorityQueue<>(Comparator.comparingDouble(distances::get));
        distances.put(start, 0.0);
        queue.add(start);

        while (!queue.isEmpty()) {
            String camera = queue.poll();
            double currentDist = distances.get(camera);

            for (Road road : roads.get(camera)) {
                String neighbor = road.camera1.equals(camera) ? road.camera2 : road.camera1;
                double travelTime = road.distance / road.speedLimit;
                double newDist = currentDist + travelTime;

                if (!distances.containsKey(neighbor) || newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    queue.add(neighbor);
                }
            }
        }
        return distances;
    }
}