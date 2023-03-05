package simulation;

import simulation.Creature.Entity;

import java.util.*;

public class MapClass {
    private HashMap<Cell, Entity> cells;
    final int width;
    final int height;

    public MapClass(int width, int height) {
        cells = new HashMap<>();
        this.width = width;
        this.height = height;
    }

    public boolean contains(Cell cell) {
        return cells.containsKey(cell);
    }

    public void add(Cell cell, Entity entity) {
        cells.put(cell, entity);
    }

    public void updateEntity(Cell cell, Entity entity) {
        add(cell, entity);
    }

    public void del(Cell cell) {
        cells.remove(cell);
    }

    public Entity getEntity(Cell cell) {
        return cells.get(cell);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Set<Cell> getCells() {
        return cells.keySet();
    }
}
