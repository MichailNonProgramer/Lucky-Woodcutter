package worldObjects;

import graphics.DrawPriorities;

import java.awt.image.BufferedImage;

public interface WorldGameObject {
    DrawPriorities getPriority();
    BufferedImage getSpriteSheet();
    void changeSprite();
}
