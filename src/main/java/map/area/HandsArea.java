package map.area;

import map.Point;

public class HandsArea extends Area {
    public HandsArea(int x, int y) {
        super(x, y);
    }

    public HandsArea(Point cords) {
        super(cords);
    }

    // на 2 клетки во все стороны
    @Override
    protected int getLeftBoundX() {
        return getX() - 2;
    }

    @Override
    protected int getLeftBoundY() {
        return getY() - 2;
    }

    @Override
    protected int getRightBoundX() {
        return getX() + 3;
    }

    @Override
    protected int getRightBoundY() {
        return getY() + 3;
    }
}
