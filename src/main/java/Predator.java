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
        return "Predator{" + "name = " + getName()  + "; attack = "+ getAttack() + "; speed = " + getSpeed() + '}';
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
            Cell cellBufer = null;
            int x = cellFromQueue.getX();
            int y = cellFromQueue.getY();

            // анализируем ячейки сверху/снизу/с лева/с права
            // если ячейка пустая, то добавляем её в очередь, если в ячейке Травоядное, то добавляем её в очередь и
            // заканчиваем цикл поиска
            // Иные ячейки не анализируются (камни, деревья, иные хищники)
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
                //Список для формирования кратчайшего пути к жетрве
                List<Cell> pathToGoal = new ArrayList<>();
                //ищем ключ (позицию клетки) к которой необходимо прийти
                for (Map.Entry<Cell, Cell> entry : parents.entrySet()) {
                    if (cellFromQueue.equals(entry.getValue()) && map.get(entry.getKey()) != null) {
                        cellBufer = entry.getKey();
                        pathToGoal.add(cellBufer);
                        break;
                    }
                }
                //формируем кратчайший путь к точке назначения
                while (!cell.equals(cellFromQueue)) {
                    pathToGoal.add(parents.get(cellBufer));
                    cellFromQueue = parents.get(cellBufer);
                    cellBufer = cellFromQueue;
                }
                //определяем индекс клетки в списке кратчайшего пути в зависимости от скорости хищника
                destinationCellIndex = pathToGoal.size() - 1 - getSpeed();
                if (destinationCellIndex < 0) {
                    destinationCellIndex = 0;
                }
                // анализируем различные варианты когда хищник может или не может добраться до жертвы и
                // хватает ли ему атаки.
                if (pathToGoal.size() == 1) {
                    if (getAttack() >= ((Hervibore) map.get(pathToGoal.get(0))).getHp()) {
                        map.put(pathToGoal.get(0), map.get(cellFromQueue));
                        map.put(cellFromQueue, null);
                    } else {
                        ((Hervibore) map.get(pathToGoal.get(0))).setHp(((Hervibore) map.get(pathToGoal.get(0))).getHp()
                                - getAttack());
                    }
                } else if (pathToGoal.size() - 1 - getSpeed() <= 0) {
                    if (getAttack() >= ((Hervibore) map.get(pathToGoal.get(0))).getHp()) {
                        map.put(pathToGoal.get(0), map.get(cellFromQueue));
                        map.put(cellFromQueue, null);
                    } else {
                        map.put(pathToGoal.get(destinationCellIndex + 1), map.get(cellFromQueue));
                        map.put(cellFromQueue, null);
                        ((Hervibore) map.get(pathToGoal.get(0))).setHp(((Hervibore) map.get(pathToGoal.get(0))).getHp()
                                - getAttack());
                    }
                } else {
                    map.put(pathToGoal.get(destinationCellIndex), map.get(cellFromQueue));
                    map.put(cellFromQueue, null);
                }
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
        } else if (map.get(el).getClass() == Hervibore.class) {
            lineOfCells.add(el);
            parents.put(el, cellBufer);
            flagExit = true;
        }
        return flagExit;
    }
}