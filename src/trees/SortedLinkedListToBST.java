package trees;

public class SortedLinkedListToBST {
	static LNode head;
	
	class LNode {
		int data;
		LNode prev, next;
		LNode(int data) {
			this.data = data;
			prev = next = null;
		}
	}
	
	class TNode {
		int data;
		TNode left, right;
		TNode(int data) {
			this.data = data;
			left = right = null;
		}
	}
	
	int countNodes(LNode head) {
		int cnt = 0;
		LNode tmp = head;
		while(tmp != null) {
			tmp = tmp.next;
			cnt++;
		}
		return cnt;
	}
	
	TNode listToBST(int n) {
		if (n <= 0) {
			return null;
		}
		TNode left = listToBST(n/2);
		TNode root = new TNode(head.data);
		head = head.next;
		root.left = left;
		root.right = listToBST(n-n/2 -1);
		return root;
	}
	
	void insertLNode(int data) {
		LNode tmp = new LNode(data);
		tmp.prev = null;
		tmp.next = head;
		if (head != null) {
			head.prev = tmp;
		}
		head = tmp;
	}
	
	void printLNodeList() {
		LNode tmp = head;
		
		while(tmp != null) {
			System.out.print(tmp.data + " ");
			tmp = tmp.next;
		}
		System.out.println();
	}
	
	void printInorderTree(TNode root) {
		if (root != null) {
			printInorderTree(root.left);
			System.out.print(root.data + " ");
			printInorderTree(root.right);
		}
	}
	
	public static void main(String[] args) {
		SortedLinkedListToBST lList = new SortedLinkedListToBST();
		lList.insertLNode(5);
		lList.insertLNode(4);
		lList.insertLNode(3);
		lList.insertLNode(2);
		lList.insertLNode(1);
		
		lList.printLNodeList();
		
		int n = lList.countNodes(head);
		TNode root = lList.listToBST(n);
		lList.printInorderTree(root);
		System.out.println();
	}

}
