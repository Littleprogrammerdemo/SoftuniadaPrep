package softuniada_2021;
import java.util.*;

public class _08_Snowboarding {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Четем входните данни
        int stamina = Integer.parseInt(scanner.nextLine()); // Издръжливост на състезателя
        String[] trackNames = scanner.nextLine().split(" "); // Имена на пистите
        int[] trackStamina = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray(); // Издръжливост за всяка писта
        int[] trackObstacles = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray(); // Брой препятствия за всяка писта

        // Решаваме проблема с жаден алгоритъм
        List<Track> tracks = new ArrayList<>();
        for (int i = 0; i < trackNames.length; i++) {
            tracks.add(new Track(trackNames[i], trackStamina[i], trackObstacles[i]));
        }

        // Сортираме първо по разход на издръжливост (по-малко е по-добре), а после по препятствия (повече е по-добре)
        tracks.sort(Comparator.comparingInt((Track t) -> t.staminaNeeded).thenComparingInt(t -> -t.obstacles));

        List<String> chosenTracks = new ArrayList<>();
        int totalObstacles = 0;
        int remainingStamina = stamina;

        for (Track track : tracks) {
            if (remainingStamina >= track.staminaNeeded) {
                chosenTracks.add(track.name);
                totalObstacles += track.obstacles;
                remainingStamina -= track.staminaNeeded;
            }
        }

        // Подреждаме избраните писти по азбучен ред
        Collections.sort(chosenTracks);

        // Принтираме резултата
        System.out.println(String.join(" ", chosenTracks));
        System.out.println(totalObstacles);
        System.out.println(remainingStamina);
    }

    // Клас за съхранение на информация за всяка писта
    static class Track {
        String name;
        int staminaNeeded;
        int obstacles;

        public Track(String name, int staminaNeeded, int obstacles) {
            this.name = name;
            this.staminaNeeded = staminaNeeded;
            this.obstacles = obstacles;
        }
    }
}
