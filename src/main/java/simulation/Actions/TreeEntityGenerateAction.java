package simulation.Actions;

import simulation.Creature.Entity;
import simulation.Creature.Tree;
import simulation.MapClass;

public class TreeEntityGenerateAction extends EntityGenerateAction<Tree>{
    public TreeEntityGenerateAction(MapClass map) {
        super.spawnRate = map.getHeight() * map.getWidth() / 20 ;
    }

    @Override
    Tree createEntity() {
        return new Tree();
    }
}
