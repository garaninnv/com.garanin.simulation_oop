package simulation.Creature;

public class Tree extends Entity {
    public Tree() {
        setName("Дер");
    }

    @Override
    public String toString() {
        return getName();
    }
}
