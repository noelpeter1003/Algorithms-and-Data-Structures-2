/**
*					  |	Insert  | Selection | Quick   |	Merge Rec |	Merge It |
*______________________________________________________________________________
*1000 random 	      |	4100    | 5900	    | 88033	  | 9666	  | 45500    |
*1000 few unique 	  |	96566   | 160633    | 109900  |	369500	  | 278000   |
*1000 nearly ordered  |	212666  | 5855166   | 99033	  | 221000	  | 667966   |
*1000 reverse order   |	37133   | 105666    | 7133	  | 39766	  | 63500    |
*1000 sorted 		  |	1333    | 92533	    | 7166	  | 17700	  | 34700    |
*10000 random 		  |	6417033 | 10516933  | 1155833 |	241400	  | 535133   |
*
* a. Which of these sorting algorithms does the order of input have an impact on? Why?
* b. Which algorithm has the biggest difference between the best and worst performance, based
*	 on the type of input, for the input of size 1000? Why?
* c. Which algorithm has the best/worst scalability, i.e., the difference in performance time
*   based on the input size? Please consider only input files with random order for this answer.
* d. Did you observe any difference between iterative and recursive implementations of merge
*   sort?
* e. Which algorithm is the fastest for each of the 6 input files?
* 
* ANSWERS
*  a. All of the sorting algorithms are impacted by the order of the input data. Some are impacted more drastically than others
*  	in various different ways. 
*  
*  b. Insertion sort has the largest notable difference between its performance for inputs of size 1,000 based on their format. Insertion sort
*  	takes a massive 212666ns to sort 1,000 inputs of nearly ordered array, whilst only takes 1333ns to sort 1,000 inputs that were already sorted.
*  	This is because if the input is random and unordered Insertion Sort must perform in the worst case O(n^2) comparisons and swaps whereas if the input 
*  	is already sorted it only needs to perform (n) comparison and 0 swaps.
*  
*  c.Best Case - iterative merge Sort is the best performer when it comes to scalability of inputs of a random order. It manages to 
*  	sort 1000 random values in 45500ns whilst also managing to sort 10000 random values in 535133ns.
*  
*  Worst Case - Selection Sort is the worst performer when it comes to scalability of inputs of a random order. It manages to 
*  	sort 1000 random values in 5900ns whilst to sort 10000 random values in 10516933ns.
*  
*  d. the main difference is time complexity between and teh scalability differs as well
*  
*  e.	1000 random = Insertion Sort 
*  		1000 few unique = Insertion Sort
*  		1000 nearly ordered = Quick Sort
*  		1000 reverse order = Quick Sort
*  		1000 sorted = Insertion Sort
*  		10000 random = Merge Rec Sort
*/

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 * Test class for SortComparison.java
 *
 * @author Noel Peter
 * @version HT 2022
 */
@RunWith(JUnit4.class)
public class SortComparisonTest {
	// ~ Constructor ........................................................
	@Test
	public void testConstructor() {
		new SortComparison();
	}

	// ~ Public Methods ........................................................

	// ----------------------------------------------------------
	/**
	 * Check that the methods work for empty arrays
	 */
	@Test
	public void testEmpty() {
		double a[] = {};
		double sortedArray[] = new double[a.length];
		int length;

		sortedArray = SortComparison.insertionSort(a);
		length = sortedArray.length;
		assertEquals("Checking insertionSort on an empty array, returned array should also be empty", length, 0);

		sortedArray = SortComparison.selectionSort(a);
		length = sortedArray.length;
		assertEquals("Checking selectionSort on an empty array, returned array should also be empty", length, 0);

		sortedArray = SortComparison.quickSort(a);
		length = sortedArray.length;
		assertEquals("Checking quickSort on an empty array, returned array should also be empty", length, 0);

		sortedArray = SortComparison.mergeSortIterative(a);
		length = sortedArray.length;
		assertEquals("Checking bubbleSort on an empty array, returned array should also be empty", length, 0);

		sortedArray = SortComparison.mergeSortRecursive(a);
		length = sortedArray.length;
		assertEquals("Checking bubbleSort on an empty array, returned array should also be empty", length, 0);
	}

