import java.util.HashMap;
import java.util.LinkedHashMap;

public class Hervibore extends Creature{
    private int hp;
    public Hervibore(int hp) {
        this.setName("ТрЖ");
        this.hp = hp;
    }
    //Cтремятся найти ресурс (траву), может потратить свой ход на движение в сторону травы, либо на её поглощение
    //Если значение HP жертвы опускается до 0, травоядное исчезает
    @Override
    public void makeMove(LinkedHashMap<Cell, Entity> map, Cell cell) {

    }

    //проверка свободна ли ячейка
    @Override
    void cellEmptyCheck() {

    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public String toString() {
        return "Hervibore{" +
                "hp=" + hp +
                "name=" + getName() +
                '}';
    }
}
