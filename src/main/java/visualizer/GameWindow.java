package visualizer;

import game.Game;
import map.Cell;
import map.GameMap;
import utils.KeyHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameWindow extends JPanel implements Runnable {
    private boolean running;
    private final int width;
    private final int height;
    private BufferedImage image;
    private Graphics2D graphics2D;
    private static Thread thread;
    private final Game game = new Game();

    private int fps = 60;


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
            long time = System.currentTimeMillis();
            update();
            time = (1000 / fps) - (System.currentTimeMillis() - time);
            if (time > 0) {
                try {
                    Thread.sleep(time);
                } catch (Exception ignored) {
                }
            }
        }
        setVisible(false);
    }

    private void input() {
    }

    private void draw(Graphics2D graphics2D) {
        Graphics graphics = this.getGraphics();
        for (var entry : GameMap.spawnEmptyMap().values()) {
            var newX = entry.getX();
            var newY = entry.getY();
            graphics.drawImage(entry.getSpriteSheet(), newX, newY, Cell.cellSize, Cell.cellSize, null);
        }
        graphics.drawImage(game.player.getSpriteSheet(), game.player.getX() *  Cell.cellSize,
                game.player.getY() *  Cell.cellSize, Cell.cellSize, Cell.cellSize, null);
        graphics.dispose();
    }

    public void init() {
        addNotify();
        running = true;
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        graphics2D = (Graphics2D) image.getGraphics();
        this.addKeyListener(new KeyHandler(game.player));
    }

    public void addNotify() {
        super.addNotify();

        if (thread == null) {
            thread = new Thread(this, "GameThread");
            thread.start();
        }
    }

    public void render() {
        draw(graphics2D);
    }

    public void update() {
        render();
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
