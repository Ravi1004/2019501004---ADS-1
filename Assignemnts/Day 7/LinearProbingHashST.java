
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
public class LinearProbingHashST<Key, Value> {
    private static final int INIT_CAPACITY = 4;

    private int n;
    private int m;
    private Key[] keys;
    private Value[] vals;


    public LinearProbingHashST(int capacity) {
        m = capacity;
        n = 0;
        keys = (Key[]) new Object[m];
        vals = (Value[]) new Object[m];
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    public void put(Key key, Value val) {
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

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m)
            if (keys[i].equals(key))
                return vals[i];
        return null;
    }


    public static void main(String[] args) {
        int c = 0;
        LinearProbingHashST<Integer, Integer> LPHST = new LinearProbingHashST<Integer, Integer>(1000000);
        Random rand = new Random();
        for (int i = 0; i <= 500000; i++) {
            int key = rand.nextInt(1000000);
            LPHST.put(key, i);
        }

        for (int j = 100; j < 1000000; j = j + 100) {
            if (LPHST.get(j) != null) {
                c++;
            }
        }
        System.out.println((float)c /1000000);
    }
}
