public class BST<Key extends Comparable<Key>, Value> {
    Node root;

    public class Node {
        Key key;
        Value val;
        Node left;
        Node right;
        int size;
        /**.
         * @param k the key in the key value pair
         * @param v the value associated with the key
         */
        public Node(Key k, Value v, int n) {
            key = k;
            val = v;
            left = null;
            right = null;
            this.size = n;
        }
    }

    /**
     *to check the size of the
     * @return
     */
    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method inserts the key at
     *
     * @param k
     * @return
     */
    public void put(Key k, Value v) {
        if (k == null) {
            return;
        }
        if (v == null) {
            delete(k);
            return;
        }
        root = put(root, k, v);
    }
/**
 * this is a put method which is used to insert the key value pair
 * @param x this is a node type parameter
 * @param key this is key
 * @param val value associated with the key
 * @return the node
 */
    public Node put(Node x, Key key, Value val) {
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

    /**.
     * This is a get method which will return the value when key is passed
     * @param k the key for which the value is to be fetched.
     * @return returns the value
     * thime Complexity will be order of N
     */
    public Value get(Key k) {
        Node currentNode = root;
        while (currentNode != null) {
            if (k.compareTo(currentNode.key) < 0) {
                currentNode = currentNode.left;
            } else if (k.compareTo(currentNode.key) > 0) {
                currentNode = currentNode.right;
            } else {
                return currentNode.val;
            }
        }
        return null;
    }

    /**
     * This is a method which will return the max element in the tree
     *
     * @return returns the key with maximum value.
     * Complexity is order of N
     */
    public Key max() {
        Node x = root;
        if (x == null) {
            return null;
        }
        while (x != null) {
            if (x.right == null) {
                return x.key;
            } else {
                x = x.right;
            }
        }
        return null;
    }

    /**
     * This is a method which will return the min element in the tree
     *
     * @return returns the key with minimum value
     * Complexity is of ~O(N) when there
    */
    public Key min() {
        if (isEmpty()) {
            return null;
        }
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null)
            return x;
        else
            return min(x.left);
    }

    /**
     *
     * @param key key will be passed as parameter
     * @return returns the key which is the type of key
     */
    public Key floor(Key key) {
        if (key == null) {
            return null;
        }
        if (isEmpty()) {
            return null;
        }
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

    /**
     *returns the top most rounded of value
     * @param key
     * @return the key rounded to top value
     */
    public Key ceiling(Key key) {
        if (key == null) {
            return null;
        }
        if (isEmpty()) {
            return null;
        }
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
            return null;
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

    /**
     *this will return the position of the key
     * @param key
     * @return the postion of the key
     */
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

    /**
     * this will delete the key
     * @param key the key to be deleted from the tree
     */
    public void delete(Key key) {
        if (key == null) {
            return;
        }
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

    /**
     * This method will delete the maximum element of the tree
     */
    public void deleteMax() {
        if (isEmpty()) {
            return;
        }
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null)
            return x.left;
        x.right = deleteMax(x.right);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     *this method will delete the min element in the tree if it is not empty
     */
    public void deleteMin() {
        if (isEmpty()) {
            return;
        }
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null)
            return x.right;
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }



    /**
     * this is ahelper function which will return the size of the tree
     * @return
     */
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null)
            return 0;
        else
            return x.size;
    }
}
