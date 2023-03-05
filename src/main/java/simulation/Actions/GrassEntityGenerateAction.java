package simulation.Actions;

import simulation.Creature.Grass;
import simulation.MapClass;

public class GrassEntityGenerateAction extends EntityGenerateAction<Grass> {
    public GrassEntityGenerateAction(MapClass map) {
        super.spawnRate = map.getHeight() * map.getWidth() / 7;
    }

    @Override
    Grass createEntity() {
        return new Grass();
    }
}
