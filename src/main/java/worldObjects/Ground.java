package worldObjects;

import graphics.DrawPriorities;
import graphics.sprites.GroundSprites;

import java.awt.image.BufferedImage;

public class Ground implements WorldGameObject {
    private BufferedImage spriteSheet;

    public Ground() {
        this.spriteSheet = GroundSprites.DAY;
    }

    @Override
    public DrawPriorities getDrawPriority() {
        return DrawPriorities.GROUND;
    }

    @Override
    public BufferedImage getSpriteSheet() {
        return this.spriteSheet;
    }

    @Override
    public void changeSprite() {

    }
}
