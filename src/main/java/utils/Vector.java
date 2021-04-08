package utils;

public class Vector {

    private float x;
    private float y;

    public static float worldX;
    public static float worldY;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Vector() {
        x = 0;
        y = 0;
    }

    public Vector(Vector vector) {
        new Vector(vector.getX(), vector.getY());
    }

    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void addX(float value) {
        x += value;
    }

    public void addY(float value) {
        y += value;
    }

    public void setX(float value) {
        x = value;
    }

    public void setY(float value) {
        y = value;
    }

    public void setVector(Vector vector) {
        this.x = vector.x;
        this.y = vector.y;
    }

    public void setVector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public static void setWorldVectorCords(float x, float y) {
        worldX = x;
        worldY = y;
    }

    public static float getWorldVectorX(float x) {
        return x - worldX;
    }

    public static float getWorldVectorY(float y) {
        return y - worldY;
    }

    public Vector getWorldVector() {
        return new Vector(x - worldX, y - worldY);
    }

    @Override
    public String toString() {
        return x + ", " + y;
    }
}
