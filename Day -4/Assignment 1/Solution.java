
import java.util.Random;

/**
 * Class Solution.
 *
 * @author Ravi
 */
class Solution {

	/**
	 * @param a first number
	 * @param b second number
	 * @return boolean value based on the difference between those numbers Time
	 *         COmplexity for this method is O(1).
	 */
	private static boolean less(final int a, final int b) {
		int diff = a - b;
		if (diff < 0) {
			return true;
		}one
		return false;
	}

	/**
	 *
	 * @param arr array
	 * @param i   number
	 * @param j   number Time COmplexity for this method is O(1).
	 *
	 */
	private static void exch(final int[] arr, final int i, final int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	/**
	 * @param arr  array
	 * @param low  lowe index
	 * @param high high index
	 * @return int that is the place of the parition where it is to be inserted in
	 *         array Time Complexity for this method is O(N^2).
	 */
	private static int partition(final int[] arr, final int low, final int high) {
		int i = low;
		int j = high + 1;
		while (true) {
			while (less(arr[++i], arr[low])) {
				if (i == high) {
					break;
				}
			}
			while (less(arr[low], arr[--j])) {
				if (j == low) {
					break;
				}
			}

			if (i >= j) {
				break;
			}
			exch(arr, i, j);
		}
		exch(arr, low, j);
		return j;
	}

	/**
	 * @param arr  array
	 * @param low  low index
	 * @param high high index Time Complexity for this method is O(1).
	 */
	private static void sort(final int[] arr, final int low, final int high) {
		if (high <= low) {
			return;
		}
		int j = partition(arr, low, high);
		sort(arr, low, j - 1);
		sort(arr, j + 1, high);
	}

	/**
	 * @param arr array to be sorted Time Complexity for this method is O(N).
	 */
	private static void sort(final int[] arr) {
		Random randomObj = new Random();
		for (int i = arr.length - 1; i > 0; i--) {
			int index = randomObj.nextInt(i + 1);
			int temp = arr[index];
			arr[index] = arr[i];
			arr[i] = temp;
		}
		sort(arr, 0, arr.length - 1);
	}

	/**
	 * @param arr array to be sorted
	 * @return sorted array Time Complexity for this method is O(1).
	 */
	public static int[] quickSort(final int[] arr) {
		// fill you code Here
		sort(arr);
		return arr;
	}
}