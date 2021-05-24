package visualizer;

import config.GameConfig;
import creatures.persons.player.Player;
import game.Game;
import graphics.sprites.EffectsSprites;
import map.Cell;
import network.Client;
import network.Lock;
import utils.FPSCounter;
import gameLogic.handlers.KeyHandler;
import gameLogic.handlers.MouseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GameWindow extends JPanel implements Runnable {
    private boolean running;
    private final int width;
    private final int height;
    private BufferedImage bufferedImage;
    private Graphics2D graphics2D;
    private static Thread thread;
    private final Game game;

    public GameWindow(int width, int height, Game game) {
        this.width = width;
        this.height = height;
        this.game = game;
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
    }

    @Override
    public synchronized void run() {
        long now;
        long updateTime;
        long wait;

        final int TARGET_FPS = 60;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
        final int TARGET_UPDATE = 3;
        final long OPTIMAL_TIME_SERVER = 1000000000 / TARGET_UPDATE;
        init();
        var counter = 0;
        while (running) {
            FPSCounter.StartCounter();
            now = System.nanoTime();
            try {
                if (!game.getSoloGame()) {
                    if (counter == 60) {
                        if (!Lock.isLockClient) {
                            Lock.isLockClient = true;
                            var coord = game.getPlayer().getPoint();
                            //System.out.println(Lock.isLock);
                            Client.rememberChanges(game.getGameMap());
                            Client.start();
                            System.out.println("OK");
                            game.setGameMap(Client.getGameMap());
                            for (var obj : game.getGameMap().getMap().get(coord).getObjectsInCell()) {
                                if (obj instanceof Player && ((Player) obj).getId().equals(game.getPlayer().getId())) {
//                                    try {
//                                        for (var obj2 : game.getGameMap().getMap().get(game.getPlayer().getPoint()).getObjectsInCell())
//                                            if (obj2 instanceof Player)
//                                                if (!((Player) obj).getPoint().equals(((Player)obj2).getPoint()))
//                                                    game.getGameMap().getMap().get(((Player) obj2).getPoint()).removeObjectFromCell(obj2);
//                                    } catch (Exception e) {
//                                        System.out.println(e.getMessage());
//                                    }
                                    game.setPlayer((Player) obj);
                                   // System.out.println("Change Player");
                                    break;
                                }
                            }
                        }
                        Lock.isLockClient = false;
                        counter = 0;
                    }
                }
                update();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            updateTime = System.nanoTime() - now;
            wait = (OPTIMAL_TIME - updateTime) / 1000000;

            try {
                Thread.sleep(Math.max(0,wait));
                if (!game.getSoloGame())
                    counter += 1;
            } catch (Exception e) {
                e.printStackTrace();
            }
            FPSCounter.StopAndPost();
        }
        setVisible(false);
    }

    public void init() {
        addNotify();
        running = true;
        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        graphics2D = bufferedImage.createGraphics();
        this.addKeyListener(new KeyHandler(game.getPlayer(), game));
        var mouseHandler = new MouseHandler(game.getPlayer(), game.getGameMap());
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
        graphics2D.clearRect(0, 0, GameConfig.getScreenWidth(), GameConfig.getScreenHeight());
        for (var point : game.getPlayer().getVisibleArea().getActiveCords()) {
            Cell cell = game.getGameMap().getMap().get(point);
            if (cell != null) {
                for (var worldObj : cell.getObjectsInCell()) {
                    worldObj.setSpriteSheet();
                    graphics2D.drawImage(worldObj.getSpriteSheet(),
                            cell.getX() - game.getPlayer().getCamera().getXOffset(),
                            cell.getY() - game.getPlayer().getCamera().getYOffset(),
                            Cell.cellSize, Cell.cellSize, null);
                }
        }

        }
        // отрисовка активной области игрока
            for (var point : game.getPlayer().getHandsArea().getActiveCords()) {
                Cell cell = game.getGameMap().getMap().get(point);
                if (cell != null) {
                    graphics2D.drawImage(EffectsSprites.ACTIVE,
                            cell.getX() - game.getPlayer().getCamera().getXOffset(),
                            cell.getY() - game.getPlayer().getCamera().getYOffset(),
                            Cell.cellSize, Cell.cellSize, null);
                }
        }

        graphics2D.dispose();
        Graph2D.drawImage(bufferedImage, 0, 0, this);
    }


    public synchronized void addNotify() {
        super.addNotify();
        System.out.println(123);
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
