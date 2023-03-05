package simulation;

public class MapDisplayer {
    //отрисовка игрового поля с героями
    public static void showMap(MapClass map) {
        System.out.print("   |");
        for (int i = 0; i < map.getHeight(); i++) {
            System.out.print("Y " + i + "|");
        }
        System.out.println();
        for (int i = 0; i < map.getHeight(); i++) {
            System.out.print("X " + i + "|");
            for (int j = 0; j < map.getWidth(); j++) {
                if (map.contains(new Cell(i, j))) {
                    System.out.print(map.getEntity(new Cell(i, j)).getName() + "|");
                } else {
                    System.out.print("   |");
                }
            }
            System.out.println();
        }
        System.out.println("__________________________________________");
    }
}
