import java.util.*;

public class Actions {
    private int moveCounter = 1;

    public void initMap(LinkedHashMap<Cell, Entity> map, int maxX, int maxY) {
        for (int x = 0; x < maxY; x++) {
            for (int y = 0; y < maxX; y++) {
                map.put(new Cell(x, y), null);
            }
        }
    }

    //Действия, совершаемые перед стартом симуляции. Пример - расставить объекты и существ на карте
    //рандомная инициализация хищника, двух животных, камней, деревьев, травы.
    public void initActions(LinkedHashMap<Cell, Entity> map) {
        map.put(createObjectsForGame(map), new Predator(2, 4));
        map.put(createObjectsForGame(map), new Predator(5, 2));
        map.put(createObjectsForGame(map), new Hervibore(5, 5));
        map.put(createObjectsForGame(map), new Hervibore(3, 4));
        map.put(createObjectsForGame(map), new Rock());
        map.put(createObjectsForGame(map), new Tree());
        map.put(createObjectsForGame(map), new Grass());
        map.put(createObjectsForGame(map), new Grass());
        map.put(createObjectsForGame(map), new Grass());
        map.put(createObjectsForGame(map), new Grass());
    }

    // запуск функции для выполнения хода всех существ в порядке очереди.
    public void turnActions(LinkedHashMap<Cell, Entity> map) throws InterruptedException {
        for (Cell key : map.keySet()) {
            if (map.get(key) != null) {
                if (map.get(key).getClass().equals(Predator.class)) {
                    ((Predator) map.get(key)).makeMove(map, key);
                    System.out.println("Хищник " + key + " делает ход");
                    MapDisplayer.showMap(map);
                    Thread.sleep(1000);
                } else if (map.get(key).getClass().equals(Hervibore.class)) {
                    ((Hervibore) map.get(key)).makeMove(map, key);
                    System.out.println("Травоядное " + key + " делает ход");
                    MapDisplayer.showMap(map);
                    Thread.sleep(1000);
                }
            }
        }
        System.out.println("Ход №" + this.moveCounter + " выполнен");
        this.moveCounter++;
    }

    // Реализация рандомного расположение игровых объектов
    private static Cell createObjectsForGame(LinkedHashMap<Cell, Entity> map) {
        int maxEl = map.keySet().size();
        int iEl = new Random().nextInt(maxEl);
        int iter = 0;
        List<Cell> cells = new ArrayList<>();
        for (Cell el : map.keySet()) {
            if (map.get(el) == null) {
                cells.add(el);
            }
        }
        return cells.get(new Random().nextInt(cells.size()));
    }
    public void addHerviboreAndGrass(LinkedHashMap<Cell, Entity> map) {
        map.put(createObjectsForGame(map), new Hervibore(6, 2));
        map.put(createObjectsForGame(map), new Hervibore(2, 6));
        map.put(createObjectsForGame(map), new Hervibore(3, 4));
        map.put(createObjectsForGame(map), new Hervibore(1, 9));
        map.put(createObjectsForGame(map), new Grass());
        map.put(createObjectsForGame(map), new Grass());
        map.put(createObjectsForGame(map), new Grass());
        map.put(createObjectsForGame(map), new Grass());
        map.put(createObjectsForGame(map), new Grass());
        map.put(createObjectsForGame(map), new Grass());
        map.put(createObjectsForGame(map), new Grass());
        map.put(createObjectsForGame(map), new Grass());
        map.put(createObjectsForGame(map), new Grass());
        map.put(createObjectsForGame(map), new Grass());
        map.put(createObjectsForGame(map), new Grass());
        map.put(createObjectsForGame(map), new Grass());
    }
}
