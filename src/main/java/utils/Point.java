package utils;

import java.io.Serializable;

public class Point implements Serializable {
    public int x;
    public int y;
    private static final long serialVersionUID = 1L;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point add(Point other) {
        var nx = x + other.x;
        var ny = y + other.y;
        return new Point(nx, ny);
    }

    public Point mul(int value) {
        return new Point(x * value, y * value);
    }

    @Override
    public boolean equals(Object other) {
        try {
            return x == ((Point) other).x && y == ((Point) other).y;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.x;
        hash = 71 * hash + this.y;
        return hash;
    }
}
