package simulation.Actions;

import simulation.Cell;
import simulation.Creature.*;
import simulation.MapClass;
import simulation.MapDisplayer;

import java.util.*;

public class MoveCreaturesAction extends Action {
    private int moveCounter = 1;

    // запуск функции для выполнения хода всех существ в порядке очереди.
    @Override
    public void perform(MapClass map) {
        Set <Cell> cellSet = new HashSet<>(map.getCells());
        for (Cell cellEntity : cellSet) {
            if (map.getEntity(cellEntity).getClass().equals(Predator.class)) {
                Predator pre = (Predator) map.getEntity(cellEntity);
                pre.makeMove(map, cellEntity);

                System.out.println("Хищник " + cellEntity.toString() + " делает ход");
                MapDisplayer.showMap(map);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else if (map.getEntity(cellEntity).getClass().equals(Hervibore.class)) {
                Hervibore hervibore = (Hervibore) map.getEntity(cellEntity);
                hervibore.makeMove(map, cellEntity);
                System.out.println("Травоядный " + cellEntity.toString() + " делает ход");
                MapDisplayer.showMap(map);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println("Ход №" + this.moveCounter + " выполнен");
        System.out.println("Добавление новых сущностей на карту");
        this.moveCounter++;
    }
}
