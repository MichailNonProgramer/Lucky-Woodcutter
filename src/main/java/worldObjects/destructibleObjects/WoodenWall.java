package worldObjects.destructibleObjects;

import graphics.DrawPriorities;
import graphics.sprites.WoodenWallSprites;
import utils.Point;
import worldObjects.Buildable;
import worldObjects.Solid;

public class WoodenWall extends DestructibleObject implements Solid, Buildable {
    public WoodenWall(int x, int y) {
        this(new Point(x, y));
    }

    public WoodenWall(Point point) {
        super(point,10, WoodenWallSprites.DAY, Resources.Wood, 5, 0);
    }

    @Override
    public DrawPriorities getDrawPriority() {
        return DrawPriorities.Wall;
    }

    @Override
    public void changeSprite() {
    }

    @Override
    public int getBuildCost() {
        return 10;
    }

    @Override
    public void setSpriteSheet() {
        super.setSpriteSheet(WoodenWallSprites.DAY);
    }
}
