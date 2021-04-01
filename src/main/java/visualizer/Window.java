package visualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JPanel implements ActionListener {
    Image image = new ImageIcon("").getImage();
    Timer timer = new Timer(100, this);
    JFrame frame;
    private int count;
    public Window(JFrame frame){
        timer.start();
        count = 0;
        this.frame = frame;
    }

    public void paint(Graphics g){
        g.clearRect(0 , 0, frame.getWidth(), frame.getHeight());
        g.drawImage(image, 0, 0, frame.getWidth(), frame.getHeight(), null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (count) {
            case 0:
                image = new ImageIcon("src/main/java/sources/images/2.png").getImage();
                break;
            case 10:
                image = new ImageIcon("src/main/java/sources/images/1.png").getImage();
                break;
            case 20:
                image = new ImageIcon("src/main/java/sources/images/3.png").getImage();
                break;
            case 30:
                image = new ImageIcon("src/main/java/sources/images/4.png").getImage();
                break;
            default:
                break;
        }
        count++;
        if (count == 40) {
            count = 0;
        }
        repaint();
    }
}
