import java.util.NoSuchElementException;
import java.util.Scanner;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root; // root of BST

    private class Node {
        private Key key; // sorted by key
        private Value val; // associated data
        private Node left, right; // left and right subtrees
        private int size; // number of nodes in subtree

        public Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    /**
     * Initializes an empty symbol table.
     */
    public BST() {
    }

    /**
     * Returns true if this symbol table is empty.
     *
     * @return {@code true} if this symbol table is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null)
            return 0;
        else
            return x.size;
    }

    public boolean contains(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (key == null)
            throw new IllegalArgumentException("calls get() with a null key");
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return get(x.left, key);
        else if (cmp > 0)
            return get(x.right, key);
        else
            return x.val;
    }

    public void put(Key key, Value val) {
        if (key == null)
            throw new IllegalArgumentException("calls put() with a null key");
        if (val == null) {
            delete(key);
            return;
        }
        root = put(root, key, val);

    }

    private Node put(Node x, Key key, Value val) {
        if (x == null)
            return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = put(x.left, key, val);
        else if (cmp > 0)
            x.right = put(x.right, key, val);
        else
            x.val = val;
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    public void deleteMin() {
        if (isEmpty())
            throw new NoSuchElementException("Symbol table underflow");
        root = deleteMin(root);

    }

    private Node deleteMin(Node x) {
        if (x.left == null)
            return x.right;
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMax() {
        if (isEmpty())
            throw new NoSuchElementException("Symbol table underflow");
        root = deleteMax(root);

    }

    private Node deleteMax(Node x) {
        if (x.right == null)
            return x.left;
        x.right = deleteMax(x.right);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) {
        if (key == null)
            throw new IllegalArgumentException("calls delete() with a null key");
        root = delete(root, key);

    }

    private Node delete(Node x, Key key) {
        if (x == null)
            return null;

        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = delete(x.left, key);
        else if (cmp > 0)
            x.right = delete(x.right, key);
        else {
            if (x.right == null)
                return x.left;
            if (x.left == null)
                return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key min() {
        if (isEmpty())
            throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null)
            return x;
        else
            return min(x.left);
    }

    public Key max() {
        if (isEmpty())
            throw new NoSuchElementException("calls max() with empty symbol table");
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null)
            return x;
        else
            return max(x.right);
    }

    public Key floor(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to floor() is null");
        if (isEmpty())
            throw new NoSuchElementException("calls floor() with empty symbol table");
        Node x = floor(root, key);
        if (x == null)
            return null;
        else
            return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0)
            return x;
        if (cmp < 0)
            return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null)
            return t;
        else
            return x;
    }

    public Key floor2(Key key) {
        return floor2(root, key, null);
    }

    private Key floor2(Node x, Key key, Key best) {
        if (x == null)
            return best;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return floor2(x.left, key, best);
        else if (cmp > 0)
            return floor2(x.right, key, x.key);
        else
            return x.key;
    }

    public Key ceiling(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to ceiling() is null");
        if (isEmpty())
            throw new NoSuchElementException("calls ceiling() with empty symbol table");
        Node x = ceiling(root, key);
        if (x == null)
            return null;
        else
            return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0)
            return x;
        if (cmp < 0) {
            Node t = ceiling(x.left, key);
            if (t != null)
                return t;
            else
                return x;
        }
        return ceiling(x.right, key);
    }

    public Key select(int k) {
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException("argument to select() is invalid: " + k);
        }
        Node x = select(root, k);
        return x.key;
    }

    private Node select(Node x, int k) {
        if (x == null)
            return null;
        int t = size(x.left);
        if (t > k)
            return select(x.left, k);
        else if (t < k)
            return select(x.right, k - t - 1);
        else
            return x;
    }

    public int rank(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to rank() is null");
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        if (x == null)
            return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return rank(key, x.left);
        else if (cmp > 0)
            return 1 + size(x.left) + rank(key, x.right);
        else
            return size(x.left);
    }

    // public Iterable<Key> keys() {
    //     if (isEmpty())
    //         return new Queue<Key>();
    //     return keys(min(), max());
    // }

    // public Iterable<Key> keys(Key lo, Key hi) {
    //     if (lo == null)
    //         throw new IllegalArgumentException("first argument to keys() is null");
    //     if (hi == null)
    //         throw new IllegalArgumentException("second argument to keys() is null");

    //     Queue<Key> queue = new Queue<Key>();
    //     keys(root, queue, lo, hi);
    //     return queue;
    // }

    // private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
    //     if (x == null)
    //         return;
    //     int cmplo = lo.compareTo(x.key);
    //     int cmphi = hi.compareTo(x.key);
    //     if (cmplo < 0)
    //         keys(x.left, queue, lo, hi);
    //     if (cmplo <= 0 && cmphi >= 0)
    //         queue.enqueue(x.key);
    //     if (cmphi > 0)
    //         keys(x.right, queue, lo, hi);
    // }

    // public int size(Key lo, Key hi) {
    //     if (lo == null)
    //         throw new IllegalArgumentException("first argument to size() is null");
    //     if (hi == null)
    //         throw new IllegalArgumentException("second argument to size() is null");

    //     if (lo.compareTo(hi) > 0)
    //         return 0;
    //     if (contains(hi))
    //         return rank(hi) - rank(lo) + 1;
    //     else
    //         return rank(hi) - rank(lo);
    // }

    // public int height() {
    //     return height(root);
    // }

    // private int height(Node x) {
    //     if (x == null)
    //         return -1;
    //     return 1 + Math.max(height(x.left), height(x.right));
    // }

    // public Iterable<Key> levelOrder() {
    //     Queue<Key> keys = new Queue<Key>();
    //     Queue<Node> queue = new Queue<Node>();
    //     queue.enqueue(root);
    //     while (!queue.isEmpty()) {
    //         Node x = queue.dequeue();
    //         if (x == null)
    //             continue;
    //         keys.enqueue(x.key);
    //         queue.enqueue(x.left);
    //         queue.enqueue(x.right);
    //     }
    //     return keys;
    // }

    private boolean isBST() {
        return isBST(root, null, null);
    }

    private boolean isBST(Node x, Key min, Key max) {
        if (x == null)
            return true;
        if (min != null && x.key.compareTo(min) <= 0)
            return false;
        if (max != null && x.key.compareTo(max) >= 0)
            return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

    private boolean isSizeConsistent() {
        return isSizeConsistent(root);
    }

    private boolean isSizeConsistent(Node x) {
        if (x == null)
            return true;
        if (x.size != size(x.left) + size(x.right) + 1)
            return false;
        return isSizeConsistent(x.left) && isSizeConsistent(x.right);
    }

    // private boolean isRankConsistent() {
    //     for (int i = 0; i < size(); i++)
    //         if (i != rank(select(i)))
    //             return false;
    //     for (Key key : keys())
    //         if (key.compareTo(select(rank(key))) != 0)
    //             return false;
    //     return true;
    // }

    public static void main(String[] args) {
        BST<Integer, String> b = new BST<Integer, String>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Make a tree structure : ");
        System.out.println("Enter the node key and value : ");
        b.root = new BST<Integer, String>().new Node(sc.nextInt(), sc.next(), sc.nextInt());
        System.out.println("Enter the node key and value : ");
        b.root.left = new BST<Integer, String>().new Node(sc.nextInt(), sc.next(), sc.nextInt());
        System.out.println("Enter the node key and value : ");
        b.root.right = new BST<Integer, String>().new Node(sc.nextInt(), sc.next(), sc.nextInt());
        System.out.println("Enter the node key and value : ");
        b.root.left.left = new BST<Integer, String>().new Node(sc.nextInt(), sc.next(), sc.nextInt());
        System.out.println("Enter the node key and value : ");
        b.root.right.right = new BST<Integer, String>().new Node(sc.nextInt(), sc.next(), sc.nextInt());
        System.out.println("Enter the node key and value : ");
        b.root.left.right = new BST<Integer, String>().new Node(sc.nextInt(), sc.next(), sc.nextInt());

        System.out.println("Enter the node key and value : ");
        b.root.right.left = new BST<Integer, String>().new Node(sc.nextInt(), sc.next(), sc.nextInt());



        System.out.println(b.isBST());
    }
}


