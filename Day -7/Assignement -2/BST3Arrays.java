import java.util.*;

/**
 * @author Ravi
 */
/**
 * BST3Arrays class to have 3 Arrays One with the elements of the Search tree
 * The Second with the Indices of the Lesser elements to Parent The Third with
 * the indices of the greater elements to the Parent
 */
public class BST3Arrays<Key extends Comparable<Key>, Value> {

    Key[] rootArray;
    Integer[] leftArray;
    Integer[] rightArray;
    int rootPos = 1;
    int leftPos = 1;
    int rightPos = 1;

    public BST3Arrays() {
        rootArray = (Key[]) new Comparable[40];
        leftArray = new Integer[10];
        rightArray = new Integer[10];
    }

    /**
     * Insert method to insert the keys and respective indices into their arrays
     * Time Complexity : O(N)
     *
     * @param key
     * @param value
     */
    public void insert(Key key) {
        if (rootArray[1] == null) {
            rootArray[1] = key;
            return;
        }
        for (int i = 1; i < rootArray.length;) {
            if (rootArray[i] != null && key.compareTo(rootArray[i]) > 0) {
                i = (2 * i) + 1;
            } else if (rootArray[i] != null && key.compareTo(rootArray[i]) < 0) {
                i = 2 * i;
            } else {
                rootArray[i] = key;
                if (i % 2 == 0) {
                    leftArray[leftPos++] = i;
                } else {
                    rightArray[rightPos++] = i;
                }
                break;
            }
        }
    }

    /**
     * checks if the element is in BST returns true if present else false.
     *
     * @param key key to be searched.
     * @return boolean.
     */
    public boolean contains(Key key) {
        for (int i = 1; i < rootArray.length;) {
            if (rootArray[i] != null && key.compareTo(rootArray[i]) > 0) {
                i = 2 * i;
                i = i + 1;
            } else if (rootArray[i] != null && key.compareTo(rootArray[i]) < 0) {
                i = 2 * i;
            } else {
                if (rootArray[i] == null) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        BST3Arrays bstArr = new BST3Arrays();
        bstArr.insert(6);
        bstArr.insert(40);
        bstArr.insert(1);
        bstArr.insert(50);
        bstArr.insert(91);
        bstArr.insert(3);
        bstArr.insert(10);
        bstArr.insert(51);
        bstArr.insert(21);
        bstArr.insert(30);
        bstArr.insert(26);
        bstArr.insert(21);

        System.out.println("-------------------------------------------");
        System.out.println("root nodes = " + bstArr.toString(bstArr.rootArray));
        System.out.println("left nodes = " + bstArr.toString(bstArr.leftArray));
        System.out.println("right nodes = " + bstArr.toString(bstArr.rightArray));
        System.out.println("-------------------------------------------");
    }

    /**
     * displays the array.
     *
     * @param key array of keys.
     * @return keys in array in form of string.
     */
    String toString(Key[] key) {
        String str = "";
        for (int i = 1; i < key.length; i++) {
            if (key[i] == null || key[i].equals(new Integer(0))) {
                str = str + -1 + " ";
                continue;
            }
            str = str + key[i] + " ";
        }
        return str;
    }
}