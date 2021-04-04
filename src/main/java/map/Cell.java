package map;

import worldObjects.WorldGameObject;

public class Cell {
    private final int x;
    private final int y;
    public static final int cellSize = 20;
    public WorldGameObject objectInCell;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
