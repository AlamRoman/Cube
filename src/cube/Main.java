package cube;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel{
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setSize(750, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Cube in 2d");
        
        Panel panel = new Panel();
        
        panel.startThread();

        frame.add(panel);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
