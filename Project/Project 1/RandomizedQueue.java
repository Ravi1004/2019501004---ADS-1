import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item arr[];
    private int size = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        arr = (Item[])new Object[10];
    }

    public boolean isEmpty() {
        return size == 0;

    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if(null == item){
            throw new NoSuchElementException();
        }
        if (size == arr.length) {
            resize();
        } else {
            arr[size] = item;
            size++;
        }
    }

    public void resize() {

        Item[] arr2 = (Item[]) new Object[2*arr.length];
        for (int l = 0; l < arr.length; l++) {
            arr2[l] = arr[l];
        }
        arr = arr2;
    }

    // remove and return a random item
    //might throw IllegalArgumentException
    public Item dequeue() {
        try{
            StdRandom.shuffle(arr);
        }
        catch(IllegalArgumentException e){
            throw new IllegalArgumentException();
        }
        Item abc = arr[size - 1];
        arr[size - 1] = null;
        size--;
        return abc;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        StdRandom.shuffle(arr);
        Item abc = arr[size - 1];
        return abc;
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    // return an independent iterator over items in random order
    private class ArrayIterator implements Iterator<Item> {
        /**
         * . current node
         */
        private int d;
        /**
         * . This is the hasNext method which will return the item
         *
         * @return current node
         */
        public boolean hasNext() {
            return Items[d] != null;
        }
        /**
         * . This is the next method which will retun the item and moves to the next
         * item
         *
         * @return the item
         */
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return item[b++];
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> RQ = new RandomizedQueue<Integer>();

        RQ.enqueue(10);
        RQ.enqueue(20);
        RQ.enqueue(30);
        RQ.enqueue(40);
        RQ.enqueue(50);
        RQ.enqueue(60);
        RQ.enqueue(70);
        RQ.enqueue(80);
        RQ.dequeue();
        RQ.dequeue();


        for (int b : RQ.arr) {
            System.out.println(b);
        }



    }

}