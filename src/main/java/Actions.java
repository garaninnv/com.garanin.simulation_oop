import java.util.LinkedHashMap;
import java.util.Random;

public class Actions {

    public static void initMap(LinkedHashMap<Cell, Entity> map, int maxX, int maxY) {
        for (int i = 0; i < maxY; i++){
            for (int j = 0; j < maxX; j ++ ) {
                map.put(new Cell(i, j), null);
            }
        }
    }
    //действия, совершаемые перед стартом симуляции. Пример - расставить объекты и существ на карте
    //рандомная инициализация хищника, двух животных, камней, деревьев, травы.
    public static void initActions (LinkedHashMap<Cell, Entity> map) {
        map.put(randomCell(map), new Predator(5,5));
        map.put(randomCell(map), new Hervibore(8));
        map.put(randomCell(map), new Hervibore(10));
        map.put(randomCell(map), new Rock());
        map.put(randomCell(map), new Tree());
        map.put(randomCell(map), new Grass());
        map.put(randomCell(map), new Grass());
        map.put(randomCell(map), new Grass());
        MapDisplayer.showMap(map);
    }

    // запуск функции для выполнения хода всех существ в порядке очереди.
    public static void turnActions (LinkedHashMap<Cell, Entity> map){
        for (Cell key: map.keySet()) {
            if (map.get(key) != null) {
                if (map.get(key).getClass().equals(Predator.class)) {
                    ((Predator) map.get(key)).makeMove(map, key);
                    //System.out.println("Хищник делает ход");
                    MapDisplayer.showMap(map);
                } else if (map.get(key).getClass().equals(Hervibore.class)) {
                    //((Hervibore) map.get(key)).makeMove(map);
                    System.out.println("Травоядное делает ход");
                }
            }
        }
    }

    public static Cell randomCell (LinkedHashMap<Cell, Entity> map) {
        int maxEl = map.keySet().size();
        int iEl = new Random().nextInt(maxEl);
        int iter = 0;
        for (Cell el : map.keySet()) {
            if (iEl == iter) {
                if (map.get(el) == null){
                    return el;
                } else {
                    randomCell(map);
                }
            } else {
                iter++;
            }
        }
        return null;
    }
}
