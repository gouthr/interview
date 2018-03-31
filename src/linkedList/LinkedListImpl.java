package linkedList;

public class LinkedListImpl {
	private class Node {
		int data;
		Node next;
		
		public Node(int data) {
			this.data = data;
			this.next = null;
		}
	}
	
	/** 
	 * Given a head node, reverse a singly linked list and return the new head.
	 * 
	 * @param head
	 * @return
	 */
	public Node reverseList(Node head) {
		Node tmp = null;
		Node cur = null;
		
		while(head != null) {
			tmp = head;
			head = head.next;
			tmp.next = cur;
			cur = tmp;
		}
		return cur;
	}
	
	public Node insertNode(Node head, int data) {
		Node node = new Node(data);
		node.next = head;
		return node;
	}
	
	public void printList(Node head) {
		Node tmp = head;
		System.out.println("Nodes in the linked list: ");
		while (tmp != null) {
			System.out.print(tmp.data + " ");
			tmp = tmp.next;
		}
		System.out.println();
	}
	
	public Node mergeSortedLists(Node a, Node b) {
		Node res = null;
		
		if (a == null) {
			return b;
		}
		if (b == null) {
			return a;
		}
		
		if (a.data <= b.data) {
			res = a;
			res.next = mergeSortedLists(a.next, b);
		} else {
			res = b;
			res.next = mergeSortedLists(a, b.next);
		}	
		return res;
	}
	
	public Node middleOfList(Node head) {
		
		if (head == null || head.next == null) {
			return head;
		}
		
		Node fast = head.next;
		Node slow = head;
		
		while (fast != null) {
			fast = fast.next;
			if (fast != null) {
				fast = fast.next;
				slow = slow.next;
			}
		}		
		return slow;
	}
	
	public Node mergeSort(Node head) {
		if (head == null || head.next == null) {
			return head;
		}
		
		Node middleNode = middleOfList(head);
		Node middleNext = middleNode.next;
		middleNode.next = null;
		Node l = mergeSort(head);
		Node r = mergeSort(middleNext);
		
		Node res = mergeSortedLists(l, r);
		
		return res;
	}
	
	public static void main(String[] args) {
		LinkedListImpl list = new LinkedListImpl();
		// 2->3->20->5->10->15
		Node head = list.insertNode(null, 15);
		head = list.insertNode(head, 10);
		head = list.insertNode(head, 5);
		head = list.insertNode(head, 20);
		head = list.insertNode(head, 3);
		head = list.insertNode(head, 2);
		
		list.printList(head);
		
		head = list.reverseList(head);
		
		list.printList(head);
		
		head = list.mergeSort(head);
		
		list.printList(head);
	}
}
