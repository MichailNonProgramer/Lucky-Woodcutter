package worldObjects.destructibleObject;

import graphics.DrawPriorities;
import graphics.sprites.TreeSprites;
import map.Point;

import java.awt.image.BufferedImage;

public class Tree extends DestructibleObject{

    public Tree(Point point){
        super(point, 3, TreeSprites.DAY);
    }
    public Tree(int x, int y){
        super(new Point(x, y), 3,TreeSprites.DAY);
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
