/**
 * @author Ravi
 */
class circularList {
    /**
     * Node class and its constructor
     */
    class Node {

        Node next;
        int value;

        Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    Node last;

    circularList() {
        Node last = null;
    }

    /**
     * This method is called to add elements into the linked list
     *
     * @param value to be passed into the linked list
     */
    public void enqueue(int value) {
        if (last == null) {
            last = new Node(value);
            last.next = last;
        } else {
            Node head = last.next;
            last.next = new Node(value);
            last = last.next;
            last.next = head;
        }
    }

    /**
     * This method will delete the value from the linked list
     *
     * @return returns the deleted element
     */
    public int dequeue() {
        if (last == null) {
            return -1;
        } else if (last.next == last) {
            int val = last.value;
            last = null;
            return val;
        } else if (last.next.next == last) {
            int val = last.next.value;
            last.next = last;
            return val;
        } else {
            int val = last.next.value;
            last.next = last.next.next;
            return val;
        }

    }

    /**
     * This method is for printing the remaining elements in lined list
     */
    public void printAllElements() {
        Node current = last.next;
        System.out.println("Remaining Elements");
        while (current != last) {
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.print(last.value);

    }

}