import java.util.*;

/**
 * this queue class will implements itterable class
 * @param <Item> item is a generic type
 * @author Ravi
 */
public class Queue<Item> implements Iterable<Item> {
    public Item[] q;
    public int n;
    public int first;
    public int last;

    public Queue() {
        this.q = (Item[]) new Object[2];
        this.n = 0;
        this.first = 0;
        this.last = 0;
    }

    /**
     * is called when to check whether the queue is empty or not
     *
     * @return true if empty else false
     * time complexity will be constant
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * this is size function which is called to check the size of the queue
     * @return the size of the queue
     */
    public int size() {
        return n;
    }

    /**
     * this is a helper method which is called when the array size is full.
     * time complexity will order of N
     * @param capacity returns the updated capacity
     */
    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = q[(first + i) % q.length];
        }
        q = temp;
        first = 0;
        last = n;
    }

    /**
     * this is a enqueue method which is used to insert the item in to the queue
     *
     * @param item item to be inserted into the queue
     */
    public void enqueue(Item item) {
        if (n == q.length)
            resize(2 * q.length);
        q[last++] = item;
        if (last == q.length)
            last = 0;
        n++;
    }

    /**
     * this is dequeue method which is used to delete the element in the queue
     *
     * @return returns the deleted item.
     */
    public Item dequeue() {
        Item item = q[first];
        q[first] = null;
        n--;
        first++;
        if (first == q.length)
            first = 0;
        if (n > 0 && n == q.length / 4)
            resize(q.length / 2);
        return item;
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;

        public boolean hasNext() {
            return i < n;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = q[(i + first) % q.length];
            i++;
            return item;
        }
    }

    /**
     * this is to string method
     * @return returns the string in format.
     */
    public String toString() {
        String st = "";
        int i;
        for (i = 0; i < last; i++) {
            st += q[i] + " ";
        }
        return st;
    }
}
