/**
 * Class Solution.
 *
 * @author Ravi
 */
class Solution {
	/**
	 * @param a first string
	 * @param b second string
	 * @return boolean value based on the difference between those two strings Time
	 *         Complexity for this method is O(1).
	 */
	private static boolean less(final String a, final String b) {
		int diff = a.compareTo(b);
		if (diff < 0) {
			return true;
		}
		return false;
	}

	/**
	 * @param a    array
	 * @param aux  auxilary array
	 * @param low  low index
	 * @param mid  mid index
	 * @param high high index Time Complexity for this method is O(N).
	 */
	public static void merge(final String[] a, final String[] aux, final int low, final int mid, final int high) {
		for (int k = low; k <= high; k++) {
			aux[k] = a[k];
		}
		int i = low;
		int j = mid + 1;
		for (int k = low; k <= high; k++) {
			if (i > mid) {
				a[k] = aux[j++];
			} else if (j > high) {
				a[k] = aux[i++];
			} else if (less(aux[j], aux[i])) {
				a[k] = aux[j++];
			} else {
				a[k] = aux[i++];
			}
		}
	}

	/**
	 * @param a    array
	 * @param aux  auxilary array
	 * @param low  low index
	 * @param high high index Time Complexity for this method is O(1).
	 */
	private static void sort(final String[] a, final String[] aux, final int low, final int high) {
		if (high <= low) {
			return;
		}
		int mid = low + (high - low) / 2;
		sort(a, aux, low, mid);
		sort(a, aux, mid + 1, high);
		merge(a, aux, low, mid, high);
	}

	/**
	 * @param a array to be sorted Time Complexity for this method is O(1).
	 */
	public static void sort(final String[] a) {
		String[] aux = new String[a.length];
		sort(a, aux, 0, a.length - 1);
	}

	/**
	 * @param arr array to be sorted
	 * @return sorted array Time Complexity for this method is O(1).
	 */
	public static String[] mergeSort(final String[] arr) {
		// fill you code Here
		sort(arr);
		return arr;
	}

}