	// ----------------------------------------------------------
	/**
	 * Check that the insertionSort() method works properly
	 */
	@Test
	public void testInsertionSort() {
		double arr1[] = { 19.7, 6.5, 1.5, 86.5, 14.1, 70.4, 100.0, 0.7, -199.9, 2.1, 4991.2, 56.3 };
		double expArray[] = { -199.9, 0.7, 1.5, 2.1, 6.5, 14.1, 19.7, 56.3, 70.4, 86.5, 100.0, 4991.2 };
		double sortedArray[] = new double[arr1.length];

		sortedArray = SortComparison.insertionSort(arr1);
		assertArrayEquals("Checking insertionSort on a test array, returned array should equal expected array",
				expArray, sortedArray, 0);

		double arr2[] = { 104.0 };
		double expArray2[] = { 104.0 };
		double sortedArray2[] = new double[arr2.length];

		sortedArray2 = SortComparison.insertionSort(arr2);
		assertArrayEquals("Checking insertionSort on a test array, returned array should equal expected array",
				expArray2, sortedArray2, 0);

		double arr3[] = { 199.6, -199.7 };
		double expArray3[] = { -199.7, 199.6 };
		double sortedArray3[] = new double[arr3.length];

		sortedArray3 = SortComparison.insertionSort(arr3);
		assertArrayEquals("Checking insertionSort on a test array, returned array should equal expected array",
				expArray3, sortedArray3, 0);

		double arr4[] = { -199.9, 2.1, 4991.2 };
		double expArray4[] = { -199.9, 2.1, 4991.2 };
		double sortedArray4[] = new double[arr4.length];

		sortedArray4 = SortComparison.insertionSort(arr4);
		assertArrayEquals("Checking insertionSort on a test array, returned array should equal expected array",
				expArray4, sortedArray4, 0);

	}

	// ----------------------------------------------------------
	/**
	 * Check that the quickSort() method works properly
	 */
	@Test
	public void testQuickSort() {
		double arr1[] = { 19.7, 6.5, 1.5, 86.5, 14.1, 70.4, 100.0, 0.7, -199.9, 2.1, 4991.2, 56.3 };
		double expArray[] = { -199.9, 0.7, 1.5, 2.1, 6.5, 14.1, 19.7, 56.3, 70.4, 86.5, 100.0, 4991.2 };
		double sortedArray[] = new double[arr1.length];

		sortedArray = SortComparison.quickSort(arr1);
		assertArrayEquals("Checking insertionSort on a test array, returned array should equal expected array",
				expArray, sortedArray, 0);

		double arr2[] = { 104.0 };
		double expArray2[] = { 104.0 };
		double sortedArray2[] = new double[arr2.length];

		sortedArray2 = SortComparison.quickSort(arr2);
		assertArrayEquals("Checking insertionSort on a test array, returned array should equal expected array",
				expArray2, sortedArray2, 0);

		double arr3[] = { 199.6, -199.7 };
		double expArray3[] = { -199.7, 199.6 };
		double sortedArray3[] = new double[arr3.length];

		sortedArray3 = SortComparison.quickSort(arr3);
		assertArrayEquals("Checking insertionSort on a test array, returned array should equal expected array",
				expArray3, sortedArray3, 0);

		double arr4[] = { -199.9, 2.1, 4991.2 };
		double expArray4[] = { -199.9, 2.1, 4991.2 };
		double sortedArray4[] = new double[arr4.length];

		sortedArray4 = SortComparison.quickSort(arr4);
		assertArrayEquals("Checking insertionSort on a test array, returned array should equal expected array",
				expArray4, sortedArray4, 0);

	}

