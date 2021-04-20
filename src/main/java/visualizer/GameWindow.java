package visualizer;

import config.Config;
import utils.Camera;
import game.Game;
import map.Cell;
import map.GameMap;
import utils.FPSCounter;
import utils.KeyHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameWindow extends JPanel implements Runnable {
    private boolean running;
    private final int width;
    private final int height;
    private BufferedImage bufferedImage;
    private Graphics2D graphics2D;
    private static Thread thread;
    private final Game game = new Game();

    public Camera getCamera() {
        return camera;
    }

    private Camera camera;
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
            //fpsCounter = new FPSCounter();
            //fpsCounter.start();
            update();
            //fpsCounter.interrupt();
            //System.out.println(fpsCounter.fps());
        }
        setVisible(false);
    }


    public void init() {
        addNotify();
        camera = new Camera(0, 0);
        camera.centerOnPlayer(game.player);
        running = true;
        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        graphics2D = bufferedImage.createGraphics();
        this.addKeyListener(new KeyHandler(game.player, camera));

    }

    @Override
    public void paintComponent(Graphics G) {
        Graphics2D Graph2D = (Graphics2D) G;
        if (bufferedImage == null) {
            System.err.println("BuffImg is null");
        }

        graphics2D = bufferedImage.createGraphics();
        graphics2D.clearRect(0, 0, Config.getScreenWidth(), Config.getScreenHeight());
        for (var point : game.player.visibleCords) {
            Cell cell = GameMap.getMap().get(point);
            if (cell != null) {
                for (var worldObj : cell.getObjectsInCell()) {
                    graphics2D.drawImage(worldObj.getSpriteSheet(),
                            (int) (cell.getX() - camera.getxOffset()),
                            (int) (cell.getY() - camera.getyOffset()), Cell.cellSize, Cell.cellSize, null);
                }
            }
        }

//        for (var cell : GameMap.getMap().values()) {
//            var newX = cell.getX();
//            var newY = cell.getY();
//            // Сделать чтобы не каждый раз перерисовывалось
//            for (var worldObj : cell.getObjectsInCell()) {
//                graphics2D.drawImage(worldObj.getSpriteSheet(), newX, newY, Cell.cellSize, Cell.cellSize, null);
//            }
//
//        }
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
