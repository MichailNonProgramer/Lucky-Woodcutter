package worldObjects.destructibleObject;

import graphics.DrawPriorities;
import graphics.sprites.StoneWallSprites;
import graphics.sprites.WoodenWallSprites;
import utils.Point;
import worldObjects.Solid;

public class StoneWall extends DestructibleObject implements Solid {
    public StoneWall(int x, int y) {
        this(new Point(x, y));
    }

    public StoneWall(Point point) {
        super(point,10, StoneWallSprites.DAY, Resources.Stone, 2);
    }

    @Override
    public DrawPriorities getDrawPriority() {
        return DrawPriorities.Wall;
    }

    @Override
    public void changeSprite() {

    }
}