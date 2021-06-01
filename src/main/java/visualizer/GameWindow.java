package visualizer;

import config.GameConfig;
import game.Game;
import graphics.sprites.EffectsSprites;
import map.Cell;
import utils.FPSCounter;
import gameLogic.handlers.KeyHandler;
import gameLogic.handlers.MouseHandler;

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
    private Game game;

    public GameWindow(int width, int height) {
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
    }

    @Override
    public void run() {
        long now;
        long updateTime;
        long wait;

        final int TARGET_FPS = 60;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
        init();
        while (running) {
            FPSCounter.StartCounter();
            now = System.nanoTime();
            update();
            updateTime = System.nanoTime() - now;
            wait = (OPTIMAL_TIME - updateTime) / 1000000;

            try {
                Thread.sleep(wait);
            } catch (Exception e) {
                e.printStackTrace();
            }
            FPSCounter.StopAndPost();
        }

        setVisible(false);
    }


    public void init() {
        addNotify();
        game = new Game();
        running = true;
        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        graphics2D = bufferedImage.createGraphics();
        this.addKeyListener(new KeyHandler(game.player, game.getGameMap()));
        var mouseHandler = new MouseHandler(game.player, game.getGameMap());
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
    }

    @Override
    public void paintComponent(Graphics G) {
        Graphics2D Graph2D = (Graphics2D) G;
        if (bufferedImage == null) {
            System.err.println("BuffImg is null");
        }

        graphics2D = bufferedImage.createGraphics();
        graphics2D.setBackground(new Color(35, 129, 217));
        graphics2D.clearRect(0, 0, GameConfig.getScreenWidth(), GameConfig.getScreenHeight());


        for (var point : game.player.getVisibleArea().getActiveCords()) {
            Cell cell = game.getGameMap().getMap().get(point);
            if (cell != null) {
                for (var worldObj : cell.getObjectsInCell()) {
                    graphics2D.drawImage(worldObj.getSpriteSheet(),
                            cell.getX() - game.player.getCamera().getXOffset(),
                            cell.getY() - game.player.getCamera().getYOffset(),
                            Cell.cellSize, Cell.cellSize, null);
                }
            }
        }
        // отрисовка активной области игрока
        for (var point : game.player.getHandsArea().getActiveCords()) {
            Cell cell = game.getGameMap().getMap().get(point);
            if (cell != null) {
                graphics2D.drawImage(EffectsSprites.ACTIVE,
                        cell.getX() - game.player.getCamera().getXOffset(),
                        cell.getY() - game.player.getCamera().getYOffset(),
                        Cell.cellSize, Cell.cellSize, null);
            }
        }


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
