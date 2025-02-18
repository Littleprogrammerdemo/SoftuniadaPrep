package softuniada_2018;
import java.util.*;
public class _08_Logistics {
    static class Package {
        int price;
        int deadline;
        int index;

        Package(int price, int deadline, int index) {
            this.price = price;
            this.deadline = deadline;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Четене на брой пратки
        int N = sc.nextInt();
        sc.nextLine(); // За да премахнем излишния нов ред

        List<Package> packages = new ArrayList<>();

        // Четене на пратките
        for (int i = 0; i < N; i++) {
            int price = sc.nextInt();
            int deadline = sc.nextInt();
            packages.add(new Package(price, deadline, i + 1));
            sc.nextLine(); // За да премахнем излишния нов ред
        }

        // Четене на катастрофите
        String disastersInput = sc.nextLine();
        Set<Integer> disasters = new HashSet<>();

        if (!disastersInput.equals("none")) {
            String[] disasterDays = disastersInput.split(" ");
            for (String day : disasterDays) {
                disasters.add(Integer.parseInt(day));
            }
        }

        // Сортиране на пратките
        packages.sort((p1, p2) -> {
            // Ако няма катастрофи, сортираме по цена в намаляващ ред
            if (disasters.isEmpty()) {
                if (p1.deadline == p2.deadline) {
                    return Integer.compare(p2.price, p1.price); // Първо по цена, намаляващо
                } else {
                    return Integer.compare(p1.deadline, p2.deadline); // Първо по крайния срок
                }
            }
            // Ако има катастрофи, сортираме по цена в нарастващ ред
            if (p1.deadline == p2.deadline) {
                return Integer.compare(p1.price, p2.price); // Първо по цена, нарастващо
            } else {
                return Integer.compare(p1.deadline, p2.deadline); // Първо по крайния срок
            }
        });

        // Събиране на резултатите
        int balance = 0;
        List<Integer> resultOrder = new ArrayList<>();

        // За всеки ден до най-големия краен срок избираме пратка
        int currentDay = 1;
        for (Package p : packages) {
            if (p.deadline >= currentDay) {
                resultOrder.add(p.index);
                balance += p.price;
                currentDay++;
            }
        }

        // Ако има катастрофи, изваждаме съответните разходи
        for (int day : disasters) {
            if (day <= resultOrder.size()) {
                int index = resultOrder.get(day - 1);
                // Намаляме баланса за катастрофата
                Package crashedPackage = null;
                for (Package p : packages) {
                    if (p.index == index) {
                        crashedPackage = p;
                        break;
                    }
                }
                balance -= 50 + crashedPackage.price;  // 50 за поправка и цената на пратката за обезщетение
            }
        }

        // Печата резултата
        System.out.println(balance);
        System.out.println(String.join(" ", resultOrder.toString().replaceAll("[\\[\\],]", "")));
    }
}
