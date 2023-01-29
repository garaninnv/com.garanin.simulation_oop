import java.util.LinkedHashMap;
import java.util.Set;

public class MapDisplayer {

    public static void showMap(LinkedHashMap<Cell, Entity> map){
        Set<Cell> cellSet = map.keySet();
        int index = 4;
        int count = 0;
        int lineX = 0;
        int i;
        System.out.print("   |");
        for (i = 0; i <= index; i++){
            System.out.print("Y" + i + " |");
            if (i == index) {
                System.out.println();
            }
        }
        i = 0;
        System.out.print("X"+ i + " |");
        for (Cell key: cellSet) {
            if (lineX ==  5) {
                lineX=0;
                i++;
                System.out.print("X"+ i + " |");
            }

            if (map.get(key) == null){
                if (index == count) {
                    System.out.println("   |");
                    count = 0;
                } else {
                    System.out.print("   |");
                    count++;
                }
            } else {
                if (index == count) {
                    System.out.println(map.get(key).getName()+"|");
                    count = 0;
                } else {
                    System.out.print(map.get(key).getName() + "|");
                    count++;
                }
            }
            lineX++;
        }
        System.out.println("____________________");
    }

}
