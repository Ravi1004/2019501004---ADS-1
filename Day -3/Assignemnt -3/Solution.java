import java.util.*;

public class Solution {
	public int[] sortInsertion(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = i; j > 0 && (arr[j] < arr[j - 1]); j--) {
				int temp = arr[j];
				arr[j] = arr[j - 1];
				arr[j - 1] = temp;
			}
		}
		return arr;
	}

	public int[] sortSelection(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int min = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[min]) {
					min = j;
				}
			}
			int temp = arr[i];
			arr[i] = arr[min];
			arr[min] = temp;
		}
		return arr;
	}
}