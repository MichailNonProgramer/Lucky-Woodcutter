package worldObjects.destructibleObject;

import graphics.DrawPriorities;
import graphics.sprites.WoodenWallSprites;
import utils.Point;
import worldObjects.Solid;

public class WoodenWall extends DestructibleObject implements Solid {
    public WoodenWall(int x, int y) {
        this(new Point(x, y));
    }

    public WoodenWall(Point point) {
        super(point,5, WoodenWallSprites.DAY, Resources.Wood, 2);
    }

    @Override
    public DrawPriorities getDrawPriority() {
        return DrawPriorities.Wall;
    }

    @Override
    public void changeSprite() {

    }
}
