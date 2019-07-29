package sorter;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/* 1. Declare 3 buttons
 * 2. Constructed 3 buttons and add to buttonPanel
   3. Set ActionListeners for 3 buttons*/ 
public class SortController extends JPanel{
	private JButton startButton;
	private JButton stopButton;
	private JButton resetButton;
	private final ChangeEvent CHANGE_EVENT = new ChangeEvent(this);
	private SortEngine engine;
	private Thread sortThread;
	private JComboBox<String> sortBox;
	private SortView sortView;
	private String[] mySorts = {"Quick sort", "Merge Sort", "Insertion Sort", "Selection Sort"};
	private JPanel buttonPanel;
	private JPanel sortPanel;
	private ChangeListener sortListener;
	
	// One argument constructor for the controller where we pass the sortEngine instance to the controller.
	// The controller acts as a second JPanel and is in charge of the constructing the buttons and panel
	// That will be placed onto the Frame
	public SortController(SortEngine aEngine) {
		super();
		this.setPreferredSize(new Dimension(SortFrame.FRAME_WIDTH - 10, SortFrame.FRAME_HEIGHT - 20));
		// Contruct buttons and button panel
		this.startButton = new JButton("Start");
		this.stopButton = new JButton("Stop");
		this.resetButton= new JButton("Reset");
		//this.buttonPanel = new SortController(new SortEngineImpl());  // create a Stack Panel (view) and send it the engine it is "modeling"
		this.setLayout(new BorderLayout());
		this.sortPanel = new SortView(aEngine);
		this.buttonPanel = new JPanel();
		//Check using flowlayout
		this.buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 15));
		this.buttonPanel.add(startButton);
		this.buttonPanel.add(stopButton);
		this.buttonPanel.add(resetButton);
		this.sortBox = new JComboBox<String>(this.mySorts);
		
		this.buttonPanel.add(sortBox);
		this.add(buttonPanel, BorderLayout.NORTH);
		//this.add(startButton, BorderLayout.NORTH);
		//startButton.addActionListener(this);
		
		// Construct sortBox
		
		// Construct view

		this.sortView = new SortView(aEngine);
		this.add(sortView, BorderLayout.CENTER);
		this.engine = aEngine;
		// Create a changeListener for the sortEngine
		// add that changeListener 
		
		// add that to the sortEngine
		this.sortListener = new ChangeListener()
		{ 
			public void stateChanged(ChangeEvent e){
				update();
			}

		};
		this.engine.addChangeListener(sortListener);
		init();
	} // end of constructor
	
	// repaint all models (engines)
	private void update() 
	{
		try {
			//System.out.println("I'm in the try block.");
			Thread.sleep(10);
		}
		catch (InterruptedException e) {
			System.out.println("Error in sleep.");
		}
		repaint();
	}
	
	// addListeners to the buttons
	public void init() {

		System.out.println("I'm creating and starting a new thread.");
		this.sortThread = new Thread();
		this.sortThread.start();

		System.out.println("Before first while loop.");
		while(this.sortThread != null && this.sortThread.isAlive()) {
			this.engine.setStopFlag(true);
			this.stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				while(sortThread != null && sortThread.isAlive()) {
					engine.setStopFlag(true);
				}
				System.out.println("I just killed the thread using stop.");
			}
		} ); 
			//sortThread.start();
			this.resetButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					while(sortThread != null && sortThread.isAlive()) {
						System.out.println("I'm in reset and i'm reshuffling the list.");
						engine.setStopFlag(true);
					}
					engine.shuffleList();
					System.out.println("I just shuffled the list using reset button.");
				}
			} ); 
			this.startButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					while(sortThread != null && sortThread.isAlive()) {
						engine.setStopFlag(true);
					}
					sortThread = new Thread(new Runnable() {
						public void run() {
							int index = sortBox.getSelectedIndex();
							System.out.println("I'm setting the stop flag to false because i hit start.");
							engine.setStopFlag(false);
							switch(index) {
								case 0:
									System.out.println("I'm running quicksort.");
									engine.quickSort();
									break;
								case 1: 
									System.out.println("I'm running mergesort.");
									engine.mergeSort();
									break;
								case 2:
									System.out.println("I'm running insertionsort");
									engine.insertionSort();
									break;
								case 3:
									System.out.println("I'm running selectionsort");
									engine.selectionSort();
							}
						}
					} );
					sortThread.start();
				}
			} ); 
		}
	}
}