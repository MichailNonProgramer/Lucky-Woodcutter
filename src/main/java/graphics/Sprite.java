package graphics;

import utils.Vector;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Sprite {
    private final BufferedImage SPRITESHEET;
    private BufferedImage[][] spriteArray;
    private final int TILESIZE = 32;
    private int width;
    private int height;
    private int widthSprite;
    private int heightSprite;


    private Sprite(String file) {
        width = TILESIZE;
        height = TILESIZE;

        System.out.println("Load" + file + "..");
        SPRITESHEET = loadSprite(file);

        widthSprite = SPRITESHEET.getWidth() / width;
        heightSprite = SPRITESHEET.getHeight() / height;
        loadSpriteArray();
    }

    public Sprite(String file, int width, int height) {
        this.width = width;
        this.height = height;

        System.out.println("Load" + file + "..");
        SPRITESHEET = loadSprite(file);

        widthSprite = SPRITESHEET.getWidth() / width;
        heightSprite = SPRITESHEET.getHeight() / height;
        loadSpriteArray();
    }

    public void setSize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public void setWidth(int width) {
        widthSprite = SPRITESHEET.getTileWidth() / width;
    }

    public void setHeight(int heght) {
        widthSprite = SPRITESHEET.getHeight() / heght;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private BufferedImage loadSprite(String file) {
        BufferedImage sprite = null;
        try {
            sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
        } catch (Exception e) {
            System.out.println("Error");
        }
        return sprite;
    }

    public void loadSpriteArray() {
        spriteArray = new BufferedImage[widthSprite][heightSprite];
        for (var x = 0; x < widthSprite; x++) {
            for (var y = 0; y < heightSprite; y++) {
                spriteArray[x][y] = getSprite(x, y);
            }
        }
    }

    public BufferedImage getSPRITESHEET() {
        return this.SPRITESHEET;
    }

    private BufferedImage getSprite(int x, int y) {
        return SPRITESHEET.getSubimage(x * width, y * height, width, height);
    }

    public BufferedImage[] getSpriteArray(int i) {
        return spriteArray[i];
    }

    public BufferedImage[][] getSpriteArray() {
        return spriteArray;
    }

    public static void drawArray(Graphics2D graphics2D, ArrayList<BufferedImage> bufferedImages, Vector position,
                                 int width, int height, int xOffset, int yOffset) {
        var x = position.getX();
        var y = position.getY();

        for (var i = 0; i < bufferedImages.size(); i++) {
            if (bufferedImages.get(i) != null)
                graphics2D.drawImage(bufferedImages.get(i), (int) x, (int) y, width, height, null);
            x += xOffset;
            y += yOffset;
        }
    }

//public static void drawImage(Graphics2D graphics2D, Font font, String word, Vector position, int width, int height, int xOffset, int yOffset){
//        var x = position.getX();
//        var y = position.getY();
//
//        for(int i = 0;i < word.length(); i++){
//            if(word.charAt(i) != 32)
//                graphics2D.drawImage(font.getFont(word.charAt(i)), x, y, width, height, null);
//        }
//}
}
