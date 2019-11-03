package practice;

public class Stack {

    static int size = 0;

    public int[] push(int a, int[] arr) {
        if (size < arr.length) {
            arr[size] = a;
            size++;
        } else if (size > arr.length) {
            System.out.println("over flow");
            return null;
        }
        //return true;
        return arr;
    }

    public int[] pop(int[] arr) {
        /* if (size > 0) {
            size--;
            arr[size] = 0;

            //return true;
        }
        else{
            System.out.println("under flow");
        }*/
        //return false;
        for (int i = 0; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[size - 1] = 0;
        return arr;
    }
    //boolean value = push(20);
}
