package worldObjects;

import graphics.DrawPriorities;

import java.awt.image.BufferedImage;

public interface WorldGameObject {
    DrawPriorities getDrawPriority();
    BufferedImage getSpriteSheet();
    void changeSprite();
}
