package sorter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class SortView extends JPanel{
	private int[] list;
	private SortEngine engine;
	public static int MYWIDTH = SortFrame.FRAME_WIDTH - 40;
	public static int MYHEIGHT = SortFrame.FRAME_HEIGHT - 100;
	public JPanel aPanel;
	// This one argument constructor will create a SortView instance (Panel) which will act as a JPanel 
	// We will use this panel to draw lines of the values in the array from the engine that is passed in.
	public SortView(SortEngine aEngine) {
		super();
		this.setBackground(Color.BLUE);
		this.engine = aEngine;
		this.list = this.engine.getList();
		//this.aPanel = new SortController(new SortEngineImpl());
		this.setPreferredSize(new Dimension(MYWIDTH, MYHEIGHT));
		this.repaint();
	}
	
	// Override the paintComponent method for the SortView class. We are drawing every line from the sortEngnine's list 
	// and displaying it on the screen using coordinates. 
	public void paintComponent(Graphics g) {
		this.list = this.engine.getList();
		g.setColor(Color.MAGENTA);
		for (int i = 0; i < this.list.length; ++i) {
			int value = this.list[i];
			int x1 = i + 1;
			int x2 = i + 1;
			int y1 = MYHEIGHT;
			int y2 = MYHEIGHT - value;
			g.drawLine(x1, y1, x2, y2);
		}
	}
}
