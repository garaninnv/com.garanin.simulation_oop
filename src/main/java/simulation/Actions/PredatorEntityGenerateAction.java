package simulation.Actions;

import simulation.Creature.Predator;
import simulation.MapClass;

public class PredatorEntityGenerateAction extends EntityGenerateAction<Predator> {
    public PredatorEntityGenerateAction(MapClass map) {
        super.spawnRate = map.getHeight() * map.getWidth() / 10;
    }

    @Override
    Predator createEntity() {
        return new Predator(3, 5);
    }
}
