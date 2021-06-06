package worldObjects.destructibleObjects;

import graphics.DrawPriorities;
import graphics.sprites.StoneWallSprites;
import utils.Point;
import worldObjects.Buildable;
import worldObjects.Solid;

public class StoneWall extends DestructibleObject implements Solid, Buildable {
    public StoneWall(int x, int y) {
        this(new Point(x, y));
    }

    public StoneWall(Point point) {
        super(point, 20, StoneWallSprites.DAY, Resources.Stone, 5, 0);
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
        return 30;
    }

    @Override
    public void setSpriteSheet() {
        super.setSpriteSheet(StoneWallSprites.DAY);
    }
}