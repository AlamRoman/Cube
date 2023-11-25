package cube;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.Random;

import javax.swing.JPanel;

public class Panel extends JPanel implements Runnable{

	static final int SCREEN_WIDTH = 750;
    static final int SCREEN_HEIGHT = 450;
    
    final int POINT_REDIUS = 8;
    
    static final int FPS = 60;
    static final int UPDATE_PER_SECOND = 20;
    
    Random random = new Random();
    
    Thread thread;
    
    cube cube = new cube(SCREEN_WIDTH,SCREEN_HEIGHT);
    
    public Panel() {
    	this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.black);
	}
    
    void update() {
    	cube.projectPoints();
    	
    	cube.drawLines();
    }
    
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	
    	Graphics2D g2d = (Graphics2D) g;
    	
    	//print points
    	g2d.setColor(Color.red);
    	for (int i = 0; i < 8; i++) {
			g2d.fillOval(cube.projected2d[i][0] - POINT_REDIUS/2, cube.projected2d[i][1] - POINT_REDIUS/2, POINT_REDIUS, POINT_REDIUS);
		}
    	
    	//print lines
    	g2d.setColor(Color.white);
    	for (Line2D line : cube.lines) {
			g2d.draw(line);
		}
    	
    }
    
    public void startThread() {
    	thread = new Thread(this);
    	thread.start();
    }

	@Override
	public void run() {
		long lastUpdateTime = System.nanoTime();
		long lastTime = System.nanoTime();
		long currentTime;
		double delay = 1000000000 / (double) FPS;
		double updateDelay = 1000000000 / (double) UPDATE_PER_SECOND;
		
		while(thread != null) {
			currentTime = System.nanoTime();
			
			if (currentTime - lastTime >= delay) {
				if (currentTime - lastUpdateTime >= updateDelay) {
					update();
					lastUpdateTime = currentTime;
				}
				
				repaint();
				
				lastTime = currentTime;
				
			}
			
			try {
	            Thread.sleep(1);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
			
		}
		
	}

}
