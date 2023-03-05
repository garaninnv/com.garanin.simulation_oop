package simulation.Creature;

public class Grass extends Entity {
    public Grass() {
        setName("Тра");
    }

    @Override
    public String toString() {
        return getName();
    }
}
