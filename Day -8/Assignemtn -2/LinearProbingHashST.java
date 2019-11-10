import java.util.*;

/**
 * LinearProbingHashST class implements the API of Hash Tables under Linear
 * Probing
 *
 * @param <Key>
 * @param <Value>
 */
public class LinearProbingHashST<Key extends Comparable<Key>, Value> {
    private static final int INIT_CAPACITY = 4;
    private int n; // number of key-value pairs in the symbol table
    private int m; // size of linear probing table
    private Key[] keys; // the keys
    private Value[] vals; // the values

    public LinearProbingHashST() {
        this(INIT_CAPACITY);
    }

    /**
     * Construtor
     *
     * @param capacity
     */
    public LinearProbingHashST(int capacity) {
        this.m = capacity;
        this.n = 0;
        this.keys = (Key[]) new Comparable[m];
        this.vals = (Value[]) new Comparable[m];
    }

    /**
     * Time Complexity : O(1) - Best Time Complexity : O(N) - Worst
     *
     * @param key
     * @param val
     */
    public void put(Key key, Value val) {
        if (val == null) {
            delete(key);
            return;
        }

        // double table size if 50% full
        if (n >= m / 2)
            resize(2 * m);

        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        n++;
    }

    /**
     * get method return the value of a particular key Time Complexity : O(N)
     *
     * @param key
     * @return
     */
    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                return vals[i];
            }
        }
        return null;
    }

    /**
     * delete method deletes the particular key from the list Time Complexity : o(N)
     *
     * @param key
     */
    public void delete(Key key) {
        if (!contains(key))
            return;
        // find position i of key
        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % m;
        }

        // delete key and associated value
        keys[i] = null;
        vals[i] = null;

        // rehash all keys in same cluster
        i = (i + 1) % m;
        while (keys[i] != null) {
            // delete keys[i] an vals[i] and reinsert
            Key keyToRehash = keys[i];
            Value valToRehash = vals[i];
            keys[i] = null;
            vals[i] = null;
            n--;
            put(keyToRehash, valToRehash);
            i = (i + 1) % m;
        }
        n--;
        // halves size of array if it's 12.5% full or less
        if (n > 0 && n <= m / 8) {
            resize(m / 2);
        }
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    private int hash(Key key) {
        // System.out.println(key.hashCode());
        return (key.hashCode() & 0x7fffffff) % m;
    }

    private void resize(int capacity) {
        LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<Key, Value>(capacity);
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                temp.put(keys[i], vals[i]);
            }
        }
        keys = temp.keys;
        vals = temp.vals;
        m = temp.m;
    }

    public static void main(String[] args) {
        LinearProbingHashST<String, Integer> st = new LinearProbingHashST<String, Integer>();
        st.put("Ravi", 1);
        System.out.println(st.get("Ravi"));
        st.put("Viswa", 3);
        System.out.println(st.get("Viswa"));
    }
}