package map;

public class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void add(Point other) {
        x += other.x;
        y += other.y;
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
