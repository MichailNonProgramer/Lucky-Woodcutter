package gameLogic.area;

import config.GameConfig;
import utils.Direction;
import map.Cell;
import utils.Point;


public class VisibleArea extends Area {
    private final int leftXBorderDist = GameConfig.getScreenWidth() / 2 / Cell.cellSize;
    private final int leftYBorderDist = GameConfig.getScreenHeight() / 2 / Cell.cellSize;
    private final int rightXBorderDist = GameConfig.getScreenWidth() / 2 / Cell.cellSize + 1;
    private final int rightYBorderDist = GameConfig.getScreenHeight() / 2 / Cell.cellSize + 1;

    public VisibleArea(int x, int y) {
        this(new Point(x, y));
    }

    public VisibleArea(Point cords) {
        super(cords);
        updateActiveCords();
    }


    protected int getLeftBoundX() {
        return getX() - leftXBorderDist;
    }

    protected int getLeftBoundY() {
        return getY() - leftYBorderDist;
    }

    protected int getRightBoundX() {
        return getX() + rightXBorderDist;
    }

    protected int getRightBoundY() {
        return getY() + rightYBorderDist;
    }


    @Override
    public void updateBounds(Direction dir) {
    }
}
