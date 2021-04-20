package worldObjects.destructibleObject;

import graphics.DrawPriorities;

import java.awt.image.BufferedImage;

public class Tree implements DestructibleObject{
    @Override
    public DrawPriorities getPriority() {
        return null;
    }

    @Override
    public BufferedImage getSpriteSheet() {
        return null;
    }

    @Override
    public void changeSprite() {

    }
}
