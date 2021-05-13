package worldObjects;

import graphics.DrawPriorities;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public interface WorldGameObject  extends Serializable {
    DrawPriorities getDrawPriority();
    BufferedImage getSpriteSheet();
    void changeSprite();
    void setSpriteSheet();

}
