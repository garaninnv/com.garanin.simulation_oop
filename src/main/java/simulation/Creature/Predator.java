package simulation.Creature;

import simulation.Cell;
import simulation.MapClass;

import java.util.*;

public class Predator extends Creature {
    private int attack;

    public Predator(int attack, int speed) {
        this.attack = attack;
        this.setSpeed(speed);
        this.setName("Хищ");
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }


    @Override
    public String toString() {
        return "name = " + getName() + "; attack = " + getAttack() + "; speed = " + getSpeed();
    }

    @Override
    public void makeMove(MapClass map, Cell cell) {
        //очередь с клетками по мере их обнаружения
        Queue<ArrayList<Cell>> lineOfCellsArray = new LinkedList<>();
        ArrayList<Cell> cellLinkedList = new ArrayList<>();

        //посещенные клетки
        List<Cell> visitedCells = new LinkedList<>();

        path(lineOfCellsArray, cellLinkedList, visitedCells, cell, map, Hervibore.class);

        //получение последнего пути из очереди
        ArrayList<Cell> cells = lineOfCellsArray.stream()
                .reduce((e1, e2) -> e2)
                .orElse(null);

        // анализируем различные варианты когда хищник может или не может добраться до жертвы и
        // хватает ли ему атаки.
        //если жертва стоит впритык
        if (cells != null && !cells.isEmpty()) {
            if (cells.size() == 2) {
                //убиваем жертву или уменьшаем ХП жертвы
                if (getAttack() >= ((Hervibore) map.getEntity(cells.get(cells.size() - 1))).getHp()) {
                    map.updateEntity(cells.get(cells.size() - 1), map.getEntity(cell));
                    map.del(cell);
                } else {
                    ((Hervibore) map.getEntity(cells.get(cells.size() - 1))).setHp(((Hervibore) map.
                            getEntity(cells.get(cells.size() - 1))).getHp() - getAttack());
                }
                //хватает скорости дойти до жервы и атаковать
            } else if (cells.size() - 1 - getSpeed() <= 0) {
                if (getAttack() >= ((Hervibore) map.getEntity(cells.get(cells.size() - 1))).getHp()) {
                    map.updateEntity(cells.get(cells.size() - 1), map.getEntity(cell));
                    map.del(cell);
                } else {
                    map.updateEntity(cells.get(cells.size() - 2), map.getEntity(cell));
                    ((Hervibore) map.getEntity(cells.get(cells.size() - 1))).setHp(((Hervibore) map.
                            getEntity(cells.get(cells.size() - 1))).getHp() - getAttack());
                    map.del(cell);
                }
                //просто двигаемся к жертве
            } else {
                map.add(cells.get(((Predator) map.getEntity(cell)).getSpeed() - 1), map.getEntity(cell));
                map.del(cell);
            }
        }
    }
}