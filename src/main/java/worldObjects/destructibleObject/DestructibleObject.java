package worldObjects.destructibleObject;

import map.Point;
import worldObjects.WorldGameObject;

import java.awt.image.BufferedImage;

public abstract class DestructibleObject implements WorldGameObject {
    private int lives;
    private final Point point;
    private BufferedImage spriteSheet;

    public DestructibleObject(Point point, int lives, BufferedImage spriteSheet) {
        this.point = point;
        this.lives = lives;
        this.spriteSheet = spriteSheet;
    }

    public int getLives() {
        return this.lives;
    }

    public void reduceLives() {
        this.lives -= 1;
    }

    public Point getPoint() {
        return point;
    }

    @Override
    public BufferedImage getSpriteSheet() {
        return spriteSheet;
    }

    public void setSpriteSheet(BufferedImage spriteSheet) {
        this.spriteSheet = spriteSheet;
    }
}
