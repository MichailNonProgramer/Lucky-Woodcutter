package gameLogic.area;
import java.util.Collections;
import config.GameConfig;
import map.Cell;
import utils.Direction;
import utils.Point;

public class InfectionArea extends Area {
    private int lxOffset = -1;
    private int lyOffset = -1;
    private int rxOffset = 2;
    private int ryOffset = 2;


    public InfectionArea(int x, int y) {
        this(new Point(x, y));
    }

    public InfectionArea(Point cords) {
        super(cords);
        updateActiveCords();
    }


    protected int getLeftBoundX() {
        return getX() + lxOffset;
    }

    protected int getLeftBoundY() {
        return getY() + lyOffset;
    }

    protected int getRightBoundX() {
        return getX() + rxOffset;
    }

    protected int getRightBoundY() {
        return getY() + ryOffset;
    }


    @Override
    public void updateBounds(Direction dir) {
    }
}