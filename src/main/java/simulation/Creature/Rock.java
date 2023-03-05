package simulation.Creature;

public class Rock extends Entity {
    public Rock() {
        setName("Кам");
    }

    @Override
    public String toString() {
        return getName();
    }
}
