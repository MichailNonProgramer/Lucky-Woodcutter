package worldObjects.destructibleObject;

import graphics.DrawPriorities;

import java.awt.image.BufferedImage;

public class Tree implements DestructibleObject{
    @Override
    public DrawPriorities getDrawPriority() {
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
