import java.util.HashMap;
import java.util.LinkedHashMap;

public abstract class Creature extends Entity{

    private int speed;
    abstract void makeMove(LinkedHashMap<Cell, Entity> map, Cell cell);

    abstract void cellEmptyCheck ();
}
