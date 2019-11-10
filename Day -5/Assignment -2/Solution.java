/**
 * @author Ravi
 */
class Solution {
	/**
	 *
	 * @param arr
	 * @return
	 */
	public static double[] dynamicMedian(double[] arr) {
		double median = 0;
		int len = arr.length;
		Maxpq<Double> max = new Maxpq(arr.length);
		Minpq<Double> min = new Minpq(arr.length);
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] <= median) {
				max.insert(arr[i]);
			} else {
				min.insert(arr[i]);
			}

			if (min.size() > max.size() + 1) {
				max.insert(min.max());
				min.delMax();
			}else if (max.size() > min.size() + 1) {
				min.insert(max.max());
				max.delMax();
			}

			if (min.size() == max.size()) {
				median = (max.max() + min.max()) / 2.0;
			} else if (min.size() > max.size()) {
				median = (double) min.max();
			} else if (min.size() < max.size()) {
				median = (double) max.max();
			}

			arr[i] = median;
		}
		return arr;
	}
}