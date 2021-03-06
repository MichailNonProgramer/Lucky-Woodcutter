package worldObjects.destructibleObjects;

import graphics.DrawPriorities;
import graphics.sprites.BoulderSprites;
import utils.Point;
import utils.RandomInt;
import worldObjects.Solid;

import java.awt.image.BufferedImage;

public class Boulder extends DestructibleObject implements Solid {

    public Boulder(int x, int y) {
        this(new Point(x, y));
    }

    public Boulder(Point point) {
        super(point, RandomInt.getValue(12, 7), BoulderSprites.DAY, Resources.Stone, RandomInt.getValue(64, 47), 0);
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

    @Override
    public void setSpriteSheet() {
        super.setSpriteSheet(BoulderSprites.DAY);
    }
}