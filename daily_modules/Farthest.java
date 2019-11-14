
/**
 * @author Ravi
 */
import java.util.Scanner;

class Farthest {

    public static void main(String[] args) {
        System.out.println("Enter the size of the ");
        Scanner s = new Scanner(System.in);
        int size = s.nextInt();
        double[] arr = new double[size];
        for (int i = 0; i < size; i++) {
            arr[i] = s.nextDouble();
        }
        System.out.println("Min element " + min(arr));
        System.out.println("Man element " + max(arr));

    }

    /**.
     * This is a min method, this will take an array as input and will
     * return the minimum value from the array
     * @param arr This is an array passed as an input to this method
     * @return minumum value from the array
     * Worst case time complexity will be O(n),
     * best case comolexity will be constant.
     */
    public static double min(double[] arr) {
        double m = arr[0];
        for (int a = 1; a < arr.length; a++) {
            if (m > arr[a]) {
                m = arr[a];
            }
        }
        return m;

    }

    /**.
     * This is a max method, this will take an double array as input and
     * return the maximum value from the array
     * @param arr Input double arry for this method
     * @return maximum value in given array
     * Worst case complexity will be O(n),
     * Best case complexity will be constant.
     */
    public static double max(double[] arr) {
        double ma = arr[0];
        for (int a = 1; a < arr.length; a++) {
            if (ma < arr[a]) {
                ma = arr[a];
            }
        }
        return ma;
    }
}


