package practice;

public class Testmain {
    public static void main(String[] args) {
        Stack st = new Stack();
        int[] arr = new int[10];
        arr = st.push(20, arr);
        arr = st.push(30, arr);
        arr = st.push(120, arr);
        arr = st.push(80, arr);
        arr = st.pop(arr);
        System.out.println("Stack values: ");
        for (int i : arr) {
            System.out.print(i + ", ");
        }

        st.pop(arr);
        System.out.println("Stack values: ");
        for (int i : arr) {
            System.out.print(i + ", ");
        }

    }
}