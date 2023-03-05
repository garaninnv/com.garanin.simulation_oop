package simulation.Actions;

import simulation.Creature.Rock;
import simulation.MapClass;

public class RockEntityGenerateAction extends EntityGenerateAction<Rock>{
    public RockEntityGenerateAction(MapClass map) {
        super.spawnRate = map.getHeight() * map.getWidth() / 20;
    }

    @Override
    Rock createEntity() {
        return new Rock();
    }
}
