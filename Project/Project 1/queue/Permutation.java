import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        try{
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> randQ = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            randQ.enqueue(StdIn.readString());
        }
        // StdIn.readString().enqueue();
        for (int i = 0; i < k; i++){
            randQ.dequeue();
        }
        }
        catch (IllegalArgumentException e) {
            StdOut.println("Illegal Argument is Entered");
        }
        catch (NoSuchElementException e) {
            StdOut.println("No Such Element is present");
        }
   }
}