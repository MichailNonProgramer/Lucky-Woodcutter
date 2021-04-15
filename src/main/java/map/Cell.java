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

    public Cell(Point point) {
        this.point = point;
    }

    public ArrayList<WorldGameObject> getObjectsInCell() {
        return this.objectInCell;
    }

    public void addObjectInCell(WorldGameObject worldGameObject) {
        objectInCell.add(worldGameObject);
    }

    public void removeObjectFromCell(WorldGameObject worldGameObject) {
        objectInCell.remove(worldGameObject);
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
