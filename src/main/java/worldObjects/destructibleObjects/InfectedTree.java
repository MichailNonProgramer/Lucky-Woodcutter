package worldObjects.destructibleObjects;

import graphics.DrawPriorities;
import graphics.sprites.TreeSprites;
import utils.Point;
import utils.RandomInt;
import worldObjects.Solid;

import java.awt.image.BufferedImage;

public class InfectedTree extends DestructibleObject implements Solid {

    public InfectedTree(int x, int y) {
        this(new Point(x, y));
    }

    public InfectedTree(Point point) {
        super(point, RandomInt.getValue(6, 4), TreeSprites.INFECTED, Resources.Wood, RandomInt.getValue(23, 7), 15);
    }

    @Override
    public DrawPriorities getDrawPriority() {
        return DrawPriorities.Tree;
    }

    @Override
    public void reduceLives() {
        super.reduceLives();
    }

    @Override
    public void setSpriteSheet(BufferedImage spriteSheet) {
        super.setSpriteSheet(spriteSheet);
    }

    @Override
    public void changeSprite() {
    }
}
