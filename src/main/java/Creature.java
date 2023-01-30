import java.util.HashMap;
import java.util.LinkedHashMap;

public abstract class Creature extends Entity {

    private int speed;
    private int hp;

    abstract void makeMove(LinkedHashMap<Cell, Entity> map, Cell cell);

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getSpeed() {
        return speed;
    }

    public int getHp() {
        return hp;
    }
}
