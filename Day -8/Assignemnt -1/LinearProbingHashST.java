import java.util.*;
/**
 * @author Ravi
 * @param <Key>
 * @param <Value>
 */
class LinearProbingHashST<Key, Value> {
    private static final int INIT_CAPACITY = 4;

    private int n; // number of key-value pairs in the symbol table
    private int m; // size of linear probing table
    private Key[] keys; // the keys
    private Value[] vals; // the values

    /**
     * Initializes an empty symbol table.
     */
    public LinearProbingHashST() {
        this(INIT_CAPACITY);
    }

    /**
     * Initializes an empty symbol table with the specified initial capacity.
     *
     * @param capacity the initial capacity
     */
    public LinearProbingHashST(int capacity) {
        m = capacity;
        n = 0;
        keys = (Key[]) new Comparable[m];
        vals = (Value[]) new Comparable[m];
    }

    public int size() {
        return n;
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

    /**
     * Returns true if this symbol table is empty.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns true if this symbol table contains the specified key.
     *
     * @param key the key
     * @return bool time complexity O(1)
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

    // hash function for keys - returns value between 0 and M-1
    // time complexity O(1)
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    /**
     * Inserts the specified key-value pair into the symbol table
     *
     * @param key the key
     * @param val the value time complexity O(N)
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
     * Returns the value associated with the specified key.
     *
     * @param key the key
     * @return the value associated with {@code key}; {@code null} if no such value
     *         time complexity O(N)
     */
    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m)
            if (keys[i].equals(key))
                return vals[i];
        return null;
    }

    /**
     * Removes the specified key and its associated value from this symbol table (if
     * the key is in this symbol table).
     *
     * @param key the key time complexity O(2N)
     */
    public void delete(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to delete() is null");
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
        if (n > 0 && n <= m / 8)
            resize(m / 2);

        assert check();
    }

    // integrity check - don't check after each put() because
    // integrity not maintained during a delete()
    // time complexity O(1)
    private boolean check() {

        // check that hash table is at most 50% full
        if (m < 2 * n) {
            System.err.println("Hash table size m = " + m + "; array size n = " + n);
            return false;
        }

        // check that each key in table can be found by get()
        for (int i = 0; i < m; i++) {
            if (keys[i] == null)
                continue;
            else if (get(keys[i]) != vals[i]) {
                System.err.println("get[" + keys[i] + "] = " + get(keys[i]) + "; vals[i] = " + vals[i]);
                return false;
            }
        }
        return true;
    }

    public static int wordfreq(String[] arofinput, String k) {
        int freq = 0;
        for (int i = 0; i < arofinput.length; i++) {
            if (arofinput[i].equals(k)) {
                freq++;
            }
        }
        return freq;
    }

    public static void main(String[] args) {
        LinearProbingHashST<String, Integer> st = new LinearProbingHashST<String, Integer>();
        String[] arr = { "Ravi", "Viswa", "is", "a", "killer", "killer" };
        for (int i = 0; i < arr.length; i++) {
            if (st.contains(arr[i])) {
                st.put(arr[i], (st.get(arr[i]) + 1));
            } else {
                st.put(arr[i], 1);
            }
        }
        System.out.println(st.get("Ravi"));
        System.out.println(st.get("Viswa"));
        System.out.println(st.get("is"));
        System.out.println(st.get("a"));
        System.out.println(st.get("killer"));
        System.out.println("enter your input");
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        int spaceCount = 0;


        for (char c : input.toCharArray()) {
            if (c == ' ') {
                spaceCount++;
            }
        }
\

        String[] arrOfStr = new String[spaceCount + 1];
        arrOfStr = input.split(" ", 10);
        boolean a = false;
        for (int i = 0; i < arrOfStr.length; i++) {
            if (!st.contains(arrOfStr[i])) {
                a = false;
                System.out.println("false");
                break;
            } else if (st.contains(arrOfStr[i]) && st.get(arrOfStr[i]) >= st.wordfreq(arrOfStr, arrOfStr[i])) {
                a = true;
            }
        }
        if (a == false) {
            System.out.println("not the right input");
        } else {
            System.out.println("the right input");

        }

    }

}