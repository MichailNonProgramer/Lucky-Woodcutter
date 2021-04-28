package graphics.sprites;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public abstract class SpriteLoader {
    protected static BufferedImage loadSprite(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (Exception e) {
            try {
                return ImageIO.read(new File("src\\main\\java\\graphics\\images\\errors\\Error.png"));
            }
            catch (Exception exception){
                System.out.println(e.getMessage());
                System.exit(1);
                return null;
            }
        }
    }
}
