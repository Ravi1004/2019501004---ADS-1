public class Solution {

	StackNode head = null;
	int size = 0;

	public static String isMatching(String str) {
		Solution sol = new Solution();
		String[] temp = str.split("");
		for (int i = 0; i < temp.length; i++) {
			sol.push(temp[i]);
		}

		if (sol.size == 0) {
			return "YES";
		}
		return "NO";
	}

	public void push(final String data) {
		if (size == 0) {
			StackNode firstNode = new StackNode(data);
			head = firstNode;
			size++;
			return;
		}

		if (data.equals(")")) {
			if (head.data.equals("(")) {
				size--;
				head = head.next;
				return;
			}
		}

		if (data.equals("]")) {
			if (head.data.equals("[")) {
				head = head.next;
				size--;
				return;
			}
		}

		if (data.equals("}")) {
			if (head.data.equals("{")) {
				head = head.next;
				size--;
				return;
			}
		}
		size++;
		StackNode firstNode = new StackNode(data);
		firstNode.next = head;
		head = firstNode;
	}
}