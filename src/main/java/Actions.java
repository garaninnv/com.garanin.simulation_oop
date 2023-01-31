import java.util.LinkedHashMap;
import java.util.Random;

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
    public void turnActions(LinkedHashMap<Cell, Entity> map) {
        for (Cell key : map.keySet()) {
            if (map.get(key) != null) {
                if (map.get(key).getClass().equals(Predator.class)) {
                    ((Predator) map.get(key)).makeMove(map, key);
                    System.out.println("Хищник делает ход");
                    MapDisplayer.showMap(map);
                } else if (map.get(key).getClass().equals(Hervibore.class)) {
                    ((Hervibore) map.get(key)).makeMove(map, key);
                    System.out.println("Травоядное делает ход");
                    MapDisplayer.showMap(map);
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
        for (Cell el : map.keySet()) {
            if (iEl == iter) {
                if (map.get(el) == null) {
                    return el;
                } else {
                    createObjectsForGame(map);
                }
            } else {
                iter++;
            }
        }
        return null;
    }
}
