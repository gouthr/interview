package linkedList;

public class RandomPointerList {
	
	public static void main(String args[]) {
		Node one = new Node(1);
		Node two = new Node(2);
		Node three = new Node(3);
		Node four = new Node(4);
		Node five = new Node(5);
		one.next = two;
		two.next = three;
		three.next = four;
		four.next = five;
		one.rand = three;
		two.rand = four;
		three.rand = five;
		four.rand = one;
		five.rand = four;
		head = one;
		
		printList(head);
		Node newHead = copyList();
		printList(newHead);
	}
	
	public static Node copyList() {
		Node tmp = head;
		while(tmp != null) {
			Node node = new Node(tmp.data);
			node.next = tmp.next;
			Node next = tmp.next;
			tmp.next = node;
			tmp = next;
		}

		printList(head);
		Node copyHead = head.next;
		tmp = head;
		while(tmp != null) {
			tmp.next.rand = tmp.rand.next;
			tmp = tmp.next.next;
		}
		
		tmp = head;
		Node tmpCopyHead = copyHead;
		while(tmp != null) {
			tmp.next = tmp.next.next;
			tmp = tmp.next;
			if (tmpCopyHead.next != null) {
				tmpCopyHead.next = tmpCopyHead.next.next;
				tmpCopyHead = tmpCopyHead.next;
			}
		}
		
		return copyHead;	
	}
	
	public static void printList(Node start) {
		Node tmp = start;
		
		while(tmp != null) {
			System.out.print(tmp.data + " ");
			tmp = tmp.next;
		}
		System.out.println();
	}

	private static Node head;
}
