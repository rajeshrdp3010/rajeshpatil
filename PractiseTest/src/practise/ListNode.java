package practise;

public class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(val);
		ListNode p = this.next;
		while (p != null) {
			str.append(" , " +p.val);
			p = p.next;
			
		}
		return str.toString();
	}
}
