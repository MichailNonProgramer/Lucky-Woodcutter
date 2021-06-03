package worldObjects;

import graphics.DrawPriorities;
import graphics.sprites.GroundSprites;
import map.Cell;

import java.awt.image.BufferedImage;

public class Ground implements WorldGameObject {
    private transient BufferedImage spriteSheet;

    public Ground() {
        this.spriteSheet = GroundSprites.DAY;
    }
    private static final long serialVersionUID = 1L;

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

    @Override
    public void setSpriteSheet() {
        this.spriteSheet = GroundSprites.DAY;
    }

    @Override
    public boolean equals(Object obj){
        try {
            return this.hashCode() == ((Ground)obj).hashCode();
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = DrawPriorities.GROUND.getValue() * 71;
        return hash;
    }


}
