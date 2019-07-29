package sorter;

import java.util.Collections;
import java.util.EventListener;
import java.util.Random;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

public class SortEngineImpl implements SortEngine{
	private int maxSize;
	private int maxValue;
	private int myArray[];
	private boolean stopFlag;
	private EventListenerList eventListenerList;
	private final ChangeEvent CHANGE_EVENT = new ChangeEvent(this);
	public final Random generator = new Random();
	//SortEngineImpl has a no argument constructor that uses MAX_SIZE as its size, and MAX_VALUE as the upper 
	//limit of values in the engine's list of values (values are 1 to MAX_VALUE) randomly generated
	public SortEngineImpl() {
		this.maxSize = SortEngine.MAX_SIZE;
		this.maxValue = SortEngine.MAX_VALUE;
		this.myArray = new int[this.maxSize];
		for (int x = 0; x < this.maxSize; ++x) {
			this.myArray[x] =  generator.nextInt(this.maxSize) + 1;
		}
		this.eventListenerList = new EventListenerList();
		this.stopFlag = false;
	}
	// SortEngineImpl has a 1 argument constructor that receives the size to make the engine, has to 
	// be greater or equal to 1 and less than or equal to MAX_SIZE.
	public SortEngineImpl(int resize) {
		if (resize < 1 || resize > SortEngine.MAX_SIZE) {
			resize = SortEngine.MAX_SIZE;
		}
		this.maxSize = resize;
		this.maxValue = SortEngine.MAX_VALUE;
		this.myArray = new int[this.maxSize];
		for (int x = 0; x < this.maxSize; ++x) {
			this.myArray[x] = generator.nextInt(this.maxSize) + 1;
		}
		this.eventListenerList = new EventListenerList();
		this.stopFlag = false;
	}
	// post: returns the size of this engine's list
	public int getSize() {
		return this.myArray.length;
	}

	//receives: nothing 
	//task: list is sorted in ascending order using mergesort algorithm
	//returns: nothing
	public void mergeSort() {
		myMergeSort(this.myArray, 0, this.maxSize - 1);
		this.fireChangeEvent(CHANGE_EVENT);
	}
	
	// merge Algorithm
	private void merge(int arr[], int l, int m, int r)  { 
	        // Find sizes of two subarrays to be merged 
	        int n1 = m - l + 1; 
	        int n2 = r - m; 
	  
	        /* Create temp arrays */
	        int L[] = new int [n1]; 
	        int R[] = new int [n2]; 
	  
	        /*Copy data to temp arrays*/
	        for (int i=0; i<n1; ++i) 
	            L[i] = arr[l + i]; 
	        //this.fireChangeEvent(CHANGE_EVENT);
	        for (int j=0; j<n2; ++j) 
	            R[j] = arr[m + 1+ j]; 
	       // this.fireChangeEvent(CHANGE_EVENT);
	        	
	        /* Merge the temp arrays */
	  
	        // Initial indexes of first and second subarrays 
	        int i = 0, j = 0; 
	  
	        // Initial index of merged subarry array 
	        int k = l; 
	        while (i < n1 && j < n2 && this.stopFlag == false) 
	        { 
	            if (L[i] <= R[j]) 
	            { 
	                arr[k] = L[i]; 
	                i++; 
	            } 
	            else
	            { 
	                arr[k] = R[j]; 
	                j++; 
	                
	            } 
	            k++; 
	        } 
	        this.fireChangeEvent(CHANGE_EVENT);
	        /* Copy remaining elements of L[] if any */
	        while (i < n1 && this.stopFlag == false) 
	        { 
	            arr[k] = L[i]; 
	            i++; 
	            k++; 
	        } 
	  
	        /* Copy remaining elements of R[] if any */
	        while (j < n2 && this.stopFlag == false) 
	        { 
	            arr[k] = R[j]; 
	            j++; 
	            k++; 
	        } 
	        this.fireChangeEvent(CHANGE_EVENT);
	    } 
	  
	  // Main function that sorts arr[l..r] using 
	  // merge() 
	public void myMergeSort(int arr[], int l, int r) 
	    { 
	        if (l < r) 
	        { 
	            // Find the middle point 
	            int m = (l+r)/2; 
	  
	            // Sort first and second halves 
	            if (this.stopFlag == false) {
	            	myMergeSort(arr, l, m); 
	            	this.fireChangeEvent(CHANGE_EVENT);
	            }
	            if (this.stopFlag == false) {
	            	myMergeSort(arr , m+1, r); 
	            	this.fireChangeEvent(CHANGE_EVENT);
	            }
	            	// Merge the sorted halves 
	            if (this.stopFlag == false) {
	            	merge(arr, l, m, r); 
	            	this.fireChangeEvent(CHANGE_EVENT);
	            }
	       } 
	  } 
	
		//receives: nothing 
		//task: list is sorted in ascending order using quicksort algorithm
		//returns: nothing
	public void quickSort() {
		myQuickSort(this.myArray, 0, this.maxSize - 1);
	}
	
