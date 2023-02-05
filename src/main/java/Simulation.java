import java.awt.*;
import java.util.*;

public class Simulation {
    public static void main(String[] args) throws InterruptedException {
        int maxX = 10;
        int maxY = 10;
        Scanner scanner = new Scanner(System.in);

        int idMenu = -1;
        LinkedHashMap<Cell, Entity> map = new LinkedHashMap<>();
        Actions actions = new Actions();

        actions.initMap(map, maxX, maxY);

        actions.initActions(map);

        while (idMenu != 0) {
            System.out.println("============Меню симуляции============");
            System.out.println("1 - Симулировать игровой Мир сделать один ход");
            System.out.println("2 - Запустить бесконечную симуляцию игрового Мира ");
            System.out.println("3 - Приостановить бесконечную симуляцию игрового Мира");
            System.out.println("4 - Продолжить игру в бесконечном цикле");
            System.out.println("5 - Начать игру с начала");
            System.out.println("0 - Конец  Игры");
            System.out.println("=======================================");
            MapDisplayer.showMap(map);
            idMenu = scanner.nextInt();

            if (idMenu == 1) {
                try {
                    actions.turnActions(map);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else if (idMenu == 2) {
                //пока на карте есть травоядные имеется смысл жизни в игровом Мире
                    while (areHervibore(map, actions)) {

                        try {
                            actions.turnActions(map);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
            } else if (idMenu == 5) {
                actions = new Actions();
                actions.initMap(map, maxX, maxY);
                actions.initActions(map);
            } else if (idMenu != 0) {
                System.out.println("Выберите корректный пункт меню");
            }
        }
    }
    private static boolean areHervibore(LinkedHashMap<Cell, Entity> map, Actions actions) {
        boolean fl = false;
        for (Map.Entry<Cell, Entity> el : map.entrySet()) {
            if (el.getValue() != null && el.getValue().getClass() == Hervibore.class) {
                fl = true;
                break;
            }
        }
        if (!fl) {
            actions.addHerviboreAndGrass(map);
        }
        return true;
    }
}
