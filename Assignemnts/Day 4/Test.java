import java.util.Arrays;
import java.util.Scanner;
class Test {
    public static void main(String[] args) {
        int[] arr = { 10, 20, 45, 78, 96, 45, 35, 98, 78, 85 };
        Minpq m1 = new Minpq(10);
        Maxpq m2 = new Maxpq(10);
        int[] arr2 = new int[10];
        for (int i = 0; i < arr.length; i++) {
            m1.insert(arr[i]);
            m2.insert(arr[i]);
        }
        System.out.println("Elements before deleting: ");
        System.out.println(m1.toString());
        //System.out.println(m2.toString());
        System.out.println("How many numbers to delete?");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int j = 0; j < n; j++) {
            m1.delmin();

            arr2[j] = (int) m2.delMax();

        }
        System.out.println("Elements after deleting : ");
        System.out.println(m1.toString());
        System.out.println("Largest elements after deleting : ");

        for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i]+" ");
        }



    }

}