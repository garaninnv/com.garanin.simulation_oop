package simulation.Actions;

import simulation.Creature.Hervibore;
import simulation.MapClass;

public class HerviboreEntityGenerateAction extends EntityGenerateAction<Hervibore> {
    public HerviboreEntityGenerateAction(MapClass map) {
        super.spawnRate = map.getHeight() * map.getWidth() / 10;
    }

    @Override
    Hervibore createEntity() {
        return new Hervibore(4, 4);
    }
}
