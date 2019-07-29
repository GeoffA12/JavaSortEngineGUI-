package sorter;

import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

//main program that runs the Sort Gui testing
//uses the Model-View-Controller paradigm
//the controller is SortController 
//the models are SortEngine instances
//the view is part of the controller in SortView class

public class SortFrame {

	public static final int FRAME_WIDTH=1000;
	public static final int FRAME_HEIGHT=650;
	// 
	public static void main(String[]args)
	{
		SortEngine sortEngine = new SortEngineImpl();
		
		SortController myController = new SortController(sortEngine);
		JFrame frame = new JFrame("Sort Engine GUI Asg 8-- GEOFF");
		frame.setLayout(new FlowLayout());
		frame.addWindowListener( new WindowAdapter() {
		public void windowClosing(WindowEvent windowEvent)
		{
			System.exit(0);
		}
	});
		
	frame.getContentPane().add(myController);
	frame.setSize(SortFrame.FRAME_WIDTH,SortFrame.FRAME_HEIGHT);
	frame.setVisible(true);
   }

}
