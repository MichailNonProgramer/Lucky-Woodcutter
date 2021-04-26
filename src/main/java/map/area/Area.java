package map.area;

import map.Point;

import java.util.ArrayList;

public abstract class Area {
    private ArrayList<Point> activeCords;
    private Point cords;

    public int getX() {
        return cords.x;
    }

    public int getY() {
        return cords.y;
    }

    public ArrayList<Point> getActiveCords() {
        return activeCords;
    }

    public void setUpdatedActiveCords(Point newCords) {
        this.cords = newCords;
        this.activeCords = spawnNewActiveCords();
    }

    public void setActiveCords(ArrayList<Point> activeCords) {
        this.activeCords = activeCords;
    }

    public Area(int x, int y) {
        this.cords = new Point(x, y);
        this.activeCords = spawnNewActiveCords();
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
                arr.add(new Point(i, j));
            }
        return arr;
    }

    private Point[] generateBoundPoints() {
        return new Point[]{new Point(getLeftBoundX(), getLeftBoundY()),
                new Point(getRightBoundX(), getRightBoundY())};
    }

    protected abstract int getLeftBoundX();

    protected abstract int getLeftBoundY();

    protected abstract int getRightBoundX();

    protected abstract int getRightBoundY();

}
