package game;

import states.GameStateManager;
import utils.KeyboardHandler;
import utils.MouseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;


public class GamePanel extends JPanel implements Runnable {

    public static int width;
    public static int height;

    private static Thread thread;
    private static Boolean running = false;

    private static BufferedImage image;
    private static Graphics2D graphics2D;

    final int billion = 1000000000;
    final double GAME_FPS = 60;
    final double gameUpdateDelay = billion / GAME_FPS;
    final int updateCountBeforeRender = 3;
    final double TARGET_FPS = 1000;
    final double targetUpdateDelay = billion / TARGET_FPS;

    private MouseHandler mouse;
    private KeyboardHandler keyboard;
    private GameStateManager gameStateManager;

    public GamePanel(int width, int height) {
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
    }

    public void init() {
        running = true;

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        graphics2D = (Graphics2D) image.getGraphics();

        mouse = new MouseHandler(this);
        keyboard = new KeyboardHandler(this);

        gameStateManager = new GameStateManager();

    }

    public void addNotify() {
        super.addNotify();

        if (thread == null) {
            thread = new Thread(this, "GameThread");
            thread.start();
        }
    }

    private int getLastSecondTime(double lastUpdateTime) {
        return (int) (lastUpdateTime / billion);
    }

    @Override
    public void run() {
        init();
        gameLoop();
    }

    private void gameLoop() {
        double lastUpdateTime = System.nanoTime();
        var lastRenderTime = 0;
        var frameCount = 0;
        var lastSecondTime = getLastSecondTime(lastUpdateTime);
        var oldFrameCount = 0;

        while (running) {
            double nowTime = System.nanoTime();
            var updateCount = 0;
            while (nowTime - lastUpdateTime > gameUpdateDelay && updateCount < updateCountBeforeRender) {
                update();
                input();
                lastUpdateTime += gameUpdateDelay;
                updateCount++;
            }

            input();
            render();
            draw();
            lastUpdateTime = nowTime;
            frameCount++;

            var thisSecond = getLastSecondTime(lastUpdateTime);
            if (thisSecond > lastSecondTime) {
                if (frameCount != oldFrameCount) {
                    System.out.println("New second " + thisSecond + ' ' + frameCount);
                    oldFrameCount = frameCount;
                }
                frameCount = 0;
                lastSecondTime = thisSecond;
            }

            while (nowTime - lastRenderTime < targetUpdateDelay && nowTime - lastUpdateTime < gameUpdateDelay) {
                Thread.yield();

                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                    System.out.println("ERROR");
                }
                nowTime = System.nanoTime();
            }
        }
    }

    public void update() {
        gameStateManager.update();

    }

    public void input() {
        gameStateManager.input(mouse, keyboard);
    }

    public void render() {
        if (graphics2D != null) {
            graphics2D.setColor(new Color(255, 70, 23));
            graphics2D.fillRect(0, 0, width, height);
            gameStateManager.render(graphics2D);
        }
    }

    public void draw() {
        Graphics graphics = this.getGraphics();
        graphics.drawImage(image, 0, 0, width, height, null);
        graphics.dispose();

    }
}

