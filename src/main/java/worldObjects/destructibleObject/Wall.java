package worldObjects.destructibleObject;

import graphics.DrawPriorities;
import map.Point;

import java.awt.image.BufferedImage;

public class Wall extends DestructibleObject{
    public Wall(Point point, int lives, BufferedImage spriteSheet, Resource resource, int countResource) {
        super(point, lives, spriteSheet, resource, countResource);
    }

    @Override
    public DrawPriorities getDrawPriority() {
        return null;
    }

    @Override
    public void changeSprite() {

    }
}
