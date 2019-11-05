class Solution {
	/**
	 *
	 * @param arr
	 * @return true if the array is min heap. return false if the it is no the in minheap.
	 */
	public static boolean isMinHeap(double[] arr) {
		if (arr.length < 1) {
			return false;
		}
		for (int i = 0; i < arr.length / 2 - 1; i++) {
			if (arr[i] > arr[2 * i+1] || arr[i] > arr[2 * i + 2]) {
				return false;

			}

		}
		return true;
	}
}