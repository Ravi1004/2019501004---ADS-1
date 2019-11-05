
public class Solution {
	private MyNode head;
	private MyNode tail;
	private int size;

	public Solution() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
/**.
 *
 * @param a
 * @param b
 * @return
 */
	public static String Josephus(final int a, final int b) {
		Solution sol = new Solution();
		sol.Order(a - 1);
		String removed = sol.remove(b);
		return removed;
	}
/**.
 *
 * @param a
 */
	private void Order(final int a) {
		for (int i = a; i >= 0; i--) {
			this.insert(String.valueOf(i));
		}
	}
/***.
 *
 * @param data
 */
	public void insert(final String data) {

		MyNode firstNode = new MyNode(data);
		size++;
		if (head == null) {
			head = firstNode;
			firstNode.next = head;
			tail = firstNode;
		} else {
			MyNode temp = tail;
			firstNode.next = head;
			head = firstNode;
			temp.next = head;
		}
	}

	public String remove(final int pos) {
		int index = 0;
		MyNode temp = head;
		MyNode prev = head;
		String removed = "";
		while (temp.next != temp) {
			int count = 1;
			while (count != pos) {
				prev = temp;
				temp = temp.next;
				count++;
			}
			removed = removed + temp.data + " ";
			prev.next = temp.next;
			temp = prev.next;
		}
		removed = removed + temp.data;
		return removed;
	}
}