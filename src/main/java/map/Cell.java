package map;

import worldObjects.WorldGameObject;

import java.util.ArrayList;

public class Cell {
    public static final int cellSize = 50;
    private final Point point;
    private final ArrayList<WorldGameObject> objectInCell = new ArrayList<>();

    public Cell(int x, int y) {
        this.point = new Point(x, y);
    }

    public int getX() {
        return point.x;
    }

    public int getY() {
        return point.y;
    }

    public Point getPoint() {
        return point;
    }
}
