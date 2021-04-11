package visualizer;

import javax.swing.*;
import java.awt.image.BufferStrategy;

public class Display  extends JFrame{
    private BufferStrategy bufferStrategy;
    private Window window;

    public Display(int width, int height){
        setTitle("Lucky Woodcutter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new Window(width, height));
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