	//Partition method for the quickSort routine. 
	private int partition(int [] list, int lo, int hi)
	{
		//if (this.stopFlag == false) {
			int  pivotvalue;  // type of element being sorted
			pivotvalue = list[lo];
			if (this.stopFlag == false) {
				while(this.stopFlag == false && lo < hi)
				{
					if (stopFlag == false) {
						while (this.stopFlag == false && lo < hi && pivotvalue < list[hi]) {
							hi--;
						}
					}
					if (this.stopFlag == false) {
						if (lo != hi)
						{
							list[lo]=list[hi];
							this.fireChangeEvent(CHANGE_EVENT);
							lo++;
						}
					}
					if (this.stopFlag == false) {
						while (this.stopFlag == false && lo < hi && pivotvalue > list[lo]) {
							lo++;
						}
					}
					if (this.stopFlag == false) {
						if (lo !=hi)
						{
							list[hi]= list[lo];
							this.fireChangeEvent(CHANGE_EVENT);
							hi--;
						}
					}
				}
			}
			list[hi]=pivotvalue;
			pivotvalue=hi;  // returns this as pivotlocation 
			this.fireChangeEvent(CHANGE_EVENT);
			return pivotvalue;
		//}
	}
	  
	  
	    /* The function that implements QuickSort() 
	      arr[] --> Array to be sorted, 
	      low  --> Starting index, 
	      high  --> Ending index */
	private void myQuickSort(int[] list, int low, int high) 
	{
		int pivotlocation;
		if (this.stopFlag == false)
		{
			if (low < high)  //  is a list of more than 1 to sort?
			{
				pivotlocation = partition(list, low,high);
				
				myQuickSort(list, low, pivotlocation-1);
				
				myQuickSort(list, pivotlocation+1, high);
				//this.fireChangeEvent(CHANGE_EVENT);
			}
		}
	}

	//receives: nothing 
	//task: list is sorted in ascending order using insertion sort algorithm
	//returns: nothing
	public void insertionSort() {
		
		myInsertionSort(this.myArray, this.myArray.length);
		this.fireChangeEvent(CHANGE_EVENT);
	}
	
	// Insertion sort algorithm
	public void myInsertionSort(int[] list, int numElements) {
		if (this.stopFlag == false) {
			for (int index = 0; index < numElements; index++)
			{
				int k = index;
				while (k > 0)
				{
					if (list[k] < list[k-1] && this.stopFlag == false)
					{
						int temp = list[k];
						list[k] = list[k - 1];
						list[k-1] = temp;
						k--;
						this.fireChangeEvent(CHANGE_EVENT);
					}
					else
					{
						k = 0;
					}	
				}
			}
		}
	}

	//receives: nothing 
	//task: list is sorted in ascending order using selection sort algorithm
	//returns: nothing
	public void selectionSort() {
		mySelectionSort(this.myArray);
		this.fireChangeEvent(CHANGE_EVENT);
	}
	
	//Selection sort Algorithm 
	public void mySelectionSort(int arr[]) 
    { 
        int n = arr.length; 
        while (this.stopFlag == false) {
        	for (int i = 0; i < n-1; i++) 
            { 
                // Find the minimum element in unsorted array 
                int min_idx = i; 
                for (int j = i+1; j < n; j++) 
                    if (arr[j] < arr[min_idx] && this.stopFlag == false) 
                        min_idx = j; 
      
                // Swap the found minimum element with the first 
                // element 
                int temp = arr[min_idx]; 
                arr[min_idx] = arr[i]; 
                arr[i] = temp; 
                this.fireChangeEvent(CHANGE_EVENT);
            } 
        	break;
        }
 
 
    } 

	//receives: nothing
	// task: this sort engine's current list is randomly shuffled 
	// returns: nothing
	public void shuffleList() {
		for (int x = 0; x < 2; ++x) {
			for (int y = 0; y < this.maxSize; y++) {
				int swap1 = generator.nextInt(this.maxSize);
				int swap2 = generator.nextInt(this.maxSize);
				int temp = this.myArray[swap1];
				this.myArray[swap1] = this.myArray[swap2];
				this.myArray[swap2] = temp;
			}
		}
		this.fireChangeEvent(CHANGE_EVENT);
	}

	//receives: nothing
	//returns: array of elements that comprise the contents of this sort engine's values 
	public int [] getList() {
		return this.myArray;
	}
	//pre: 
	//post: stop flag is set to given flagValue, which controls sorting (turns it off or on for control)
	public void setStopFlag(boolean flagValue) {
		this.stopFlag = flagValue;
		//this.fireChangeEvent(CHANGE_EVENT);
	}

	// EVENT CHANGES
	// boiler plate standard methods for use with a model - adds change listener instance to this model's listener list
	public void addChangeListener(ChangeListener changeListener)
	{
		eventListenerList.add(ChangeListener.class, changeListener);
	}
	//standard boilerplate method for change events
	public void removeChangeListener(ChangeListener changeListener)
	{
		eventListenerList.remove(ChangeListener.class, changeListener);
	}
	
	// Override the toString method for the SortEngineImpl class. 
	public String toString() {
		String retValue = "";
		for (int x = 0; x < this.maxSize; x++) {
			retValue += this.myArray[x];
			retValue += "\n";
		}
		return retValue;
	}
	//standard boilerplate method for change events
	// used when we need to notify the view that a change to the model has taken place
	// so that the view can update itself.
	public void fireChangeEvent(ChangeEvent changeEvent) {
		// Guaranteed to return a non-null array
		Object[] listeners = eventListenerList.getListenerList();
		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length-2; i>=0; i-=2) {
			if (listeners[i]==ChangeListener.class) {
				((ChangeListener)listeners[i+1]).stateChanged(changeEvent);
			}
		}
	}

}
	