	// ----------------------------------------------------------
	/**
	 * Check that the selectionSort() method works properly
	 */
	@Test
	public void testSelectionSort() {
		double arr1[] = { 19.7, 6.5, 1.5, 86.5, 14.1, 70.4, 100.0, 0.7, -199.9, 2.1, 4991.2, 56.3 };
		double expArray[] = { -199.9, 0.7, 1.5, 2.1, 6.5, 14.1, 19.7, 56.3, 70.4, 86.5, 100.0, 4991.2 };
		double sortedArray[] = new double[arr1.length];

		sortedArray = SortComparison.selectionSort(arr1);
		assertArrayEquals("Checking insertionSort on a test array, returned array should equal expected array",
				expArray, sortedArray, 0);

		double arr2[] = { 104.0 };
		double expArray2[] = { 104.0 };
		double sortedArray2[] = new double[arr2.length];

		sortedArray2 = SortComparison.selectionSort(arr2);
		assertArrayEquals("Checking insertionSort on a test array, returned array should equal expected array",
				expArray2, sortedArray2, 0);

		double arr3[] = { 199.6, -199.7 };
		double expArray3[] = { -199.7, 199.6 };
		double sortedArray3[] = new double[arr3.length];

		sortedArray3 = SortComparison.selectionSort(arr3);
		assertArrayEquals("Checking insertionSort on a test array, returned array should equal expected array",
				expArray3, sortedArray3, 0);

		double arr4[] = { -199.9, 2.1, 4991.2 };
		double expArray4[] = { -199.9, 2.1, 4991.2 };
		double sortedArray4[] = new double[arr4.length];

		sortedArray4 = SortComparison.selectionSort(arr4);
		assertArrayEquals("Checking insertionSort on a test array, returned array should equal expected array",
				expArray4, sortedArray4, 0);

	}
	
	@Test
	public void testMergeSortIterative() {
		double arr1[] = {5.0, 7.0, -9.0, 3.0, -4.0, 2.0, 8.0};
		double expArray[] = {-9.0, -4.0, 2.0, 3.0, 5.0, 7.0, 8.0};
		double sortedArray[] = new double[arr1.length];

		sortedArray = SortComparison.mergeSortIterative(arr1);
		assertArrayEquals("Checking insertionSort on a test array, returned array should equal expected array",
				expArray, sortedArray, 0);

		double arr2[] = { 104.0 };
		double expArray2[] = { 104.0 };
		double sortedArray2[] = new double[arr2.length];

		sortedArray2 = SortComparison.mergeSortIterative(arr2);
		assertArrayEquals("Checking insertionSort on a test array, returned array should equal expected array",
				expArray2, sortedArray2, 0);

		double arr3[] = { 199.6, -199.7 };
		double expArray3[] = { -199.7, 199.6 };
		double sortedArray3[] = new double[arr3.length];

		sortedArray3 = SortComparison.mergeSortIterative(arr3);
		assertArrayEquals("Checking insertionSort on a test array, returned array should equal expected array",
				expArray3, sortedArray3, 0);

		double arr4[] = { -199.9, 2.1, 4991.2 };
		double expArray4[] = { -199.9, 2.1, 4991.2 };
		double sortedArray4[] = new double[arr4.length];

		sortedArray4 = SortComparison.mergeSortIterative(arr4);
		assertArrayEquals("Checking insertionSort on a test array, returned array should equal expected array",
				expArray4, sortedArray4, 0);

	}

