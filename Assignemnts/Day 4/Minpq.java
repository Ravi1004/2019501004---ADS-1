import java.util.Comparator;
import java.util.Arrays;
/**
 * @aythor Ravi
 * @param <Key>
 */
public class Minpq<Key extends Comparable<Key>> {
    private Key[] pq;
    private int size;
    private Comparator<Key> comparator;

    public Minpq(final int capacity) {
        pq = (Key[]) new Comparable[capacity + 1];
    }

    public boolean isEmpty() {
        if (size == 0)
            return true;
        else
            return false;
    }

    public int size() {
        return size;
    }

    public void insert(final Key key) {
        pq[++size] = key;
        swim(size);
    }

    public Key min() {
        return pq[1];
    }

    public Key delmin() {
        Key max = pq[1];
        swap(1, size--);
        sink(1);
        pq[size + 1] = null;
        return max;
    }

    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

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

    private boolean greater(int i, int j) {
        if (comparator == null) {
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
        } else {
            return comparator.compare(pq[i], pq[j]) > 0;
        }
    }

    private void swap(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    public boolean remove(int n) {
        if (pq.length < n) {
            return false;
        }
        else {
            for (int i = 0; i < n; i++) {
                pq[i] = pq[i + 1];
            }
            return true;
        }
    }

    public String toString() {
        String st = "";
        for (int i = 0; i < pq.length; i++) {
            st+=pq[i]+" ";
        }
        return st;
    }
}

