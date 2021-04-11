package visualizer;

import javax.swing.*;
import java.awt.image.BufferStrategy;

public class Display  extends JFrame{
    private BufferStrategy bufferStrategy;
    private Window window;

    public Display(int wight, int height){
        setTitle("Lucky Woodcutter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new Window(wight, height));
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }


//    public  void createDisplay(){
//        JFrame frame = new JFrame("Lucky Woodcutter");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(480, 640);
//        frame.setResizable(false);
//        frame.setUndecorated(false);
//        frame.add(new Window(frame));
//        frame.setVisible(true);
//
//    }
}
