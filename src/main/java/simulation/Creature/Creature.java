package simulation.Creature;

import simulation.Cell;
import simulation.MapClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public abstract class Creature extends Entity {

    private int speed;
    private int hp;

    public void path(Queue<ArrayList<Cell>> lineOfCellsArray, ArrayList<Cell> cellLinkedList, List<Cell> visitedCells,
                     Cell cell, MapClass map, Class T) {
        boolean flagExit = false;

        cellLinkedList.add(cell);
        lineOfCellsArray.add(cellLinkedList);
        while (!lineOfCellsArray.isEmpty()) {

            List<Cell> listFromQueue = lineOfCellsArray.poll();
            Cell cellFromQueue = listFromQueue.get(listFromQueue.size() - 1);
            visitedCells.add(cellFromQueue);
            int x = cellFromQueue.getX();
            int y = cellFromQueue.getY();

            // анализируем ячейки сверху/снизу/с лева/с права
            // если ячейка пустая, то добавляем её в очередь, если в ячейке Травоядное, то добавляем её в очередь и
            // заканчиваем цикл поиска
            // Иные ячейки не анализируются (камни, деревья, иные хищники)
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    Cell el = new Cell(x + i, y + j);
                    if (!visitedCells.contains(el)) {
                        if (map.getWidth() > x + i && x + i >= 0 && map.getHeight() > y + j && y + j >= 0 && Math.abs(i) != Math.abs(j)) {
                            if (!map.contains(el)) {
                                ArrayList<Cell> buf = new ArrayList<>(listFromQueue);
                                buf.add(el);
                                lineOfCellsArray.add(buf);
                            } else if (map.getEntity(el).getClass() == T) {
                                ArrayList<Cell> buf = new ArrayList<>(listFromQueue);
                                buf.add(el);
                                lineOfCellsArray.add(buf);
                                flagExit = true;
                                break;
                            }
                        }
                    }
                }
                if (flagExit) {
                    break;
                }
            }
            if (flagExit) {
                break;
            }
        }
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getSpeed() {
        return speed;
    }

    public int getHp() {
        return hp;
    }

    public abstract void makeMove(MapClass map, Cell cell);
}
