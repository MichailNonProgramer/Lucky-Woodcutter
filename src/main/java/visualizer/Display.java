package visualizer;

import javax.swing.*;

public class Display {

    public  void createDisplay(){
        JFrame frame = new JFrame("Lucky Woodcutter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(480, 640);
        frame.setResizable(false);
//        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(false);
        frame.add(new Window(frame));
        frame.setVisible(true);

    }
}
