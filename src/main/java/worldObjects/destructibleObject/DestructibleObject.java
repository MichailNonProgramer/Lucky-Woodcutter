package worldObjects.destructibleObject;

import map.Cell;
import utils.Point;
import worldObjects.WorldGameObject;

import java.awt.image.BufferedImage;

public abstract class DestructibleObject implements WorldGameObject {
    private int lives;
    private final Point point;
    private transient BufferedImage spriteSheet;
    private final Resources resourceName;
    private final int resourcesCount;
    private static final long serialVersionUID = 1L;

    public DestructibleObject(Point point, int lives, BufferedImage spriteSheet, Resources resourceName, int resourcesCount) {
        this.point = point;
        this.lives = lives;
        this.spriteSheet = spriteSheet;
        this.resourceName = resourceName;
        this.resourcesCount = resourcesCount;
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

    @Override
    public boolean equals(Object obj){
        try {
            return this.point.equals(((DestructibleObject) obj).point)
                    && this.lives == ((DestructibleObject) obj).getLives()
                    && this.resourceName == ((DestructibleObject) obj).getResourceName()
                    && this.resourcesCount == ((DestructibleObject) obj).getResourcesCount();
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.point.x;
        hash = 71 * hash + this.point.y;
        hash = hash * this.resourcesCount;
        return hash;
    }
}
