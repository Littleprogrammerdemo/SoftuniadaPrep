package softuniada_2017;
import java.util.*;
public class _05_ParkingZone {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        scanner.nextLine();

        // Четене на зоните
        List<Zone> zones = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String zoneInput = scanner.nextLine();
            String[] parts = zoneInput.split(": ");
            String[] zoneData = parts[1].split(", ");
            String name = parts[0];
            int x = Integer.parseInt(zoneData[0]);
            int y = Integer.parseInt(zoneData[1]);
            int length = Integer.parseInt(zoneData[2]);
            int width = Integer.parseInt(zoneData[3]);
            double price = Double.parseDouble(zoneData[4]);

            zones.add(new Zone(name, x, y, length, width, price));
        }

        // Свободни паркоместа
        String[] freeSpots = scanner.nextLine().split("; ");

        // Целеви блок
        String[] targetBlock = scanner.nextLine().split(", ");
        int targetX = Integer.parseInt(targetBlock[0]);
        int targetY = Integer.parseInt(targetBlock[1]);

        // Време за преминаване на блок
        int timePerBlock = scanner.nextInt();

        // Изчисляваме най-доброто паркомясто
    }

    static class Zone {
        String name;
        int x, y, length, width;
        double price;

        Zone(String name, int x, int y, int length, int width, double price) {
            this.name = name;
            this.x = x;
            this.y = y;
            this.length = length;
            this.width = width;
            this.price = price;
        }
    }
}
