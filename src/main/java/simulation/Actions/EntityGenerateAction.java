package simulation.Actions;

import simulation.Cell;
import simulation.Creature.Entity;
import simulation.MapClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

abstract class EntityGenerateAction<T extends Entity> extends Action {
    double spawnRate;

    @Override
    public void perform(MapClass map) throws IllegalArgumentException {
        int rate = 0;
        while (rate < spawnRate) {
            Cell cell;
            try {
                cell = getRandomEmptyCell(map);
                map.add(cell, createEntity());
                rate++;
            } catch (IllegalArgumentException e) {
                System.out.println("На карте не хватает места : " + e.getMessage());
                break;
            }
        }
    }

    // Реализация рандомного расположение игровых объектов
    private Cell getRandomEmptyCell(MapClass map) {
        List<Cell> cells = new ArrayList<>();
        for (int i = 0; i < map.getHeight(); i++) {
            for (int j = 0; j < map.getWidth(); j++) {
                if (!map.contains(new Cell(i, j))) {
                    cells.add(new Cell(i, j));
                }
            }
        }
        return cells.get(new Random().nextInt(cells.size()));
    }

    abstract T createEntity();
}
