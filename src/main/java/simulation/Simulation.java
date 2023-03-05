package simulation;

import simulation.Actions.*;

import java.util.*;

public class Simulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int idMenu = -1;
        MapClass map = new MapClass(10, 10);
        List<Action> initActions = new ArrayList<>();
        List<Action> turnActions = new ArrayList<>();

        createActions(map, initActions, turnActions);
        for (Action el : initActions) {
            el.perform(map);
        }

        while (idMenu != 0) {
            System.out.println("============Меню симуляции============");
            System.out.println("1 - Симулировать игровой Мир сделать один ход");
            System.out.println("2 - Запустить бесконечную симуляцию игрового Мира ");
            System.out.println("3 - Начать игру с начала");
            System.out.println("0 - Конец  Игры");
            System.out.println("=======================================");

            MapDisplayer.showMap(map);
            idMenu = scanner.nextInt();

            if (idMenu == 1) {
                for (Action el : turnActions) {
                    el.perform(map);
                }
            } else if (idMenu == 2) {
                while (true) {
                    for (Action el : turnActions) {
                        el.perform(map);
                    }
                }
            } else if (idMenu == 3) {
                map = new MapClass(10, 10);
                createActions(map, initActions, turnActions);
            } else if (idMenu != 0) {
                System.out.println("Выберите корректный пункт меню");
            }
        }
    }

    private static void createActions(MapClass map, List<Action> initActions, List<Action> turnActions) {
        initActions.add(new GrassEntityGenerateAction(map));
        initActions.add(new RockEntityGenerateAction(map));
        initActions.add(new TreeEntityGenerateAction(map));
        initActions.add(new HerviboreEntityGenerateAction(map));
        initActions.add(new PredatorEntityGenerateAction(map));
        turnActions.add(new MoveCreaturesAction());
        turnActions.add(new GrassEntityGenerateAction(map));
        turnActions.add(new PredatorEntityGenerateAction(map));
        turnActions.add(new HerviboreEntityGenerateAction(map));
    }
}
