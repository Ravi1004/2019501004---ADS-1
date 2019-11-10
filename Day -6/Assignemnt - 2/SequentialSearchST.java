
/**
 * @author Ravi
 */

import java.util.*;

/**
 * This is a class Sequential search which extends comparable to compare
 * the keys with values
 * @param <Key> key will be passed as parameter
 * @param <Value> value will be passed as parameter
 */
public class SequentialSearchST<Key extends Comparable<Key>, Value> {
    private Node head;
    private class Node {
        private Key key;
        private Value value;
        private Node next;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * tjos is put method which is used to insert the element
     * which is key and its value
     *
     * @param key unique key
     * @param value value associated with the key
     */
    public void put(Key key, Value value) {
        Node node = new Node(key, value);
        if (head == null) {
            head = node;
            return;
        }
        Node current = head;
        while (current != null) {
            if (current.key.compareTo(key) == 0) {
                current.value = value;
                return;
            }
            current = current.next;
        }
        Node oldHead = head;
        head = new Node(key, value);
        head.next = oldHead;
    }

    /**
     *This will exchange the keys value
     *
     * @param current
     * @return
     */
    private Value exchange(Node current) {
        Node temp = current.next;
        Value value = temp.value;
        current.next = current.next.next;
        Node node = head;
        while (node.next != null) {
            node = node.next;
        }
        node.next = temp;
        temp.next = null;
        return value;
    }

    /**
     *this method is get, which is used when to get the value
     *parameter will be key
     * @param key
     * @return returns the value associated with the key
     */
    public Value get(Key key) {
        if (key == null) {
            return null;
        }
        if (head.key.compareTo(key) == 0) {
            return exchange(head);
        }
        Node current = head;
        while (current != null) {
            if (current.next.key.compareTo(key) == 0) {
                return exchange(current);
            }
            current = current.next;
        }
        return null;
    }

    /**
     *this is a helper function which is used to itterate in the queue
     * @return returns the queue
     */
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        Node current = head;
        while (current != null) {
            queue.enqueue(current.key);
            current = current.next;
        }
        return queue;
    }
}
