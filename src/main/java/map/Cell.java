package map;

import utils.Point;
import worldObjects.WorldGameObject;

import java.io.Serializable;
import java.util.ArrayList;


public class Cell implements Serializable {
    public static final int cellSize = 90;
    private final Point point;
    private final ArrayList<WorldGameObject> objectsInCell = new ArrayList<>();
    private static final long serialVersionUID = 1L;

    public Cell(int x, int y) {
        this(new Point(x, y));
    }

    public Cell(Point point) {
        this.point = point;
    }

    public ArrayList<WorldGameObject> getObjectsInCell() {
        return this.objectsInCell;
    }

    public synchronized void addObjectInCell(WorldGameObject worldGameObject) {
        objectsInCell.add(worldGameObject);
    }

    public synchronized void removeObjectFromCell(WorldGameObject worldGameObject) {
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
