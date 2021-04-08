package game;

import javax.swing.*;
import java.awt.image.BufferStrategy;

public class Window extends JFrame {
    public static final long serialVersionUID = 1L;

    private BufferStrategy bs;
    private GamePanel gp;

    public Window() {
        setTitle("Lucky Woodcutter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new GamePanel(860, 640));
        //setIgnoreRepaint(true);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
