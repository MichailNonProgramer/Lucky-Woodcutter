package gameLogic.area;

import utils.Direction;
import utils.Point;

public class HandsArea extends Area {
    private int lxOffset;
    private int lyOffset;
    private int rxOffset;
    private int ryOffset;

    private final int attackLength = 2;

    public HandsArea(int x, int y, Direction dir) {
        this(new Point(x, y), dir);
    }

    public HandsArea(Point cords, Direction dir) {
        super(cords);
        updateBounds(dir);
        updateActiveCords();
    }

    @Override
    public void updateBounds(Direction dir) {
        switch (dir) {
            case UP:
                updateOffsetsUp();
                break;
            case DOWN:
                updateOffsetsDown();
                break;
            case LEFT:
                updateOffsetsLeft();
                break;
            case RIGHT:
                updateOffsetsRight();
                break;
        }

    }

    protected void updateOffsetsUp() {
        this.lxOffset = -1;
        this.lyOffset = -attackLength;
        this.rxOffset = 2;
        this.ryOffset = 1;
    }

    protected void updateOffsetsDown() {
        this.lxOffset = -1;
        this.lyOffset = 0;
        this.rxOffset = 2;
        this.ryOffset = attackLength + 1;
    }

    protected void updateOffsetsLeft() {
        this.lxOffset = -attackLength;
        this.lyOffset = -1;
        this.rxOffset = 1;
        this.ryOffset = 2;
    }

    protected void updateOffsetsRight() {
        this.lxOffset = 0;
        this.lyOffset = -1;
        this.rxOffset = attackLength + 1;
        this.ryOffset = 2;
    }

    @Override
    protected int getLeftBoundX() {
        return getX() + lxOffset;
    }

    @Override
    protected int getLeftBoundY() {
        return getY() + lyOffset;
    }

    @Override
    protected int getRightBoundX() {
        return getX() + rxOffset;
    }

    @Override
    protected int getRightBoundY() {
        return getY() + ryOffset;
    }
}
