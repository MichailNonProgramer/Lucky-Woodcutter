package visualizer;

import game.Game;
import map.GameMap;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Display  extends JFrame {
    private static BufferStrategy bufferStrategy;
    private GameWindow window;

    public Display(int width, int height, Game game) {
        setTitle("Lucky Woodcutter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new GameWindow(width, height, game));
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
//        createBufferStrategy(2);
//        bufferStrategy = this.getBufferStrategy();
    }

    public BufferStrategy getBufferStrategy() {
        return bufferStrategy;
    }

    @Override
    public void paint (Graphics g) {}
}

