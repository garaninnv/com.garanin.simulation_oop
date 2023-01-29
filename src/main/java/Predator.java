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

        queue.add(cell);
        boolean flagExit = false;
        while (!queue.isEmpty()) {

            Cell serchCell = queue.poll();
            visitedNodes.add(serchCell);
            Cell cellHervibore = null;
            int x = serchCell.getX();
            int y = serchCell.getY();

            for (Cell el: cellSet) {
                if (!visitedNodes.contains(el) && !queue.contains(el)){
                    if (x - 1 >= 0) {
                        if (el.getX() == x - 1 && serchCell.getY() == el.getY()) {
                            if (map.get(el) == null) {
                                queue.add(el);
                                parents.put(el, serchCell);
                            } else if (map.get(el).getClass() == Hervibore.class) {
                                queue.add(el);
                                parents.put(el, serchCell);
                                flagExit =true;
                                break;
                            }
                        }
                    }
                    if (x + 1 <= 4) {
                        if (el.getX() == x + 1 && serchCell.getY() == el.getY()) {
                            if (map.get(el) == null) {
                                queue.add(el);
                                parents.put(el, serchCell);
                            } else if (map.get(el).getClass() == Hervibore.class) {
                                queue.add(el);
                                parents.put(el, serchCell);
                                flagExit =true;
                                break;
                            }
                        }
                    }
                    if (y - 1 >= 0) {
                        if (serchCell.getX() == el.getX() && el.getY() == y - 1) {
                            if (map.get(el) == null) {
                                queue.add(el);
                                parents.put(el, serchCell);
                            } else if (map.get(el).getClass() == Hervibore.class) {
                                queue.add(el);
                                parents.put(el, serchCell);
                                flagExit =true;
                                break;
                            }
                        }
                    }
                    if (y + 1 <= 4) {
                        if (serchCell.getX() == el.getX() && el.getY() == y + 1) {
                            if (map.get(el) == null) {
                                queue.add(el);
                                parents.put(el, serchCell);
                            } else if (map.get(el).getClass() == Hervibore.class) {
                                queue.add(el);
                                parents.put(el, serchCell);
                                flagExit =true;
                                break;
                            }
                        }
                    }
                }
            }

            if (flagExit) {
                List<Cell> path = new ArrayList<>();
                for (Map.Entry<Cell, Cell> entry: parents.entrySet())
                {
                    if (serchCell.equals(entry.getValue()) && entry.getValue() != null) {
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

                int len = path.size() - getAttack();
                if (len < 0) {len = 0;}

                map.put(path.get(len), map.get(path.get(path.size() -1)));
                map.put(path.get(path.size() -1), null);
                MapDisplayer.showMap(map);
                break;
            }
        }

    }

    public boolean queuing (LinkedHashMap<Cell, Entity> map, Cell el, Queue<Cell> queue
            , LinkedHashMap<Cell, Cell> parents, Cell serchCell){
        boolean flagExit = false;
        if (map.get(el) == null) {
            queue.add(el);
            parents.put(el, serchCell);
        } else if (map.get(el).getClass() == Hervibore.class) {
            queue.add(el);
            parents.put(el, serchCell);
            flagExit =true;
        }
        return flagExit;
    }

    @Override
    void cellEmptyCheck() {

    }

//    private Queue<Cell> chek (Cell cell, Set<Cell> cellSet, LinkedHashMap<Cell, Entity> map, Queue<Cell> cellQueue) {
//        int x = cell.getX();
//        int y = cell.getY();
//        Cell cellHervibore = null;
//        //первый алгоритм
////        if (x - 1 >= 0 && y - 1 >= 0) {
////            for (Cell el: cellSet) {
////                if (el.getX() == x-1 && el.getY() == y -1) {
////                    if (map.get(el) == null) {
////                        cellQueue.add(el);
////                    } else if (map.get(el).getClass() == Hervibore.class) {
////                        cellHervibore = el;
////                        System.out.println("Хищник ест травоядного");
////                        break;
////                    }
////                }
////            }
////        }
////        if (x - 1 >= 0 && cellHervibore == null) {
////            for (Cell el: cellSet) {
////                if (el.getX() == x-1 && el.getY() == y) {
////                    if (map.get(el) == null) {
////                        cellQueue.add(el);
////                    } else if (map.get(el).getClass() == Hervibore.class) {
////                        cellHervibore = el;
////                        System.out.println("Хищник ест травоядного");
////                        break;
////                    }
////                }
////            }
////        }
////        if (x - 1 >= 0 && y + 1 <= 4 && cellHervibore == null) {
////            for (Cell el: cellSet) {
////                if (el.getX() == x-1 && el.getY() == y + 1) {
////                    if (map.get(el) == null) {
////                        cellQueue.add(el);
////                    } else if (map.get(el).getClass() == Hervibore.class) {
////                        cellHervibore = el;
////                        System.out.println("Хищник ест травоядного");
////                        break;
////                    }
////                }
////            }
////        }
////        if (y - 1 >= 0 && cellHervibore == null) {
////            for (Cell el: cellSet) {
////                if (el.getY() == y - 1) {
////                    if (map.get(el) == null) {
////                        cellQueue.add(el);
////                    } else if (map.get(el).getClass() == Hervibore.class) {
////                        cellHervibore = el;
////                        System.out.println("Хищник ест травоядного");
////                        break;
////                    }
////                }
////            }
////        }
////        if (y + 1 <= 4 && cellHervibore == null) {
////            for (Cell el: cellSet) {
////                if (el.getY() == y + 1) {
////                    if (map.get(el) == null) {
////                        cellQueue.add(el);
////                    } else if (map.get(el).getClass() == Hervibore.class) {
////                        cellHervibore = el;
////                        System.out.println("Хищник ест травоядного");
////                        break;
////                    }
////                }
////            }
////        }
////        if (x + 1 <= 4 && y - 1 >= 0 && cellHervibore == null) {
////            for (Cell el: cellSet) {
////                if (el.getX() == x + 1 && el.getY() == y - 1) {
////                    if (map.get(el) == null) {
////                        cellQueue.add(el);
////                    } else if (map.get(el).getClass() == Hervibore.class) {
////                        cellHervibore = el;
////                        System.out.println("Хищник ест травоядного");
////                        break;
////                    }
////                }
////            }
////        }
////        if (x + 1 <= 4 && cellHervibore == null) {
////            for (Cell el: cellSet) {
////                if (el.getX() == x + 1) {
////                    if (map.get(el) == null) {
////                        cellQueue.add(el);
////                    } else if (map.get(el).getClass() == Hervibore.class) {
////                        cellHervibore = el;
////                        System.out.println("Хищник ест травоядного");
////                        break;
////                    }
////                }
////            }
////        }
////        if (x + 1 <= 4 && y + 1 <= 4 && cellHervibore == null) {
////            for (Cell el: cellSet) {
////                if (el.getX() == x + 1 && el.getY() == y + 1) {
////                    if (map.get(el) == null) {
////                        cellQueue.add(el);
////                    } else if (map.get(el).getClass() == Hervibore.class) {
////                        cellHervibore = el;
////                        System.out.println("Хищник ест травоядного");
////                        break;
////                    }
////                }
////            }
////        }
//
//        if (cellHervibore == null) {
//            cell = cellQueue.peek();
//            chek(cell, cellSet, map, cellQueue);
//        }
//        return cellQueue;
//
//    }

    //         На что может потратить ход хищник:
//        Переместиться (чтобы приблизиться к жертве - травоядному)
//        Атаковать травоядное. При этом количество HP травоядного уменьшается на силу атаки хищника.
//        Если значение HP жертвы опускается до 0, травоядное исчезает
    public Predator(int energy, int attack) {
        this.energy = energy;
        this.attack = attack;
       // this.setCell(cell);
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
