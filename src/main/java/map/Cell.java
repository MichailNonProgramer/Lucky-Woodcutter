package map;

import utils.Point;
import worldObjects.WorldGameObject;

import java.util.ArrayList;


public class Cell {
    public static final int cellSize = 90;
    private final Point point;
    private final ArrayList<WorldGameObject> objectsInCell = new ArrayList<>();

    public Cell(int x, int y)   {
        this(new Point(x, y));
    }

    public Cell(Point point)   {
        this.point = point;
    }

    public ArrayList<WorldGameObject> getObjectsInCell() {
        return this.objectsInCell;
    }

    public void addObjectInCell(WorldGameObject worldGameObject) {
        objectsInCell.add(worldGameObject);
    }

    public void removeObjectFromCell(WorldGameObject worldGameObject) {
        objectsInCell.remove(worldGameObject);
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
