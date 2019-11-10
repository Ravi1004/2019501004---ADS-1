import java.util.Comparator;
// author : k.sandhya
public class Minpq<Key extends Comparable<Key>> { // perform minpq operation
    private Key[] pq;
    private int size;
    private Comparator<Key> comparator;
    public Minpq(final int capacity) {// requires capacity to perform operations.
        pq = (Key[]) new Comparable[capacity + 1];
    }

    /**
     * TIs method will check whether the array is empty or not.
     *
     * @return return true if empty else false it will take constant time because
     *         this will check whether the size is empty or not.
     */
    public boolean isEmpty() {
        if (size == 0)
            return true;
        else
            return false;
    }

    /**
     * this is a helper function which will give the size of the array, return type
     * is int and no parameters passed. time complexity for this is also constant
     *
     * @return size
     */

    public int size() {
        return size;
    }

    /**
     * . this is a swim method which will check the position and swap the key in its
     * place complexity will be order of logn
     *
     * @param k this is the key passed as parrameters
     *
     */
    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    /**
     * this is a sink method, which is top down approch, if an element is inserted
     * on top then it will find its positon and then swap. time complexity will be
     * order of N
     *
     * @param k this is a key passed as parameter
     *
     */
    private void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k;
            if (j < size && greater(j, j + 1)) {
                j++;
            }
            if (!greater(k, j)) {
                break;
            }
            swap(k, j);
            k = j;
        }
    }

    /**
     * This is to insert an element, by which we can send the value to its position
     * time complexity is contastant for inserting will be constant.
     *
     * @param key this is a key with a value assoiciated with it
     */
    public void insert(final Key key) {
        pq[++size] = key;
        swim(size);
    }

    /**
     * this will be called when we need to delete the maximum element. later
     * performs the sink operation time complexity will be constant to delete the
     * max element later the sink operation will be performed
     *
     * @return
     */
    public Key delMax() {
        Key max = pq[1];
        swap(1, size--);
        sink(1);
        pq[size + 1] = null;
        return max;
    }

    /**
     * this is a method which will give the maximum element from the array time
     * complexity will be constant
     *
     * @return returns the maximum element
     */
    public Key max() {
        return pq[1];
    }

    /**
     * this is helper function which is called when we need to compare the keys
     *
     * @param i value
     * @param j value
     *  to compare the values it takes the constant time.
     * @return true if the i > j else false.
     */
    private boolean greater(int i, int j) {
        if (comparator == null) {
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
        } else {
            return comparator.compare(pq[i], pq[j]) > 0;
        }
    }

    /**
     * this is called when we need to swap the values, called in swim and swap
     * methods
     *
     * @param i key 1
     * @param j key 2
     */
    private void swap(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }
}