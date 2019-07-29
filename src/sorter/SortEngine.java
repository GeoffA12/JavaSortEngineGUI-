package sorter;


import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public interface SortEngine {
	public static final int MAX_SIZE = 450;
	public static final int MAX_VALUE = 350;
	// post: returns the size of this engine's list
	public int getSize();

	//receives: nothing 
	//task: list is sorted in ascending order using mergesort algorithm
	//returns: nothing
	public void mergeSort();

	//receives: nothing 
	//task: list is sorted in ascending order using quicksort algorithm
	//returns: nothing
	public void quickSort();

	//receives: nothing 
	//task: list is sorted in ascending order using insertion sort algorithm
	//returns: nothing
	public void insertionSort();

	//receives: nothing 
		//task: list is sorted in ascending order using selection sort algorithm
		//returns: nothing
	public void selectionSort();

	//receives: nothing
	// task: this sort engine's current list is randomly shuffled 
	// returns: nothing
	public void shuffleList();

	//receives: nothing
	//returns: array of elements that comprise the contents of this sort engine's values 
	public int [] getList();
	//pre: 
	//post: stop flag is set to given flagValue, which controls sorting (turns it off or on for control)
	public void setStopFlag(boolean flagValue);

	// EVENT CHANGES
	// boiler plate standard methods for use with a model - adds change listener instance to this model's listener list
	public void addChangeListener(ChangeListener stackEngineListener);

	// removes change listener from this model's listener list.
	public void removeChangeListener(ChangeListener stackEngineListener);

	// raises or fires off a change event so that any listeners can hear it and thus update their situation (ie the view)
	public void fireChangeEvent(ChangeEvent event);

}
