package simulation.Creature;

import simulation.Cell;
import simulation.MapClass;

import java.util.*;

public class Hervibore extends Creature {

    public Hervibore(int speed, int hp) {
        this.setName("ТрЖ");
        setSpeed(speed);
        setHp(hp);
    }

    @Override
    public String toString() {
        return "name = " + getName() +
                "; hp = " + getHp() +
                "; speed = " + getSpeed();
    }

    @Override
    public void makeMove(MapClass map, Cell cell) {
        //очередь с клетками по мере их обнаружения
        Queue<ArrayList<Cell>> lineOfCellsArray = new LinkedList<>();
        ArrayList<Cell> cellLinkedList = new ArrayList<>();

        //посещенные клетки
        List<Cell> visitedCells = new LinkedList<>();

        path(lineOfCellsArray, cellLinkedList, visitedCells, cell, map, Grass.class);

        //получение последнего пути из очереди
        ArrayList<Cell> cells = lineOfCellsArray.stream()
                .reduce((e1, e2) -> e2)
                .orElse(null);

        if (cells != null && !cells.isEmpty()) {
            if (cells.size() == 2) {
                map.updateEntity(cells.get(cells.size() - 1), map.getEntity(cell));
                map.del(cell);
            } else if (cells.size() - 1 > ((Hervibore) map.getEntity(cell)).getSpeed()) {
                map.add(cells.get(((Hervibore) map.getEntity(cell)).getSpeed() - 1), map.getEntity(cell));
                map.del(cell);
            } else {
                map.add(cells.get(cells.size() - 1), map.getEntity(cell));
                map.del(cell);
            }
        }
        //после поедания травы HP увеличивается на +1
        setHp(getHp() + 1);
    }
}
