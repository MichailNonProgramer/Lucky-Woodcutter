package gameLogic.handlers;

import utils.Point;

// нужны для вырезания клеток, на которых могут быть коллизии, связанные с направлением движения
public class ViewDirectionsConfig {
    public static final Point upDown1 = new Point(-1, 0);
    public static final Point upDown2 = new Point(0, 0);
    public static final Point upDown3 = new Point(1, 0);

    public static final Point rightLeft1 = new Point(0, -1);
    public static final Point rightLeft2 = new Point(0, 0);
    public static final Point rightLeft3 = new Point(0, 1);

    public static final Point up1 = new Point(1, -1);
    public static final Point up2 = new Point(-1, -1);

    public static final Point down1 = new Point(-1, 1);
    public static final Point down2 = new Point(1, 1);

    public static final Point right1 = new Point(1, -1);
    public static final Point right2 = new Point(1, 1);

    public static final Point left1 = new Point(-1, -1);
    public static final Point left2 = new Point(-1, 1);
}
