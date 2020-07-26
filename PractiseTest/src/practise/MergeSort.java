package practise;

/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode(int x) { val = x; } }
 */
public class MergeSort {
	public static void main(String[] args) {
		ListNode l1 = new ListNode(4);
		l1.next = new ListNode(2);
		l1.next.next = new ListNode(1);
		l1.next.next.next = new ListNode(3);
		System.out.println(sortList(l1));
	}

	 //merge two sorted list, return result head
    private static ListNode merge(ListNode h1, ListNode h2){
        if(h1 == null){
            return h2;
        }
        if(h2 == null){
            return h1;
        }
        
        if(h1.val < h2.val){
            h1.next = merge(h1.next, h2);
            return h1;
        }
        else{
            h2.next = merge(h1, h2.next);
            return h2;
        }
        
    }
    
    private static ListNode sortList(ListNode head) {
        //bottom case
        if(head == null || head.next == null){
            return head;
        }
                
        //slow move 1 step every time, fast move 2 step every time, prev record node before slow
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = head;
        
        while(fast != null && fast.next != null){
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        //change prev next to null, make two sub list(head to prev, slow to fast)
        prev.next = null;
        
        //handle those two sub list
        ListNode h1 = sortList(head);
        ListNode h2 = sortList(slow);
        
        return merge(h1, h2);
        
    }
    
}

