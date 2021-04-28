package worldObjects.destructibleObject;

import graphics.DrawPriorities;
import graphics.sprites.TreeSprites;
import graphics.sprites.WoodenWallSprites;
import map.Point;
import worldObjects.Solid;

import java.awt.image.BufferedImage;

public class WoodenWall extends DestructibleObject implements Solid {
    public WoodenWall(int x, int y) {
        super(new Point(x, y), 5, WoodenWallSprites.DAY, Resources.Wood, 2);
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
