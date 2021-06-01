package worldObjects.destructibleObjects;

import graphics.DrawPriorities;
import graphics.sprites.TreeSprites;
import utils.Point;
import utils.RandomInt;
import worldObjects.Solid;

import java.awt.image.BufferedImage;

public class LiveTree extends DestructibleObject implements Solid {

    public LiveTree(int x, int y) {
        this(new Point(x, y));
    }

    public LiveTree(Point point) {
        super(point, RandomInt.getValue(9, 6), TreeSprites.DAY, Resources.Wood, RandomInt.getValue(100, 50), -50);
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
