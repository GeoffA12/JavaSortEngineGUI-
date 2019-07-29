package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sorter.SortEngine;
import sorter.SortEngineImpl;

class SortEngineTest {

	String sName="";
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		String sName="";
		sName=utils.MyUtils.getNameFromStudent();
		System.out.println("********BEGIN TESTING FOR " + sName + " ***********");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {

		System.out.println("********END OF TESTING    ***********");
	}

	@Test
	public void testConstructors()
	{
		System.out.println("*****Testing constructors() and toString() *****");
		int size = 10;
		SortEngine engine1 = new SortEngineImpl(size);
		SortEngine engine2 = new SortEngineImpl(SortEngine.MAX_SIZE+2);
		SortEngine engine3 = new SortEngineImpl(SortEngine.MAX_SIZE);
		SortEngine engine4 = new SortEngineImpl(-50);

		int list1 [] = engine1.getList();
		assertEquals(size, list1.length);
		int list2 [] = engine2.getList();
		assertEquals(SortEngine.MAX_SIZE, list2.length);

		int list3 [] = engine3.getList();
		assertEquals(SortEngine.MAX_SIZE, list3.length);

		int list4 [] = engine4.getList();
		assertEquals(SortEngine.MAX_SIZE, list4.length);

		assertEquals(size, engine1.getSize());
		assertEquals(SortEngine.MAX_SIZE, engine2.getSize());

		String retString = engine1.toString();
		assertEquals(engine1.getSize(), utils.MyUtils.numberLines(retString));
		retString = engine2.toString();
		assertEquals(engine2.getSize(), utils.MyUtils.numberLines(retString));
		retString = engine3.toString();
		assertEquals(engine3.getSize(), utils.MyUtils.numberLines(retString));
		retString = engine3.toString();
		assertEquals(engine4.getSize(), utils.MyUtils.numberLines(retString));

		retString = engine1.toString();
		list1 = engine1.getList();
		Scanner scanner = new Scanner(retString);
		System.out.println("Current engine: ");
		for(int i=0; i<list1.length; i++)
		{
			int value1 = scanner.nextInt();
			int value2 = list1[i];
			assertEquals(value2, value1);
			System.out.print(value1 + " ");
		}
		System.out.println("");		
		System.out.println("****** End of testing constructors (); *******");		
	}

