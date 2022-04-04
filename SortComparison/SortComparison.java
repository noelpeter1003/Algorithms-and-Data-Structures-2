import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

// -------------------------------------------------------------------------
/**
 * This class contains static methods that implementing sorting of an array of
 * numbers using different sort algorithms.
 *
 * @author Noel Peter
 * @version HT 2022
 */

class SortComparison {

	static void swap(double[] arr, int i, int j) {
		double temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	/**
	 * Sorts an array of doubles using InsertionSort. This method is static, thus it
	 * can be called as SortComparison.sort(a)
	 * 
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order.
	 *
	 */
	static double[] insertionSort(double a[]) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			double temp = a[i];
			int j = i - 1;
			while (j >= 0 && a[j] > temp) {
				a[j + 1] = a[j];
				j = j - 1;
			}
			a[j + 1] = temp;
		}
		return a;
	}// end insertionsort

	/**
	 * Sorts an array of doubles using Selection Sort. This method is static, thus
	 * it can be called as SortComparison.sort(a)
	 * 
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	static double[] selectionSort(double a[]) {
		int n = a.length;
		for (int i = 0; i < n - 1; i++) {
			int min = i;
			for (int j = i + 1; j < n; j++) {
				if (a[j] < a[min])
					min = j;
			}
			swap(a, min, i);
		}
		return a;
	}// end selectionsort

	/**
	 * Sorts an array of doubles using Quick Sort. This method is static, thus it
	 * can be called as SortComparison.sort(a)
	 * 
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	static double[] quickSort(double a[]) {
		qSort(a, 0, a.length - 1);
		return a;
	}// end quicksort

	private static void qSort(double a[], int low, int high) {
		if (low >= high)
			return;
		int middle = low + (high - low) / 2;
		double pivot = a[middle];
		int i = low;
		int j = high;
		while (i <= j) {
			while (a[i] < pivot)
				i++;
			while (a[j] > pivot)
				j--;
			if (i <= j)
				swap(a, i++, j--);
		}
		if (low < j)
			qSort(a, low, j);
		if (high > i)
			qSort(a, i, high);
	}

	/**
	 * Sorts an array of doubles using Merge Sort. This method is static, thus it
	 * can be called as SortComparison.sort(a)
	 * 
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	/**
	 * Sorts an array of doubles using iterative implementation of Merge Sort. This
	 * method is static, thus it can be called as SortComparison.sort(a)
	 *
	 * @param a: An unsorted array of doubles.
	 * @return after the method returns, the array must be in ascending sorted
	 *         order.
	 */

	static double[] mergeSortIterative(double a[]) {

		mergeSort(a, a.length);

		return a;

	}// end mergesortIterative

	static void mergeSort(double arr[], int n) {
		int curr_size;
		int left_start;
		for (curr_size = 1; curr_size <= n - 1; curr_size = 2 * curr_size) {
			for (left_start = 0; left_start < n - 1; left_start += 2 * curr_size) {
				int mid = Math.min(left_start + curr_size - 1, n - 1);
				int right_end = Math.min(left_start + 2 * curr_size - 1, n - 1);
				mergeIterative(arr, left_start, mid, right_end);
			}
		}
	}

	static void mergeIterative(double arr[], int l, int m, int r) {
		int i, j, k;
		int n1 = m - l + 1;
		int n2 = r - m;
		double L[] = new double[n1];
		double R[] = new double[n2];
		for (i = 0; i < n1; i++)
			L[i] = arr[l + i];
		for (j = 0; j < n2; j++)
			R[j] = arr[m + 1 + j];
		i = 0;
		j = 0;
		k = l;
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				arr[k] = L[i];
				i++;
			} else {
				arr[k] = R[j];
				j++;
			}
			k++;
		}
		while (i < n1) {
			arr[k] = L[i];
			i++;
			k++;
		}
		while (j < n2) {
			arr[k] = R[j];
			j++;
			k++;
		}
	}

	/**
	 * Sorts an array of doubles using recursive implementation of Merge Sort. This
	 * method is static, thus it can be called as SortComparison.sort(a)
	 *
	 * @param a: An unsorted array of doubles.
	 * @return after the method returns, the array must be in ascending sorted
	 *         order.
	 */
	static double[] mergeSortRecursive(double a[]) {
		double tmp[] = new double[a.length];
		mSort(a, tmp, 0, a.length - 1);
		return a;
	}

	private static void mSort(double a[], double tmp[], int low, int high) {
		if (low < high) {
			int middle = (low + high) / 2;
			mSort(a, tmp, low, middle);
			mSort(a, tmp, middle + 1, high);
			mergeRecursive(a, tmp, low, middle + 1, high);
		}
	}

	private static void mergeRecursive(double a[], double tmp[], int left, int right, int rightEnd) {
		int leftEnd = right - 1;
		int k = left;
		int num = rightEnd - left + 1;

		while (left <= leftEnd && right <= rightEnd)
			if (a[left] <= a[right])
				tmp[k++] = a[left++];
			else
				tmp[k++] = a[right++];
		while (left <= leftEnd)
			tmp[k++] = a[left++];
		while (right <= rightEnd)
			tmp[k++] = a[right++];
		for (int i = 0; i < num; i++, rightEnd--)
			a[rightEnd] = tmp[rightEnd];
	}

	public static double[] fileArray(File file, int n) throws FileNotFoundException {
		double[] array = new double[n];
		Scanner in = new Scanner(file);
		for (int i = 0; i < array.length; i++) {
			array[i] = in.nextDouble();
		}
		in.close();
		return array;
	}

	public static long SortTimer(double[] a, int sort) {
		long startTime, endTime, timeTaken;
		long total = 0;
		for (int i = 0; i < 3; i++) {
			double[] x = new double[a.length];
			System.arraycopy(a, 0, x, 0, a.length);
			startTime = 0;
			startTime = System.nanoTime();
			switch (sort) {
			case 0:
				x = insertionSort(a);
				break;
			case 1:
				x = quickSort(a);
				break;
			case 2:
				x = mergeSortIterative(a);
				break;
			case 3:
				x = mergeSortRecursive(a);
				break;
			case 4:
				x = selectionSort(a);
				break;
			default:
				total = 0;
				break;
			}
			endTime = 0;
			endTime = System.nanoTime();
			timeTaken = endTime - startTime;
			total += timeTaken;
		}
		return total / 3;
	}

	public static void main(String[] args) throws FileNotFoundException{
		
	}
}// end class
