package softuniada_2022;
import java.util.*;
public class _06_Placeholder {
    static class Box {
        int height, width, depth;

        Box(int h, int w, int d) {
            this.height = h;
            this.width = w;
            this.depth = d;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Box> boxes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int h = scanner.nextInt();
            int w = scanner.nextInt();
            int d = scanner.nextInt();

            // Всички възможни ориентации на кутията
            boxes.add(new Box(h, Math.max(w, d), Math.min(w, d)));
            boxes.add(new Box(w, Math.max(h, d), Math.min(h, d)));
            boxes.add(new Box(d, Math.max(h, w), Math.min(h, w)));
        }

        // Сортиране по ширина и дълбочина в намаляващ ред
        boxes.sort((a, b) -> {
            if (b.width != a.width) return b.width - a.width;
            return b.depth - a.depth;
        });

        int size = boxes.size();
        int[] maxHeight = new int[size];

        int maxOverallHeight = 0;

        for (int i = 0; i < size; i++) {
            maxHeight[i] = boxes.get(i).height;

            for (int j = 0; j < i; j++) {
                if (boxes.get(i).width < boxes.get(j).width &&
                        boxes.get(i).depth < boxes.get(j).depth) {
                    maxHeight[i] = Math.max(maxHeight[i], maxHeight[j] + boxes.get(i).height);
                }
            }

            maxOverallHeight = Math.max(maxOverallHeight, maxHeight[i]);
        }

        System.out.println(maxOverallHeight);
    }
}
