import java.util.*;

public class Predator extends Creature {

    //    В дополнение к полям класса Creature, имеет силу атаку.
    private int energy;
    private int attack;

    @Override
    public void makeMove(LinkedHashMap<Cell, Entity> map, Cell cell) {
        //очередь с точками по мере их обнаружения
        Queue<Cell> queue = new LinkedList<>();

        //посещенные точки
        Queue<Cell> visitedNodes = new LinkedList<>();

        //список родителей вершин
        LinkedHashMap<Cell, Cell> parents = new LinkedHashMap<>();
        Set<Cell> cellSet = map.keySet();
        //расчетная длина хода до объекта охоты.
        int len;

        queue.add(cell);
        boolean flagExit = false;
        while (!queue.isEmpty()) {

            Cell serchCell = queue.poll();
            visitedNodes.add(serchCell);
            Cell cellHervibore = null;
            int x = serchCell.getX();
            int y = serchCell.getY();

            for (Cell el : cellSet) {
                if (!visitedNodes.contains(el) && !queue.contains(el)) {
                    if (x - 1 >= 0) {
                        if (el.getX() == x - 1 && serchCell.getY() == el.getY()) {
                            flagExit = this.queuing(map, el, queue, parents, serchCell);
                            if (flagExit) {
                                break;
                            }
                            ;
                        }
                    }
                    if (x + 1 <= 4) {
                        if (el.getX() == x + 1 && serchCell.getY() == el.getY()) {
                            flagExit = this.queuing(map, el, queue, parents, serchCell);
                            if (flagExit) {
                                break;
                            }
                            ;
                        }
                    }
                    if (y - 1 >= 0) {
                        if (serchCell.getX() == el.getX() && el.getY() == y - 1) {
                            flagExit = this.queuing(map, el, queue, parents, serchCell);
                            if (flagExit) {
                                break;
                            }
                            ;
                        }
                    }
                    if (y + 1 <= 4) {
                        if (serchCell.getX() == el.getX() && el.getY() == y + 1) {
                            flagExit = this.queuing(map, el, queue, parents, serchCell);
                            if (flagExit) {
                                break;
                            }
                            ;
                        }
                    }
                }
            }

            if (flagExit) {
                List<Cell> path = new ArrayList<>();
                for (Map.Entry<Cell, Cell> entry : parents.entrySet()) {
                    if (serchCell.equals(entry.getValue()) && map.get(entry.getKey()) != null) {
                        cellHervibore = entry.getKey();
                        path.add(cellHervibore);
                        break;
                    }
                }

                while (!cell.equals(serchCell)) {

                    path.add(parents.get(cellHervibore));
                    serchCell = parents.get(cellHervibore);
                    cellHervibore = serchCell;
                }

                len = path.size() - 1 - getSpeed();
                if (len < 0) {
                    len = 0;
                }

                if (path.size() == 1) {
                    if (getAttack() >= ((Hervibore) map.get(path.get(0))).getHp()) {
                        map.put(path.get(0), map.get(serchCell));
                        map.put(serchCell, null);
                        MapDisplayer.showMap(map);
                    } else {
                        ((Hervibore) map.get(path.get(0))).setHp(((Hervibore) map.get(path.get(0))).getHp() - getAttack());
                        MapDisplayer.showMap(map);
                    }

                } else if (path.size() - 1 - getSpeed() <= 0) {
                    if (getAttack() >= ((Hervibore) map.get(path.get(0))).getHp()) {
                        map.put(path.get(0), map.get(serchCell));
                        map.put(serchCell, null);
                        MapDisplayer.showMap(map);
                    } else {
                        map.put(path.get(len + 1), map.get(serchCell));
                        map.put(serchCell, null);
                        ((Hervibore) map.get(path.get(0))).setHp(((Hervibore) map.get(path.get(0))).getHp() - getAttack());
                        MapDisplayer.showMap(map);
                    }
                } else {
                    map.put(path.get(len), map.get(serchCell));
                    map.put(serchCell, null);
                    MapDisplayer.showMap(map);

                }
                break;
            }
        }
    }

    public boolean queuing(LinkedHashMap<Cell, Entity> map, Cell el, Queue<Cell> queue
            , LinkedHashMap<Cell, Cell> parents, Cell serchCell) {
        boolean flagExit = false;
        if (map.get(el) == null) {
            queue.add(el);
            parents.put(el, serchCell);
        } else if (map.get(el).getClass() == Hervibore.class) {
            queue.add(el);
            parents.put(el, serchCell);
            flagExit = true;
        }
        return flagExit;
    }

    //         На что может потратить ход хищник:
//        Переместиться (чтобы приблизиться к жертве - травоядному)
//        Атаковать травоядное. При этом количество HP травоядного уменьшается на силу атаки хищника.
//        Если значение HP жертвы опускается до 0, травоядное исчезает
    public Predator(int energy, int attack, int speed) {
        this.energy = energy;
        this.attack = attack;
        this.setSpeed(speed);
        this.setName("Хищ");
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public String toString() {
        return "Predator{" +
                "name=" + getName() +
                '}';
    }
}
