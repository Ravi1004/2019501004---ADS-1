import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.xml.soap.Node;

/**.
 * @author Ravi
 *
 * This is a class for deque
 * @param <Item>
 */
public class Deque<Item> implements Iterable<Item> {
    /**.
     * node first
     */
    private Node<Item> first;
    /**.
     * Node last
     */
    private Node<Item> last;
    /**.
     * size n
     */
    private int n;

    /**.
     * Node class
     *
     * @param <Item> item generic type
     */
    private static class Node<Item> {
        /**.
         * item
         */
        private Item item;
        /**.
         * Node next
         */
        private Node<Item> next;
        /**.
         * Node previous
         */
        private Node<Item> previous;
    }

    /**.
     * This is the constructor for dequeue
     *
     */
    Deque() {
        first = null;
        last  = null;
        n = 0;
    }

    /**.
     * This will return true if empty
     * false if not empty
     *
     * @return true if empty else false
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**.
     * This will return size n
     *
     * @return n
     */
    public int size() {
        return n;
    }

    /**.
     * This will add the element in the first
     *
     *
     * @param item item will be inserted in the node
     */
    public void addFirst(final Item item) {
        if (n == 0) {
            Node nd = new Node();
            nd.item = item;
            first = nd;
            last = nd;
            n++;
        } else {
            Node temp = first;
            Node nd = new Node();
            nd.item = item;
            nd.next = temp;
            temp.previous = nd;
            first = nd;
            n++;
        }
    }

    /**.
     * This will add the element in the last of the element
     *
     * @param item item will be added at the last
     */
    public void addLast(final Item item) {
        if (n == 0) {
            Node nd = new Node();
            nd.item = item;
            last = nd;
            first = nd;
            n++;
        } else {
            Node temp = last;
            Node nd = new Node();
            nd.item = item;
            nd.previous = temp;
            temp.next = nd;
            last = nd;
            n++;
        }
    }

    /**.
     * This will remove the element from the first
     *
     * @return the element which is deleted
     */
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("QUeue Underflow");
        } else if (n == 1) {
            Item temp = first.item;
            first = null;
            last = null;
            n--;
            return temp;
        } else {
            Item item = first.item;
            first = first.next;
            first.previous = null;
            n--;
            return item;
        }
    }

    /**.
     * The element will be deleted from the last
     *
     * @return the element which is deleted
     */
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("QUeue Underflow");
        } else if (n == 1) {
            Item temp = first.item;
            first = null;
            last = null;
            n--;
            return temp;

        } else {
            Item item = last.item;
            last = last.previous;
            last.next = null;
            n--;
            return item;
        }

    }

    /**.
     * this is the iterator method
     *
     * @return the value
     */

    public Iterator<Item> iterator() {
        return new NodeIterator(first);
    }

    /**.
     * This is the iterator method
     *
     */
    private class NodeIterator implements Iterator<Item> {
        /**.
         * current node
         */
        private Node<Item> current;

        /**.
         * The first node will be set a current
         * @param first1 first
         */
        NodeIterator(final Node<Item> first1) {
            current = first1;
        }

        /**.
         * This is the hasNext method which will
         * return the item
         * @return current node
         */
        public boolean hasNext() {
            return current != null;
        }

        // public void remove() {
        //     throw new UnsupportedOperationException();
        // }
        /**.
         * This is the next method which will retun the item
         * and moves to the next item
         *
         * @return the item
         */
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    /**.
     * This is the main method
     *
     * @param args args
     */
    public static void main(String[] args) {
        // main method
    }
}

