import java.util.*;

public class Threesum {
    public static int count(int[] arr) {
        int count = 0;
        int first;
        int last;
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            first = i + 1;
            last = arr.length - 1;
            while (first < last) {
                if (arr[i] + arr[first] + arr[last] == 0) {
                    System.out.println("Triplet is " + arr[i] + "," + arr[first] + "," + arr[last]);
                    count++;
                    first++;
                    last--;
                } else if (arr[i] + arr[first] + arr[last] < 0) {
                    first++;
                } else {
                    last--;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 3, 4, 0, -1, -3, -4 };
        System.out.println(count(arr));
    }
}