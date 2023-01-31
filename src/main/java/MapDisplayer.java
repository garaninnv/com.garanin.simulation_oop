import java.util.LinkedHashMap;
import java.util.Set;

public class MapDisplayer {

    //отрисовка игрового поля с героями
    public static void showMap(LinkedHashMap<Cell, Entity> map) {
        Set<Cell> cellSet = map.keySet();
        //размерность поля fieldSize X fieldSize
        int fieldSize = 4;
        //счетчик столбцов
        int columnCount;

        int lineCounter = 0;
        int lineNumber;
        System.out.print("   |");
        for (columnCount = 0; columnCount <= fieldSize; columnCount++) {
            System.out.print("Y" + columnCount + " |");
            if (columnCount == fieldSize) {
                System.out.println();
            }
        }
        lineNumber = 0;
        columnCount = 0;
        System.out.print("X" + lineNumber + " |");
        for (Cell key : cellSet) {
            if (lineCounter == 5) {
                lineCounter = 0;
                lineNumber++;
                System.out.print("X" + lineNumber + " |");
            }

            if (map.get(key) == null) {
                if (fieldSize == columnCount) {
                    System.out.println("   |");
                    columnCount = 0;
                } else {
                    System.out.print("   |");
                    columnCount++;
                }
            } else {
                if (fieldSize == columnCount) {
                    System.out.println(map.get(key).getName() + "|");
                    columnCount = 0;
                } else {
                    System.out.print(map.get(key).getName() + "|");
                    columnCount++;
                }
            }
            lineCounter++;
        }
        System.out.println("_______________________________________");
    }

}
