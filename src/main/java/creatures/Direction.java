package creatures;

import map.Point;

public enum Direction {
    UP(new Point(0, -1)),
    DOWN(new Point(0, 1)),
    RIGHT(new Point(1, 0)),
    LEFT(new Point(-1, 0)),
    DEFAULT(new Point(0, 0));

    public Point getPoint() {
        return point;
    }

    private final Point point;

    Direction(Point point) {
        this.point = point;
    }
}