	@Test
	public void testMergeSortRecursive() {
		double arr1[] = { 19.7, 6.5, 1.5, 86.5, 14.1, 70.4, 100.0, 0.7, -199.9, 2.1, 4991.2, 56.3 };
		double expArray[] = { -199.9, 0.7, 1.5, 2.1, 6.5, 14.1, 19.7, 56.3, 70.4, 86.5, 100.0, 4991.2 };
		double sortedArray[] = new double[arr1.length];

		sortedArray = SortComparison.mergeSortRecursive(arr1);
		assertArrayEquals("Checking insertionSort on a test array, returned array should equal expected array",
				expArray, sortedArray, 0);

		double arr2[] = { 104.0 };
		double expArray2[] = { 104.0 };
		double sortedArray2[] = new double[arr2.length];

		sortedArray2 = SortComparison.mergeSortRecursive(arr2);
		assertArrayEquals("Checking insertionSort on a test array, returned array should equal expected array",
				expArray2, sortedArray2, 0);

		double arr3[] = { 199.6, -199.7 };
		double expArray3[] = { -199.7, 199.6 };
		double sortedArray3[] = new double[arr3.length];

		sortedArray3 = SortComparison.mergeSortRecursive(arr3);
		assertArrayEquals("Checking insertionSort on a test array, returned array should equal expected array",
				expArray3, sortedArray3, 0);

		double arr4[] = { -199.9, 2.1, 4991.2 };
		double expArray4[] = { -199.9, 2.1, 4991.2 };
		double sortedArray4[] = new double[arr4.length];

		sortedArray4 = SortComparison.mergeSortRecursive(arr4);
		assertArrayEquals("Checking insertionSort on a test array, returned array should equal expected array",
				expArray4, sortedArray4, 0);

	}