	@Test 
	public void testShuffle()
	{
		System.out.println("***** Testing shuffleList () *****");
		SortEngine engine = new SortEngineImpl(5);
		engine.shuffleList();
		engine.insertionSort();
		int [] list = engine.getList();
		int size = list.length;
		int [] copyList = new int [size];
		int matchCount = 0;
		for(int i=0; i<list.length; i++)
		{
			copyList[i] = list[i];
		}
		engine.shuffleList();
		list = engine.getList();
		for(int i=0; i< list.length; i++)
		{
			if(copyList[i] == list[i])
			{
				matchCount++;
			}
		}
		assertTrue(matchCount<list.length);
		System.out.println("After shuffle of 5 numbers, match count = " + matchCount);
		engine = new SortEngineImpl(1000);
		engine.shuffleList();
		engine.quickSort();
		list = engine.getList();
		copyList = new int[engine.getSize()];
		for(int i=0; i<list.length; i++)
		{
			copyList[i] = list[i];
		}
		engine.shuffleList();
		matchCount = 0;
		for(int i=0; i< list.length; i++)
		{
			if(copyList[i] == list[i])
			{
				matchCount++;
			}
		}
		assertTrue(matchCount<list.length/3);
		System.out.println("After shuffle of 1000 numbers, match count = " + matchCount);
		System.out.println("***** End of Testing shuffleList () *****");
	}
	@Test
	public void testGetters()
	{
		System.out.println("*****Begin test for getters*****");
		int someSize=12;
		SortEngine engine = new SortEngineImpl(someSize);

		int [] list = engine.getList();
		int [] list2 = engine.getList();

		int size = list.length;
		assertEquals(size, engine.getSize());
		size = list2.length;
		assertEquals(size, engine.getSize());

		for(int i=0; i<list.length; i++)
		{
			assertEquals(list[i], list2[i]);
		}
		engine.shuffleList();
		engine.insertionSort();
		list = engine.getList();
		list2 = engine.getList();
		for(int i=0; i<list.length; i++)
		{
			assertEquals(list[i], list2[i]);
			System.out.println("Matching: " + list[i] + " == " + list2[i]);
		}
		SortEngine engine2 = new SortEngineImpl();
		list = engine2.getList();
		list2 = engine2.getList();
		assertEquals(SortEngine.MAX_SIZE, engine2.getSize());
		for(int i=0; i<list.length; i++)
		{
			assertEquals(list[i], list2[i]);
		}
		System.out.println("*****End testing of getters*****");
	}
	@Test
	public void testSorts()
	{
		System.out.println("*****Begin test for all sorts*****");
		SortEngine engine = new SortEngineImpl(5);
		engine.shuffleList();
		int [] list = engine.getList();
		System.out.println("current list shuffled:                        " + Arrays.toString(list));
		engine.insertionSort();
		list = engine.getList();
		System.out.println("current list Now Sorted with insertion sort:  " + Arrays.toString(list));
		for(int i=0; i< list.length-1; i++)
		{
			assertTrue(list[i] <= list[i+1]);
		}
		engine.insertionSort();
		// sort the sorted list, make sure its still sorted...
		for(int i=0; i< list.length-1; i++)
		{
			assertTrue(list[i] <= list[i+1]);
		}

		engine.shuffleList();
		System.out.println("current list shuffled:                       " + Arrays.toString(list));
		engine.mergeSort();
		list = engine.getList();
		System.out.println("current list Now Sorted with merge sort:     " + Arrays.toString(list));
		for(int i=0; i< list.length-1; i++)
		{
			assertTrue(list[i] <= list[i+1]);
		}
		engine.mergeSort();
		list = engine.getList();
		for(int i=0; i< list.length-1; i++)
		{
			assertTrue(list[i] <= list[i+1]);
		}
		engine.shuffleList();
		System.out.println("current list shuffled:                       " + Arrays.toString(list));
		engine.quickSort();
		System.out.println("current list Now Sorted with quick sort:     " + Arrays.toString(list));
		for(int i=0; i< list.length-1; i++)
		{
			assertTrue(list[i] <= list[i+1]);
		}
		engine.shuffleList();
		engine.quickSort();
		list = engine.getList();
		for(int i=0; i< list.length-1; i++)
		{
			assertTrue(list[i] <= list[i+1]);
		}
		engine.shuffleList();
		System.out.println("current list shuffled:                         " + Arrays.toString(list));
		engine.selectionSort();
		System.out.println("current list Now Sorted with selection sort:   " + Arrays.toString(list));
		for(int i=0; i< list.length-1; i++)
		{
			assertTrue(list[i] <= list[i+1]);
		}
		engine.shuffleList();

		engine = new SortEngineImpl(2);
		list  = engine.getList();
		engine.shuffleList();
		System.out.println("current list shuffled:                        " + Arrays.toString(list));
		engine.insertionSort();
		System.out.println("current list Now Sorted with insertion sort:  " + Arrays.toString(list));
		for(int i=0; i< list.length-1; i++)
		{
			assertTrue(list[i] <= list[i+1]);
		}

		engine.shuffleList();
		System.out.println("current list shuffled:                       " + Arrays.toString(list));
		engine.mergeSort();
		System.out.println("current list Now Sorted with merge sort:     " + Arrays.toString(list));
		for(int i=0; i< list.length-1; i++)
		{
			assertTrue(list[i] <= list[i+1]);
		}

		engine.shuffleList();
		System.out.println("current list shuffled:                       " + Arrays.toString(list));
		engine.quickSort();
		System.out.println("current list Now Sorted with quick sort:     " + Arrays.toString(list));
		for(int i=0; i< list.length-1; i++)
		{
			assertTrue(list[i] <= list[i+1]);
		}

		engine.shuffleList();
		System.out.println("current list shuffled:                       " + Arrays.toString(list));
		engine.selectionSort();
		System.out.println("current list Now Sorted with selection sort: " + Arrays.toString(list));
		for(int i=0; i< list.length-1; i++)
		{
			assertTrue(list[i] <= list[i+1]);
		}
		engine.shuffleList();
		engine = new SortEngineImpl(10);
		list  = engine.getList();
		engine.shuffleList();
		System.out.println("current list shuffled:                       " + Arrays.toString(list));
		engine.insertionSort();
		System.out.println("current list Now Sorted with insertion sort: " + Arrays.toString(list));
		for(int i=0; i< list.length-1; i++)
		{
			assertTrue(list[i] <= list[i+1]);
		}

		engine.shuffleList();
		System.out.println("current list shuffled:                       " + Arrays.toString(list));
		engine.mergeSort();
		System.out.println("current list Now Sorted with merge sort:     " + Arrays.toString(list));
		for(int i=0; i< list.length-1; i++)
		{
			assertTrue(list[i] <= list[i+1]);
		}

		engine.quickSort();
		System.out.println("current list Now Sorted with quick sort:     " + Arrays.toString(list));
		engine.shuffleList();
		System.out.println("current list shuffled:                       " + Arrays.toString(list));
		engine.quickSort();
		System.out.println("current list Now Sorted with quick sort:     " + Arrays.toString(list));
		for(int i=0; i< list.length-1; i++)
		{
			assertTrue(list[i] <= list[i+1]);
		}

		engine.shuffleList();
		System.out.println("current list shuffled:                       " + Arrays.toString(list));
		engine.selectionSort();
		System.out.println("current list Now Sorted with selection sort: " + Arrays.toString(list));
		for(int i=0; i< list.length-1; i++)
		{
			assertTrue(list[i] <= list[i+1]);
		}

		engine = new SortEngineImpl(1);
		engine.shuffleList();
		list = engine.getList();
		System.out.println("current list shuffled:                        " + Arrays.toString(list));
		engine.insertionSort();
		list = engine.getList();
		System.out.println("current list Now Sorted with insertion sort:  " + Arrays.toString(list));
		for(int i=0; i< list.length-1; i++)
		{
			assertTrue(list[i] <= list[i+1]);
		}
		engine.selectionSort();
		list = engine.getList();
		System.out.println("current list Now Sorted with selection sort:  " + Arrays.toString(list));
		for(int i=0; i< list.length-1; i++)
		{
			assertTrue(list[i] <= list[i+1]);
		}
		engine.shuffleList();
		engine.mergeSort();
		list = engine.getList();
		System.out.println("current list Now Sorted with merge sort:  " + Arrays.toString(list));
		for(int i=0; i< list.length-1; i++)
		{
			assertTrue(list[i] <= list[i+1]);
		}
		engine.shuffleList();
		engine.quickSort();
		list = engine.getList();
		System.out.println("current list Now Sorted with quicksort:  " + Arrays.toString(list));
		for(int i=0; i< list.length-1; i++)
		{
			assertTrue(list[i] <= list[i+1]);
		}
		System.out.println("*****End test for all sorts*****");
	}

}

