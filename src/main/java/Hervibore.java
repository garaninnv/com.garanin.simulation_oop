import java.util.*;

public class Hervibore extends Creature {

    public Hervibore(int speed, int hp) {
        this.setName("ТрЖ");
        setSpeed(speed);
        setHp(hp);
    }
    @Override
    public String toString() {
        return "Hervibore{" +
                "name = " + getName() +
                "; hp = " + getHp() +
                "; speed = " + getSpeed() +
                '}';
    }

    @Override
    public void makeMove(LinkedHashMap<Cell, Entity> map, Cell cell) {
        //очередь с клетками по мере их обнаружения
        Queue<Cell> lineOfCells = new LinkedList<>();

        //посещенные клетки
        List<Cell> visitedCells = new LinkedList<>();

        //список родителей вершин
        LinkedHashMap<Cell, Cell> parents = new LinkedHashMap<>();

        //Список всех ключей из мап (карты)
        Set<Cell> cellKeySet = map.keySet();

        // длина хода до объекта охоты.
        int destinationCellIndex;

        // флаг для досрочного выхода из циклов
        boolean flagExit = false;

        // в очередь помещаем первую ячейку и начинаем обходить всю очередь ячеек.
        lineOfCells.add(cell);
        while (!lineOfCells.isEmpty()) {

            Cell cellFromQueue = lineOfCells.poll();
            visitedCells.add(cellFromQueue);
            Cell cellHervibore = null;
            int x = cellFromQueue.getX();
            int y = cellFromQueue.getY();

            // анализируем ячейки сверху/снизу/с лева/с права
            // если ячейка пустая, то добавляем её в очередь, если в ячейке Трава, то добавляем её в очередь и
            // заканчиваем цикл поиска
            // Иные ячейки не анализируются (камни, деревья, хищники, иные травоядные)
            for (Cell el : cellKeySet) {
                if (!visitedCells.contains(el) && !lineOfCells.contains(el)) {
                    if (x - 1 >= 0) {
                        if (el.getX() == x - 1 && cellFromQueue.getY() == el.getY()) {
                            flagExit = addCellsToQueue(map, el, lineOfCells, parents, cellFromQueue);
                            if (flagExit) {
                                break;
                            }
                            ;
                        }
                    }
                    if (x + 1 <= 9) {
                        if (el.getX() == x + 1 && cellFromQueue.getY() == el.getY()) {
                            flagExit = addCellsToQueue(map, el, lineOfCells, parents, cellFromQueue);
                            if (flagExit) {
                                break;
                            }
                            ;
                        }
                    }
                    if (y - 1 >= 0) {
                        if (cellFromQueue.getX() == el.getX() && el.getY() == y - 1) {
                            flagExit = addCellsToQueue(map, el, lineOfCells, parents, cellFromQueue);
                            if (flagExit) {
                                break;
                            }
                            ;
                        }
                    }
                    if (y + 1 <= 9) {
                        if (cellFromQueue.getX() == el.getX() && el.getY() == y + 1) {
                            flagExit = addCellsToQueue(map, el, lineOfCells, parents, cellFromQueue);
                            if (flagExit) {
                                break;
                            }
                            ;
                        }
                    }
                }
            }

            if (flagExit) {
                //Список для формирования кратчайшего пути к траве
                List<Cell> path = new ArrayList<>();
                //ищем ключ (позицию клетки) к которой необходимо прийти
                for (Map.Entry<Cell, Cell> entry : parents.entrySet()) {
                    if (cellFromQueue.equals(entry.getValue()) && map.get(entry.getKey()) != null) {
                        cellHervibore = entry.getKey();
                        path.add(cellHervibore);
                        break;
                    }
                }
                //формируем кратчайший путь к точке назначения
                while (!cell.equals(cellFromQueue)) {
                    path.add(parents.get(cellHervibore));
                    cellFromQueue = parents.get(cellHervibore);
                    cellHervibore = cellFromQueue;
                }
                //определяем индекс клетки в списке кратчайшего пути в зависимости от скорости травоядного
                //если скорости не хватает дойти до цели, животное просто пытается приблизиться.
                destinationCellIndex = path.size() - getSpeed();
                if (destinationCellIndex < 0) {
                    destinationCellIndex = 0;
                }

                if (path.size() == 1) {
                    map.put(path.get(destinationCellIndex), map.get(cellFromQueue));
                    map.put(cellFromQueue, null);

                } else {
                    map.put(path.get(destinationCellIndex), map.get(path.get(path.size() - 1)));
                    map.put(path.get(path.size() - 1), null);
                }
                //после поедания травы HP увеличивается на +1
                setHp(getHp() + 1);
                break;
            }
        }
    }

    private boolean addCellsToQueue(LinkedHashMap<Cell, Entity> map, Cell el, Queue<Cell> lineOfCells
            , LinkedHashMap<Cell, Cell> parents, Cell cellBufer) {
        boolean flagExit = false;
        if (map.get(el) == null) {
            lineOfCells.add(el);
            parents.put(el, cellBufer);
        } else if (map.get(el).getClass() == Grass.class) {
            lineOfCells.add(el);
            parents.put(el, cellBufer);
            flagExit = true;
        }
        return flagExit;
    }
}
