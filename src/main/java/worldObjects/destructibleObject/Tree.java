package worldObjects.destructibleObject;

import graphics.DrawPriorities;
import graphics.sprites.TreeSprites;
import map.Point;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Tree extends DestructibleObject {

    public Tree(int x, int y) {
        super(new Point(x, y), (int)(Math.random() * ((4 - 1) + 1)) + 1, TreeSprites.DAY);
    }

    public Tree(Point point) {
        super(point,(int)(Math.random() * ((4 - 1) + 1)) + 1, TreeSprites.DAY);
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
