import java.awt.*;
import java.util.*;

public class Simulation {


    public static void main(String[] args) throws AWTException, InterruptedException {
        int moveCounter = 0;
        int maxX = 5;
        int maxY = 5;

//        карта
        LinkedHashMap<Cell, Entity> map = new LinkedHashMap<>();

        //заполнение карты
        Actions.initMap(map, maxX, maxY);
        //инициализация объектов на карте
        Actions.initActions(map);
        //действия на карте ход, атака
        Actions.turnActions(map);

    }
}
