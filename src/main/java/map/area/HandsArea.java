package map.area;

import creatures.Direction;
import map.Point;

public class HandsArea extends Area {
    private int lxOffset;
    private int lyOffset;
    private int rxOffset;
    private int ryOffset;

    public HandsArea(int x, int y, Direction dir) {
        super(x, y);
        switch (dir) {
            case UP:
                updateOffsetsUp();
                break;
            case DOWN:
                updateOffsetsDown();
                break;
            case RIGHT:
                updateOffsetsRight();
                break;
            case LEFT:
                updateOffsetsLeft();
                break;
        }
        updateActiveCords();
    }

    public HandsArea(Point cords, Direction dir) {
        super(cords);
        switch (dir) {
            case UP:
                updateOffsetsUp();
                break;
            case DOWN:
                updateOffsetsDown();
                break;
            case RIGHT:
                updateOffsetsRight();
                break;
            case LEFT:
                updateOffsetsLeft();
                break;
        }
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
        this.lyOffset = -2;
        this.rxOffset = 2;
        this.ryOffset = 1;
    }

    protected void updateOffsetsDown() {
        this.lxOffset = -1;
        this.lyOffset = 0;
        this.rxOffset = 2;
        this.ryOffset = 3;
    }

    protected void updateOffsetsLeft() {
        this.lxOffset = -2;
        this.lyOffset = -1;
        this.rxOffset = 1;
        this.ryOffset = 2;
    }

    protected void updateOffsetsRight() {
        this.lxOffset = 0;
        this.lyOffset = -1;
        this.rxOffset = 3;
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