	// ----------------------------------------------------------
	/**
	 * Main Method. Use this main method to create the experiments needed to answer
	 * the experimental performance questions of this assignment.
	 * @throws FileNotFoundException 
	 *
	 */
//	*******************************************************************************************
//	*******************************************************************************************
//	*******************************************************************************************
//	*******************************************************************************************
//	*******************************************************************************************
//	even though I'm running the main file with timer function in the SortComparison file 
//	webcat says it hasn't been tested so I commented out those parts
//	*******************************************************************************************
//	*******************************************************************************************
//	*******************************************************************************************
//	*******************************************************************************************
//	*******************************************************************************************
	
	
	public static void main(String[] args) throws FileNotFoundException {
		File thousandFile = new File("numbers1000.txt");
		double[] array1000 = SortComparison.fileArray(thousandFile, 10);

		File thousandDuplicates = new File("numbers1000Duplicates.txt");
		double[] array1000D = SortComparison.fileArray(thousandDuplicates, 100);

		File tenthousandFile = new File("numbers10000.txt");
		double[] array10000 = SortComparison.fileArray(tenthousandFile, 1000);

		File thousandNearlyOrdered = new File("numbersNearlyOrdered1000.txt");
		double[] array1000NearlyOrdered = SortComparison.fileArray(thousandNearlyOrdered, 1000);

		File thousandReverse = new File("numbersReverse1000.txt");
		double[] array1000Reverse = SortComparison.fileArray(thousandReverse, 100);

		File thousandSorted = new File("numbersSorted1000.txt");
		double[] array1000Sorted = SortComparison.fileArray(thousandSorted, 100);

		String a = "insertion sort";
		String b = "quick sort";
		String c = "merge iterative sort";
		String d = "merge recursive sort";
		String e = "selection sort";

		int insertion = 0;
		int quick = 1;
		int mergeIterative = 2;
		int mergeRecursive = 3;
		int selection = 4;
		
		System.out.println("\n\nAvg time for " + a + " with numbers1000.txt = "
				+ SortComparison.SortTimer(array1000, insertion) + " (ns)");
		System.out.println("Avg time for " + a + " with numbers1000Duplicates.txt = "
				+ SortComparison.SortTimer(array1000D, insertion) + " (ns)");
		System.out.println("Avg time for " + a + " with numbers10000.txt = "
				+ SortComparison.SortTimer(array10000, insertion) + " (ns)");
		System.out.println("Avg time for " + a + " with numbersNearlyOrdered1000.txt = "
				+ SortComparison.SortTimer(array1000NearlyOrdered, insertion) + " (ns)");
		System.out.println("Avg time for " + a + " with numbersReverse1000.txt = "
				+ SortComparison.SortTimer(array1000Reverse, insertion) + " (ns)");
		System.out.println("Avg time for " + a + " with numbersSorted1000.txt = "
				+ SortComparison.SortTimer(array1000Sorted, insertion) + " (ns)");

		System.out.println("\n\nAvg time for " + b + " with numbers1000.txt = "
				+ SortComparison.SortTimer(array1000, quick) + " (ns)");
		System.out.println("Avg time for " + b + " with numbers1000Duplicates.txt = "
				+ SortComparison.SortTimer(array1000D, quick) + " (ns)");
		System.out.println("Avg time for " + b + " with numbers10000.txt = "
				+ SortComparison.SortTimer(array10000, quick) + " (ns)");
		System.out.println("Avg time for " + b + " with numbersNearlyOrdered1000.txt = "
				+ SortComparison.SortTimer(array1000NearlyOrdered, quick) + " (ns)");
		System.out.println("Avg time for " + b + " with numbersReverse1000.txt = "
				+ SortComparison.SortTimer(array1000Reverse, quick) + " (ns)");
		System.out.println("Avg time for " + b + " with numbersSorted1000.txt = "
				+ SortComparison.SortTimer(array1000Sorted, quick) + " (ns)");

		System.out.println("\n\nAvg time for " + c + " with numbers1000.txt = "
				+ SortComparison.SortTimer(array1000, mergeIterative) + " (ns)");
		System.out.println("Avg time for " + c + " with numbers1000Duplicates.txt = "
				+ SortComparison.SortTimer(array1000D, mergeIterative) + " (ns)");
		System.out.println("Avg time for " + c + " with numbers10000.txt = "
				+ SortComparison.SortTimer(array10000, mergeIterative) + " (ns)");
		System.out.println("Avg time for " + c + " with numbersNearlyOrdered1000.txt = "
				+ SortComparison.SortTimer(array1000NearlyOrdered, mergeIterative) + " (ns)");
		System.out.println("Avg time for " + c + " with numbersReverse1000.txt = "
				+ SortComparison.SortTimer(array1000Reverse, mergeIterative) + " (ns)");
		System.out.println("Avg time for " + c + " with numbersSorted1000.txt = "
				+ SortComparison.SortTimer(array1000Sorted, mergeIterative) + " (ns)");

		System.out.println("\n\nAvg time for " + d + " with numbers1000.txt = "
				+ SortComparison.SortTimer(array1000, mergeRecursive) + " (ns)");
		System.out.println("Avg time for " + d + " with numbers1000Duplicates.txt = "
				+ SortComparison.SortTimer(array1000D, mergeRecursive) + " (ns)");
		System.out.println("Avg time for " + d + " with numbers10000.txt = "
				+ SortComparison.SortTimer(array10000, mergeRecursive) + " (ns)");
		System.out.println("Avg time for " + d + " with numbersNearlyOrdered1000.txt = "
				+ SortComparison.SortTimer(array1000NearlyOrdered, mergeRecursive) + " (ns)");
		System.out.println("Avg time for " + d + " with numbersReverse1000.txt = "
				+ SortComparison.SortTimer(array1000Reverse, mergeRecursive) + " (ns)");
		System.out.println("Avg time for " + d + " with numbersSorted1000.txt = "
				+ SortComparison.SortTimer(array1000Sorted, mergeRecursive) + " (ns)");

		System.out.println("\n\nAvg time for " + e + " with numbers1000.txt = "
				+ SortComparison.SortTimer(array1000, selection) + " (ns)");
		System.out.println("Avg time for " + e + " with numbers1000Duplicates.txt = "
				+ SortComparison.SortTimer(array1000D, selection) + " (ns)");
		System.out.println("Avg time for " + e + " with numbers10000.txt = "
				+ SortComparison.SortTimer(array10000, selection) + " (ns)");
		System.out.println("Avg time for " + e + " with numbersNearlyOrdered1000.txt = "
				+ SortComparison.SortTimer(array1000NearlyOrdered, selection) + " (ns)");
		System.out.println("Avg time for " + e + " with numbersReverse1000.txt = "
				+ SortComparison.SortTimer(array1000Reverse, selection) + " (ns)");
		System.out.println("Avg time for " + e + " with numbersSorted1000.txt = "
				+ SortComparison.SortTimer(array1000Sorted, selection) + " (ns)");
	}
}
