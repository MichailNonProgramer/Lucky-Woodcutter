package visualizer;

import map.Cell;
import map.GameMap;
import states.StateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Window extends JPanel implements Runnable {
    JFrame frame;
    private boolean running;
    private final int width;
    private final int height;
    private BufferedImage image;
    private Graphics2D graphics2D;
    //    private MouseHandler mouse;
//    private KeyboardHandler keyboard;
    private StateManager stateManager;
    private static Thread thread;


    final int billion = 1000000000;
    final double GAME_FPS = 60;
    final double gameUpdateDelay = billion / GAME_FPS;
    final int updateCountBeforeRender = 3;
    final double TARGET_FPS = 1000;
    final double targetUpdateDelay = billion / TARGET_FPS;


    public Window(int width, int height){
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
    }

    @Override
    public void run() {
        init();
        long lastUpdateTime = System.currentTimeMillis();
        long delta;
        var frameCount = 0;
        var lastSecondTime = getLastSecondTime(lastUpdateTime);
        var oldFrameCount = 0;
        var lastRenderTime = 0;
        while (running) {
            var nowTime = System.nanoTime();
            var updateCount = 0;
            delta = System.currentTimeMillis() - lastUpdateTime;
            lastUpdateTime = System.currentTimeMillis();
            update(delta);
            render();
            nowTime = System.currentTimeMillis();
            while (nowTime - lastUpdateTime > gameUpdateDelay && updateCount < updateCountBeforeRender) {
                update(delta);
                input();
                lastUpdateTime += gameUpdateDelay;
                updateCount++;
            }

            input();
            render();
            draw();
            lastUpdateTime = nowTime;
            frameCount++;

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

    private int getLastSecondTime(double lastUpdateTime) {
        return (int) (lastUpdateTime / billion);
    }


    private void input(){}

    private void draw(){
        Graphics graphics = this.getGraphics();
        //graphics.drawImage(image, 0, 0, 200, 200, null);
        for (var cell: GameMap.spawnEmptyMap()){
            var nx = cell.getX() * Cell.cellSize;
            var ny = cell.getY() * Cell.cellSize;
            graphics.drawImage(image, nx , ny , Cell.cellSize - 1, Cell.cellSize - 1, null);
        }
        graphics.dispose();
    }

    public void init(){
        running = true;
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        graphics2D = (Graphics2D) image.getGraphics();
//        mouse = new MouseHandler(this);
//        keyboard = new KeyboardHandler(this);
        stateManager = new StateManager();
    }

    public void addNotify() {
        super.addNotify();

        if (thread == null) {
            thread = new Thread(this, "GameThread");
            thread.start();
        }
    }


    public void render(){
        if (graphics2D != null) {
            graphics2D.setColor(new Color(255, 23, 58));
            graphics2D.fillRect(0, 0, width, height);
            stateManager.render(graphics2D);
        }
    }

    public void update(long delta){

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
