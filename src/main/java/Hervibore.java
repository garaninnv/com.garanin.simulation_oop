import java.util.*;

public class Hervibore extends Creature {

    public Hervibore(int speed, int hp) {
        this.setName("ТрЖ");
        setSpeed(speed);
        setHp(hp);
    }

    //Cтремятся найти ресурс (траву), может потратить свой ход на движение в сторону травы, либо на её поглощение
    //Если значение HP жертвы опускается до 0, травоядное исчезает
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

                len = path.size() - getSpeed();
                if (len < 0) {
                    len = 0;
                }

                if (path.size() == 1) {
                    map.put(path.get(len), map.get(serchCell));
                    map.put(serchCell, null);

                } else {
                    map.put(path.get(len), map.get(path.get(path.size() - 1)));
                    map.put(path.get(path.size() - 1), null);
                }
                setHp(getHp() + 1);
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
        } else if (map.get(el).getClass() == Grass.class) {
            queue.add(el);
            parents.put(el, serchCell);
            flagExit = true;
        }
        return flagExit;
    }

    @Override
    public String toString() {
        return "Hervibore{" +
                "hp=" + getHp() +
                "name=" + getName() +
                '}';
    }
}
