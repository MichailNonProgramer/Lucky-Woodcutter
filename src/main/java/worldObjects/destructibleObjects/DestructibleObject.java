package worldObjects.destructibleObjects;

import utils.Point;
import worldObjects.WorldGameObject;

import java.awt.image.BufferedImage;

public abstract class DestructibleObject implements WorldGameObject {
    private int lives;
    private final Point point;
    private BufferedImage spriteSheet;
    private final Resources resourceName;
    private final int resourcesCount;
    private final int scores;

    public DestructibleObject(Point point, int lives, BufferedImage spriteSheet, Resources resourceName, int resourcesCount, int scores) {
        this.point = point;
        this.lives = lives;
        this.spriteSheet = spriteSheet;
        this.resourceName = resourceName;
        this.resourcesCount = resourcesCount;
        this.scores = scores;
    }

    public int getScores() {
        return scores;
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

    public Resources getResourceName() {
        return resourceName;
    }

    public int getResourcesCount() {
        return resourcesCount;
    }
}
