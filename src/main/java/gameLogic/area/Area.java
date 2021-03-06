package gameLogic.area;

import config.GameConfig;
import map.Cell;
import utils.Direction;
import utils.Point;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Area  implements Serializable {
    private ArrayList<Point> activeCords;
    private Point cords;
    private static final long serialVersionUID = 1L;

    public int getX() {
        return cords.x;
    }

    public int getY() {
        return cords.y;
    }

    public ArrayList<Point> getActiveCords() {
        return activeCords;
    }

    public void setUpdatedActiveCords(Point newObjectCords) {
        this.cords = newObjectCords;
        this.activeCords = spawnNewActiveCords();
    }

    public void updateActiveCords() {
        this.activeCords = spawnNewActiveCords();
    }

    public void setActiveCords(ArrayList<Point> activeCords) {
        this.activeCords = activeCords;
    }

    public Area(int x, int y) {
        this(new Point(x, y));
    }

    public Area(Point cords) {
        this.cords = cords;
        this.activeCords = spawnNewActiveCords();
    }

    protected ArrayList<Point> spawnNewActiveCords() {
        var visibleBounds = generateBoundPoints();
        return generateActiveCords(visibleBounds);
    }

    private ArrayList<Point> generateActiveCords(Point[] visibleBounds) {
        var arr = new ArrayList<Point>();
        for (var i = visibleBounds[0].x; i < visibleBounds[1].x; i++)
            for (var j = visibleBounds[0].y; j < visibleBounds[1].y; j++) {
                if (i < 0 || j < 0
                        || i >= GameConfig.getMapWidth() / Cell.cellSize
                        || j >= GameConfig.getMapHeight() / Cell.cellSize)
                    continue;
                arr.add(new Point(i, j));
            }
        return arr;
    }

    private Point[] generateBoundPoints() {
        return new Point[]{new Point(getLeftBoundX(), getLeftBoundY()),
                new Point(getRightBoundX(), getRightBoundY())};
    }

    public abstract void updateBounds(Direction dir);

    protected abstract int getLeftBoundX();

    protected abstract int getLeftBoundY();

    protected abstract int getRightBoundX();

    protected abstract int getRightBoundY();

}
