package map;

import utils.Point;
import worldObjects.WorldGameObject;

import java.io.Serializable;
import java.util.ArrayList;


public class Cell implements Serializable {
    public static final int cellSize = 70;
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

    @Override
    public boolean equals(Object obj){
        try {
            return this.point == ((Cell) obj).point && eqObjectInCell((Cell) obj);
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.point.x;
        hash = 71 * hash + this.point.y;
        return hash;
    }

    private boolean eqObjectInCell(Cell cell){
        for (WorldGameObject object : cell.getObjectsInCell()) {
            if (!this.objectsInCell.contains(object))
                return false;
        }
        return true;
    }
}
