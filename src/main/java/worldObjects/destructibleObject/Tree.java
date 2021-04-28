package worldObjects.destructibleObject;

import graphics.DrawPriorities;
import graphics.sprites.TreeSprites;
import map.Point;
import worldObjects.Solid;

import java.awt.image.BufferedImage;

public class Tree extends DestructibleObject implements Solid {

    public Tree(int x, int y) {
        super(new Point(x, y), (int)(Math.random() * ((4 - 1) + 1)) + 1, TreeSprites.DAY, Resources.Wood, 1);
    }

    public Tree(Point point) {
        super(point,(int)(Math.random() * ((4 - 1) + 1)) + 1, TreeSprites.DAY, Resources.Wood, 1);
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
