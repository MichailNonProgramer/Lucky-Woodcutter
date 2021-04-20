package visualizer;

import game.Game;
import map.Cell;
import map.GameMap;
import utils.FPSCounter;
import utils.KeyHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameWindow extends JPanel implements Runnable {
    private boolean running;
    private final int width;
    private final int height;
    private BufferedImage bufferedImage;
    private Graphics2D graphics2D;
    private static Thread thread;
    private final Game game = new Game();
    private FPSCounter fpsCounter;

    public GameWindow(int width, int height) {
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();

    }

    @Override
    public void run() {

        init();
        while (running) {
            fpsCounter = new FPSCounter();
            fpsCounter.start();
            update();
            fpsCounter.interrupt();
            System.out.println(fpsCounter.fps());
        }
        setVisible(false);
    }


    public void init() {
        addNotify();
        running = true;
        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        graphics2D =  bufferedImage.createGraphics();
        this.addKeyListener(new KeyHandler(game.player));

    }

    @Override
    public void paintComponent(Graphics G)
    {
        Graphics2D Graph2D = (Graphics2D)G;


        if(bufferedImage == null)
        {
            System.err.println("BuffImg is null");
        };

        graphics2D =  bufferedImage.createGraphics();
        for (var entry : GameMap.spawnEmptyMap().values()) {
            var newX = entry.getX();
            var newY = entry.getY();
            graphics2D.drawImage(entry.getSpriteSheet(), newX, newY, Cell.cellSize, Cell.cellSize, null);
        }
        graphics2D.drawImage(game.player.getSpriteSheet(), game.player.getX() *  Cell.cellSize,
                    game.player.getY() *  Cell.cellSize, Cell.cellSize, Cell.cellSize, null);
        graphics2D.dispose();


        Graph2D.drawImage(bufferedImage, 0, 0, this);
    }


    public void addNotify() {
        super.addNotify();

        if (thread == null) {
            thread = new Thread(this, "GameThread");
            thread.start();
        }
    }

    public void update() {
        repaint();
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }
}
