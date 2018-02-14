package trees;

public class SortedLinkedListToBST {
	private static LNode head;
	
	private static TNode prev;
	
	private TNode tHead;
	
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
	
	/**
	 * Convert a given binary tree to doubly linked list in place.
	 */
	public void treeToDLL(TNode root) {
		if (root == null) {
			return;
		}
		treeToDLL(root.left);
		
		if (prev == null) {
			tHead = root;
		} else {
			root.left = prev;
			prev.right = root;
		}
		prev = root;
		
		treeToDLL(root.right);
	}
	
	/**
	 * Given a sorted array, convert to balanced BST.
	 */
	public TNode arrToTree(int[] arr, int st, int end) {
		
		if (st > end) {
			return null;
		}
		int mid = (st + end)/2;
		TNode root = new TNode(arr[mid]);
		root.left = arrToTree(arr, st, mid-1);
		root.right = arrToTree(arr, mid+1, end);
		
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
	
	/**
	 * Print the tree converted to DLL in place.
	 * @param head
	 */
	void printTNodeList(TNode head) {
		TNode tmp = head;
		
		while(tmp != null) {
			System.out.print(tmp.data + " ");
			tmp = tmp.right;
		}
		System.out.println();
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
		
		System.out.println("Input linked list: ");
		lList.printLNodeList();
		
		int n = lList.countNodes(head);
		TNode root = lList.listToBST(n);
		System.out.println("Convert sorted linked list to BST (inorder traversal display): ");
		lList.printInorderTree(root);
		System.out.println();
		
		lList.treeToDLL(root);
		System.out.println("Convert tree to doubly linked list in place: ");
		lList.printTNodeList(lList.tHead);
		
		int arr[] = {1, 2, 3, 4, 5};
		TNode newRoot = lList.arrToTree(arr, 0, arr.length -1);
		System.out.println("Sorted array to balanced BST (inorder traversal display): ");
		lList.printInorderTree(newRoot);
	}

}
