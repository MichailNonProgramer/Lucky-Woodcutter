package map.area;

import config.Config;
import map.Cell;
import map.Point;


public class VisibleArea extends Area {
    // разобраться почему равны 0 при инициализации
    private int leftXBorderDist = Config.getScreenWidth() / 2 / Cell.cellSize;
    private int leftYBorderDist = Config.getScreenHeight() / 2 / Cell.cellSize;
    private int rightXBorderDist = Config.getScreenWidth() / 2 / Cell.cellSize + 1;
    private int rightYBorderDist = Config.getScreenHeight() / 2 / Cell.cellSize + 1;

    public VisibleArea(int x, int y) {
        super(x, y);
    }

    public VisibleArea(Point cords) {
        super(cords);
    }

    protected int getLeftBoundX() {
        return getX() - Config.getScreenWidth() / 2 / Cell.cellSize;
    }

    protected int getLeftBoundY() {
        return getY() - Config.getScreenHeight() / 2 / Cell.cellSize;
    }

    protected int getRightBoundX() {
        return getX() + Config.getScreenWidth() / 2 / Cell.cellSize + 1;
    }

    protected int getRightBoundY() {
        return getY() + Config.getScreenHeight() / 2 / Cell.cellSize + 1;
    }
}
