package trees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TreeImpl {

	public static void main(String[] args) {
		TreeImpl tree = new TreeImpl();
		//TreeNode root = createTree(tree);
		TreeNode root = tree.insertTreeNode(null, 5);
		root = tree.insertTreeNode(root, 3);
		root = tree.insertTreeNode(root, 7);
		root = tree.insertTreeNode(root, 1);
		root = tree.insertTreeNode(root, 2);
		root = tree.insertTreeNode(root, 6);
		root = tree.insertTreeNode(root, 8);
		root = tree.insertTreeNode(root, 10);
		root = tree.insertTreeNode(root, 12);
		
		System.out.println("BFS:");
		tree.bfs(root);
		// Preorder
		System.out.println("DFS - preorder:");
		tree.dfs_preorder(root);
		System.out.println("DFS_iter - preorder:");
		tree.dfs_preorder_iter(root);
		// Inorder
		System.out.println("DFS - inorder:");
		tree.dfs_inorder(root);
		System.out.println("DFS_iter - inorder:");
		tree.dfs_inorder_iter(root);
		// Postorder
		System.out.println("DFS - postorder:");
		tree.dfs_postorder(root);
		
		try {
			root = tree.deleteTreeNode(root, 6);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		// Inorder - after node deletion
		System.out.println("DFS - inorder after node deletion:");
		tree.dfs_inorder(root);
		
		try {
			root = tree.deleteTreeNode(root, 4);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		// BST check
		System.out.println("Tree is a BST tree : " + tree.bstCheck(root));
		
		// Level Order Traversal with level wise display
		tree.levelOrderTraversal(root);
		
		// Height of the tree
		System.out.println("Height of the tree: " + tree.height(root));
		
		// Tree nodes count
		System.out.println("Total no. of tree nodes: " + tree.countTreeNodes(root));
		
		// Leaf tree nodes count
		System.out.println("Total no. of leaf tree nodes: " + tree.countLeafTreeNodes(root));
	}
	
	public static TreeNode createTree(TreeImpl tree) {
		TreeNode root = tree.new TreeNode(1);
		TreeNode a = tree.new TreeNode(2);
		TreeNode b = tree.new TreeNode(3);
		TreeNode c = tree.new TreeNode(4);
		TreeNode d = tree.new TreeNode(5);
		
		root.left = a;
		root.right = b;
		a.left = c;
		a.right = d;
		return root;
	}
	
	public TreeNode insertTreeNode(TreeNode root, int data) {
		TreeNode node = new TreeNode(data);
		if (root == null) {
			return node;
		}
		TreeNode prev = null;
		TreeNode cur = root;
		while (cur != null) {
			if (data < cur.data) {
				prev = cur;
				cur = cur.left;
			} else {
				prev = cur;
				cur = cur.right;
			}
		}
		if (data < prev.data) {
			prev.left = node;
		} else {
			prev.right = node;
		}
		return root;
	}
	
	public TreeNode deleteTreeNode(TreeNode root, int data) throws Exception{
		if (root == null) {
			return root;
		}
		TreeNode prev = null;
		TreeNode cur = root;
		
		while(cur != null && cur.data != data) {
			if (data < cur.data) {
				prev = cur;
				cur = cur.left;
			} else {
				prev = cur;
				cur = cur.right;
			}
		}
		
		TreeNode child = null;
		if (cur == null) {
			throw new Exception("Data not found.");
		} else {
			if (cur.left == null && cur.right == null) {
				child = null;
			} else if (cur.left == null) {
				child = cur.right;
			} else if (cur.right == null) {
				child = cur.left;
			} else {
				TreeNode succ = cur.right;
				while (succ.left != null) {
					succ = succ.left;
				}
				succ.left = cur.left;
				child = cur.right;
			}
		}
		
		if (prev == null) {
			return child;
		}
		if (data < prev.data) {
			prev.left = child;
		} else {
			prev.right = child;
		}
		return root;
	}
	
	public void bfs(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		while(!queue.isEmpty()) {
			TreeNode cur = queue.remove();
			System.out.println(cur.data);
			if (cur.left != null) {
				queue.add(cur.left);
			}
			if (cur.right != null) {
				queue.add(cur.right);
			}
		}	
	}
	
	public void dfs_preorder(TreeNode root) {
		if (root != null) {
			System.out.println(root.data);
			dfs_preorder(root.left);
			dfs_preorder(root.right);
		}	
	}
	
	public void dfs_inorder(TreeNode root) {
		if (root != null) {
			dfs_inorder(root.left);
			System.out.println(root.data);
			dfs_inorder(root.right);
		}	
	}
	
	public void dfs_postorder(TreeNode root) {
		if (root != null) {
			dfs_postorder(root.left);
			dfs_postorder(root.right);
			System.out.println(root.data);
		}	
	}
	
	public void dfs_preorder_iter(TreeNode root) {
		if (root == null) {
			return;
		}
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);
		while(!stack.isEmpty()) {
			TreeNode node = stack.pop();
			System.out.println(node.data);		
			if (node.right != null) {
				stack.push(node.right);
			}
			if (node.left != null) {
				stack.push(node.left);
			}	
		}
	}
	
	public void dfs_inorder_iter(TreeNode root) {
		if (root == null) {
			return;
		}
		Stack<TreeNode> stack = new Stack<TreeNode>();
		boolean done = false;
		TreeNode cur = root;
		while(!done) {
			if(cur != null) {
				stack.push(cur);
				cur = cur.left;
			} else {
				if (!stack.isEmpty()) {
					cur = stack.pop();
					System.out.println(cur.data);
					cur = cur.right;
				} else {
					done = true;
				}
			}
		}
	}
	
	public boolean bstCheck(TreeNode root) {
		if (root != null) {
			bstCheck(root.left);
			if (prev != null && root.data < prev.data) {
				return false;
			}
			prev = root;
			bstCheck(root.right);
		}
		return true;
	}
	
	public void levelOrderTraversal(TreeNode root) {
		if (root == null) {
			return;
		}
		int curLevel = 0;
		int nxtLevel = 0;
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		curLevel++;
		
		while(!queue.isEmpty()) {
			TreeNode ele = queue.remove();
			curLevel--;
			System.out.print(ele.data + " ");
			if (ele.left != null) {
				queue.add(ele.left);
				nxtLevel++;
			}
			if (ele.right != null) {
				queue.add(ele.right);
				nxtLevel++;
			}
			if (curLevel == 0) {
				System.out.println();
				curLevel = nxtLevel;
				nxtLevel = 0;
			}
		}	
	}
	
	public int height(TreeNode root) {
		if (root == null) {
			return 0;
		} else {
			return 1 + max(height(root.left), height(root.right));
		}
	}
	
	public int countTreeNodes(TreeNode root) {
		if (root != null) {
			countTreeNodes(root.left);
			cntNodes++;
			countTreeNodes(root.right);
		}
		return cntNodes;
	}
	
	public int countLeafTreeNodes(TreeNode root) {
		if (root != null) {
			countLeafTreeNodes(root.left);
			if (root.left == null && root.right == null) {
			cntLeaf++;
			}
			countLeafTreeNodes(root.right);
		}
		return cntLeaf;
	}
	
	private int max(int a, int b) {
		if (a>b) {
			return a;
		} else {
			return b;
		}
	}
	
	private class TreeNode {
		int data;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int data) {
			this.data = data;
			this.left = this.right = null;
		}
	}
	
	private TreeNode prev;
	
	private static int cntNodes;
	
	private static int cntLeaf;

}
