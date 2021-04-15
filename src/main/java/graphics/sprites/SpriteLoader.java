package graphics.sprites;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class SpriteLoader {
    protected static BufferedImage loadSprite(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (Exception e) {
            return null;
        }
    }
}
