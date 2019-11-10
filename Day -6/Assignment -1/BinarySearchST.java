import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @param <Key>   Key.
 * @param <Value>  Value.
 * @author Ravi
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] values;
    private int size;
    public BinarySearchST() {
        this.keys = (Key[]) new Comparable[20];
        this.values = (Value[]) new Comparable[20];
        this.size = 0;
    }

    /**
     * This is a helper function called when array size is full.
     * time complexity will be order of N because it will loop from
     * first to last element to store in a new array.
     */
    private void resize() {
        keys = Arrays.copyOf(keys, keys.length * 2);
        values = Arrays.copyOf(values, values.length * 2);
    }

    /**
     * @param key   key
     * @param value value
     * @param index index at which these are to be inserted in.
     */
    private void putInPlace(final Key key, final Value value, final int index) {
        Key[] tempArray1 = (Key[]) Arrays.copyOf(keys, size + 1);
        Value[] tempArray2 = (Value[]) Arrays.copyOf(values, size + 1);
        int index1 = index;
        if (index1 < size && index1 >= 0) {
            tempArray1[index1] = (Key) key;
            tempArray2[index1] = (Value) value;
            for (int j = index1 + 1; j <= size; j++) {
                tempArray1[j] = (Key) keys[index1];
                tempArray2[j] = (Value) values[index1];
                index1 += 1;
            }
            keys = (Key[]) tempArray1;
            values = (Value[]) tempArray2;
            tempArray1 = null;
            tempArray2 = null;
            this.setSize(this.getSize() + 1);
        }
    }

    /**
     * @param key key
     * @return returns the position of the key.
     */
    public int rank(final Key key) {
        int lo = 0;
        int hi = size - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key.compareTo((Key) keys[mid]) <= 0) {
                hi = mid - 1;
            } else if (key.compareTo((Key) keys[lo]) > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return lo;
    }

    /**
     * @param key   key
     * @param value value
     */
    public void put(final Key key, final Value value) {
        if (size == keys.length) {
            resize();
        }
        int rankOfTheItem = rank(key);
        if (keys[rankOfTheItem] != null && keys[rankOfTheItem].compareTo(key) == 0) {
            values[rankOfTheItem] = value;
            return;
        }
        if (rankOfTheItem < size) {
            putInPlace(key, value, rankOfTheItem);
        } else {
            keys[size] = (Key) key;
            values[size] = (Value) value;
            this.setSize(this.getSize() + 1);
        }
    }

    /**
     * @param key key
     * @return true if key is there, else false.
     */
    public boolean contains(final Key key) {
        int rankOfTheKey = rank(key);
        if (rankOfTheKey >= 0 && rankOfTheKey < size && keys[rankOfTheKey].compareTo(key) == 0) {
            return true;
        }
        return false;
    }

    /**
     * @param key key
     * @return key value
     */
    public Value get(final Key key) {
        int rankOfTheKey = rank(key);
        if (rankOfTheKey >= 0 && rankOfTheKey < size && keys[rankOfTheKey].compareTo(key) == 0) {
            return values[rankOfTheKey];
        }
        return null;
    }

    /**
     * this will return the maximum value of the key
     * @return max key in keys
     * Time Complexity : O(1).
     */
    public Key max() {
        return keys[size - 1];
    }

    /**
     * @param key key
     * @return closest key to key in keys array Time Complexity : O(log N).
     */
    public Key floor(final Key key) {

        int rankOfTheKey = rank(key);
        if (rankOfTheKey < size && keys[rankOfTheKey].compareTo(key) == 0) {
            return key;
        } else {
            return keys[rankOfTheKey - 1];
        }
    }

    /**
     * Deletes The Minimum Key in Keys. Time Complexity : O(1).
     */
    public void deleteMin() {
        this.keys = Arrays.copyOfRange(keys, 1, keys.length);
        this.values = Arrays.copyOfRange(values, 1, values.length);
        this.setSize(this.getSize() - 1);
    }

    /**
     * @return keys
     */
    public Iterable<Key> keys() {
        Queue<Key> temp = new Queue<Key>();
        for (int ind = 0; ind < size; ind++) {
            temp.enqueue(keys[ind]);
        }
        return temp;
    }

    /**
     * @return keys
     */
    public Key[] getKeys() {
        return keys;
    }

    /**
     * @return values
     */
    public Value[] getValues() {
        return values;
    }

    /**
     * @return size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size1 sets size
     */
    public void setSize(final int size1) {
        this.size = size1;
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < size; i++) {
            str = str + keys[i] + " " + values[i] + "\n";
        }
        return str;
    }

    public static void main(String[] args) {
        BinarySearchST<String, Integer> symbolTable = new BinarySearchST<String, Integer>();
        symbolTable.put("E", 0);
        symbolTable.put("A", 1);
        symbolTable.put("S", 2);
        symbolTable.put("Y", 3);
        symbolTable.put("Q", 4);
        symbolTable.put("U", 5);
        symbolTable.put("E", 6);
        symbolTable.put("S", 7);
        symbolTable.put("T", 8);
        symbolTable.put("I", 9);
        symbolTable.put("O", 10);
        symbolTable.put("N", 11);
        System.out.println(symbolTable);
        symbolTable.deleteMin();
        symbolTable.deleteMin();
        symbolTable.deleteMin();
        symbolTable.deleteMin();
        System.out.println(Arrays.toString(symbolTable.keys));
        System.out.println(symbolTable.floor("Z"));
    }
}
