package practise;

public class MergeTwoLists {

	public static void main(String[] args) {
		MergeTwoLists mer = new MergeTwoLists();
		// TODO Auto-generated method stub
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(3);
		l1.next.next = new ListNode(8);
		
		ListNode l2 = new ListNode(1);
		l2.next = new ListNode(5);
		l2.next.next = new ListNode(9);
		System.out.println(mer.mergeTwoLists(l1, l2));
	}
	private ListNode mergeTwoLists(ListNode l1, ListNode l2){
		if(l1 == null) return l2;
		if(l2 == null) return l1;
		if(l1.val < l2.val){
			l1.next = mergeTwoLists(l1.next, l2);
			return l1;
		} else{
			l2.next = mergeTwoLists(l1, l2.next);
			return l2;
		}
}
}
