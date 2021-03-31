package visualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JPanel implements ActionListener {
    Image image = new ImageIcon("src/main/java/sources/images/1.png").getImage();
    Timer timer = new Timer(20, this);
    JFrame frame;

    public Window(JFrame frame){
        timer.start();
        this.frame = frame;
    }

    public void paint(Graphics g){
        System.out.println(image);
        g.drawImage(image, 0, 0, frame.getWidth(), frame.getHeight(), null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
