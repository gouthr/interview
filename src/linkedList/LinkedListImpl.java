package linkedList;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

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
	
	private Node partition(Node[] list, int st, int end) {		
		if (st == end) {
			return list[st];
		}
		if (st < end) {
			int mid = (st + end)/2;
			Node a = partition(list, st, mid);
			Node b = partition(list, mid+1, end);
			return mergeSortedLists(a, b);
		}
		return null;
		
	}
	
	/**
	 * Merge k sorted lists into a single list.
	 * 
	 * @param list
	 * @return
	 */
	public Node mergekSortedLists(Node[] list) {
		int n = list.length;
		return partition(list, 0, n-1);
	}
	
	/**
	 * Optimized merge k lists.
	 * 
	 * @param lists
	 * @return
	 */
    public Node mergeKLists(List<Node> lists) {
        if (lists==null||lists.size()==0) return null;
        
        PriorityQueue<Node> queue= new PriorityQueue<Node>(lists.size(),new Comparator<Node>(){
            @Override
            public int compare(Node o1,Node o2){
                if (o1.data<o2.data)
                    return -1;
                else if (o1.data==o2.data)
                    return 0;
                else 
                    return 1;
            }
        });
        
        Node dummy = new Node(0);
        Node tail=dummy;
        
        for (Node node:lists)
            if (node!=null)
                queue.add(node);
            
        while (!queue.isEmpty()){
            tail.next=queue.poll();
            tail=tail.next;
            
            if (tail.next!=null)
                queue.add(tail.next);
        }
        return dummy.next;
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
		
		/** [
		  1->4->5,
		  1->3->4,
		  2->6
		]*/
		Node a = list.insertNode(null, 5);
		a = list.insertNode(a, 4);
		a = list.insertNode(a, 1);
		
		Node b = list.insertNode(null, 4);
		b = list.insertNode(b, 3);
		b = list.insertNode(b, 1);
		
		Node c = list.insertNode(null, 6);
		c = list.insertNode(c, 2);
		
		Node[] arr = {a, b, c};
		
		Node merged = list.mergekSortedLists(arr);
		
		list.printList(merged);
		
	}
}
