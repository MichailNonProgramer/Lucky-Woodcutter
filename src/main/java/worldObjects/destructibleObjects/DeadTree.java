package worldObjects.destructibleObjects;

import graphics.DrawPriorities;
import graphics.sprites.TreeSprites;
import utils.Point;
import utils.RandomInt;
import worldObjects.Solid;

import java.awt.image.BufferedImage;

public class DeadTree extends DestructibleObject implements Solid {

    public DeadTree(int x, int y) {
        this(new Point(x, y));
    }

    public DeadTree(Point point) {
        super(point, RandomInt.getValue(4, 2), TreeSprites.DEAD, Resources.Wood, RandomInt.getValue(23, 7), 5);
    }

    @Override
    public DrawPriorities getDrawPriority() {
        return DrawPriorities.Tree;
    }

    @Override
    public void changeSprite() {

    }

    @Override
    public void reduceLives() {
        super.reduceLives();
    }

    @Override
    public void setSpriteSheet() {
        super.setSpriteSheet(TreeSprites.DEAD);
    }
}
