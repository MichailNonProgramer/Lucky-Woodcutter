package map;

import worldObjects.WorldGameObject;

import java.util.ArrayList;

public class Cell {
    private final int x;
    private final int y;
    public static final int cellSize = 50;
    private ArrayList<WorldGameObject> objectInCell;

    public Cell(int x, int y){
        this.x  = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
