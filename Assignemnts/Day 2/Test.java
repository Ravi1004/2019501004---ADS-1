/**
 * .
 *
 * @author Ravi
 * this is test class in which main method is there and enqueue,
 *         dequeue and printAllElements methods;
 */
class Test {

    public static void main(String[] args) {
        circularList obj = new circularList();
        System.out.println("Circular linked list");
        obj.enqueue(10);
        obj.enqueue(20);
        obj.enqueue(30);
        obj.enqueue(40);
        int ob = obj.dequeue();
        System.out.println("deleted value " + ob);
        obj.printAllElements();
    }
}