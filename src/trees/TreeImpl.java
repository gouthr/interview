package trees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/* Binary search tree time complexity
Insertion, deletion and searching in a binary search tree are:
	•	O(N) in the worst case; (as the BST could be all nodes to the right of the root node)
	•	O(log(N)) in the average case.
Complexity of inserting n numbers into a binary search tree
	•	It could be O(n^2) even if the tree is balanced.
	•	Suppose you're adding a sorted list of numbers, all larger than the largest number in the tree. In that case, all numbers will be added to the right child of the rightmost leaf in the tree, Hence O(n^2).
	•	In general, if you'd like to keep the insertion and retrieval at O(nlogn), you need to use Self Balancing trees.

Time efficiency of building a heap is O(n)
Time efficiency to heapify k elements is O(k*logn)*/

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
		
		// Spiral tree traversal
		System.out.println("Spiral tree traversal:");
		tree.spiralTreeTraversal(root);
		
		// LCA of 2 nodes in a tree
		System.out.println();
		System.out.println("LCA of 2 nodes in a tree: " + tree.lca(root, root.left.left, root.left.left.right).data);
	
		// Horizontal level tree sum
		System.out.println("Horizontal level tree sum:");
		int index = 0;
		int[] hsum = new int[10];
		tree.horizontalLevelTreeSum(root, hsum, index);
		for (int i=0; i<hsum.length; i++) {
			System.out.println(hsum[i] + " ");
		}
		
		// Max Span of a tree
		tree.findTreeSpan(root, 0, 0);
		int maxSpan = Math.abs(tree.lspan) + Math.abs(tree.rspan) + 1;
		System.out.println("Left span: " + tree.lspan + "; Right span: " + tree.rspan);
		
		// Vertical level tree sum
		System.out.println("Vertical level tree sum:");
		int midVal = Math.abs(tree.lspan);
		int[] vsum = new int[maxSpan];
		tree.verticalLevelTreeSum(root, vsum, midVal);
		for (int i=0; i<maxSpan; i++) {
			System.out.println(vsum[i] + " ");
		}
		
		// Max path sum of tree from the root to any leaf node
		tree.maxPathTreeSum(root, 0);
		System.out.println("Max path sum of a tree: " + tree.maxPathSum);
		
		// Print max path route from root to a leaf node
		tree.pathToMaxPathTree(root, tree.maxPathLeafNode);
		
		// Balanced tree check
		System.out.println();
		System.out.println("Balanced tree check: " + tree.balancedTreeCheck(root));
		
		// Serialize a tree
		System.out.println();
		System.out.println("Serialized tree: ");
		tree.serializeTree(root);
		System.out.println();
		
		// Deserialize a tree
		String[] treeRep = "5 3 1 # 2 # # # 7 # 8 # 10 # 12 # #".split(" ");
		TreeNode newRoot = tree.new TreeNode(); 
		newRoot = tree.deserializeTree(newRoot, treeRep);
		System.out.println("Deserialized tree: ");
		tree.dfs_preorder(newRoot);
		System.out.println();
		
		// Create a tree from preorder and inorder traversals
		System.out.println("Inorder before tree creation:");
		tree.dfs_inorder(root);
		System.out.println();
		System.out.println("Preorder before tree creation:");
		tree.dfs_preorder(root);
		System.out.println();
		
		int[] preorder = new int[]{5, 3, 1, 2, 7, 8, 10, 12};
		int[] inorder = new int[]{1, 2, 3, 5, 7, 8, 10, 12};
		TreeNode newRoot2 = tree.createTreeFromPreOrderInOrder(preorder, inorder, 0, inorder.length-1);
		
		System.out.println("Inorder after tree creation:");
		tree.dfs_inorder(newRoot2);
		System.out.println();
		System.out.println("Preorder after tree creation:");
		tree.dfs_preorder(newRoot2);
		System.out.println();
		
		// Mirror check
		System.out.println("Mirror check:" + tree.mirrorCheck(root.left, root.right));
		System.out.println();
		
		// Deepest leaf node level
		System.out.println("Deepest leaf node level: " + tree.deepestLeafNode(root, 0));
		System.out.println();
		
		// Return mirror of the tree
		System.out.println("Mirror of a given tree:");
		System.out.println("Given Tree:");
		tree.levelOrderTraversal(root);
		System.out.println("Mirror tree:");
		TreeNode newRoot1 = tree.mirrorTree(root);
		tree.levelOrderTraversal(newRoot1);
		System.out.println("Mirror tree in place:");
		tree.mirrorTreeInPlace(root);
		tree.levelOrderTraversal(root);
		System.out.println("Back to normal:");
		tree.mirrorTreeInPlace(root);
		tree.levelOrderTraversal(root);
		
		tree.kLargestInBST(root, 4);

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
	
	public void spiralTreeTraversal(TreeNode root) {
		if (root == null) {
			return;
		}
		Stack<TreeNode> stack1 = new Stack<TreeNode>();
		Stack<TreeNode> stack2 = new Stack<TreeNode>();
		
		stack1.push(root);
		while(!stack1.isEmpty() || !stack2.isEmpty()) {
			while(!stack1.isEmpty()) {
				TreeNode ele = stack1.pop();
				System.out.print(ele.data + " ");
				if (ele.right != null) {
					stack2.push(ele.right);
				}
				if (ele.left != null) {
					stack2.push(ele.left);
				}
			}
			while(!stack2.isEmpty()) {
				TreeNode ele = stack2.pop();
				System.out.print(ele.data + " ");
				if (ele.left != null) {
					stack1.push(ele.left);
				}
				if (ele.right != null) {
					stack1.push(ele.right);
				}
			}
		}
	}
	
	public TreeNode lca(TreeNode root, TreeNode a, TreeNode b) {
		if (root == null) {
			return null;
		}
		if (root.left == a || root.right == a || root.left == b || root.right == b) {
			return root;
		} else {
			TreeNode l = lca(root.left, a, b);
			TreeNode r = lca(root.right, a, b);
			if (l != null && r != null) {
				return root;
			} else if (l != null) {
				return l;
			} else {
				return r;
			}
		}
	}
	
	public void horizontalLevelTreeSum(TreeNode root, int[] sum, int index) {
		if (root != null) {
			sum[index]+=root.data;
			horizontalLevelTreeSum(root.left, sum, index+1);
			horizontalLevelTreeSum(root.right, sum, index+1);
		}
	}
	
	public void findTreeSpan(TreeNode root, int lspan, int rspan) {
		if (root != null) {
			if (lspan < this.lspan) {
				this.lspan = lspan;
			}
			if (rspan > this.rspan) {
				this.rspan = rspan;
			}
			findTreeSpan(root.left, lspan-1, rspan);
			findTreeSpan(root.right, lspan, rspan+1);
		}
	}
	
	public void verticalLevelTreeSum(TreeNode root, int[] sum, int midval) {
		if (root != null) {
			sum[midval] += root.data;
			verticalLevelTreeSum(root.left, sum, midval-1);
			verticalLevelTreeSum(root.right, sum, midval+1);
		}
	}
	
	public void maxPathTreeSum(TreeNode root, int sum) {
		if (root != null) {
			sum += root.data;
			if (root.left == null && root.right == null) {
				if (maxPathSum < sum) {
					maxPathSum = sum;
					maxPathLeafNode = root;
				}
			}
			maxPathTreeSum(root.left, sum);
			maxPathTreeSum(root.right, sum);
		}
	}
	
	public boolean pathToMaxPathTree(TreeNode root, TreeNode leaf) {
		if (root == null) {
			return false;
		}
		if (root == leaf || pathToMaxPathTree(root.left, leaf) || pathToMaxPathTree(root.right, leaf)) {
			System.out.print(root.data + "->");
			return true;
		}
		
		return false;
	}
	
	/**
	 * Find distance between 2 nodes in a binary tree.
	 */
	public int distanceBwNodes(TreeNode root, TreeNode a, TreeNode b) {
		TreeNode lca = lca(root, a, b);
		
		int dist1 = findLevel(lca, a, 0);
		
		int dist2 = findLevel(lca, b, 0);
		
		return dist1 + dist2;
		
	}
	
	private int findLevel(TreeNode root, TreeNode a, int level) {
		if (root == null) {
			return -1;
		}
		if (root == a) {
			return level;
		}
		int left = findLevel(root.left, a, level+1);
		if (left == -1) {
			int right = findLevel(root.right, a, level+1);
			return right;
		}
		return left;
	}

	/**
	 * An empty tree is height-balanced. A non-empty binary tree T is balanced if:
	 * 1) Left subtree of T is balanced
	 * 2) Right subtree of T is balanced
	 * 3) The difference between heights of left subtree and right subtree is not more than 1.
	 */
	public boolean balancedTreeCheck(TreeNode root) {
		if (root == null) {
			return true;
		}
		
		int lh = this.height(root.left);
		int rh = this.height(root.right);
		
		if (Math.abs(lh - rh) <= 1 && balancedTreeCheck(root.left) && balancedTreeCheck(root.right)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void serializeTree(TreeNode root) {
		if (root != null) {
			System.out.print(root.data + " ");
			serializeTree(root.left);
			serializeTree(root.right);
		} else {
			System.out.print("#" + " ");
		}
	}
	
	public TreeNode deserializeTree(TreeNode root, String[] treeRep) {
		if (index == treeRep.length || treeRep[index].equals("#")) {
			return null;
		}
		
		root = new TreeNode(Integer.parseInt(treeRep[index]));
		index++;
		root.left = deserializeTree(root.left, treeRep);
		index++;
		root.right = deserializeTree(root.right, treeRep);
		
		return root;
	}
	
	public TreeNode createTreeFromPreOrderInOrder(int[] preorder, int[] inorder, int startInorder, int endInorder) {
		if (startInorder > endInorder) {
			return null;
		}
		TreeNode root = new TreeNode(preorder[preIndex++]);	
		if (startInorder == endInorder) {
			return root;
		}	
		int index = findNodeInInOrder(inorder, root.data, startInorder, endInorder);
		root.left = createTreeFromPreOrderInOrder(preorder, inorder, startInorder, index-1);
		root.right = createTreeFromPreOrderInOrder(preorder, inorder, index+1, endInorder);
		
		return root;
	}
	
	public boolean mirrorCheck(TreeNode root1, TreeNode root2) {
		if (root1 == null || root2 == null) {
			return (root1 == null && root2 == null);
		}
		
		if (root1.data == root2.data && mirrorCheck(root1.left, root2.right) && mirrorCheck(root1.right, root2.left)) {
			return true;
		} else {
			return false;
		}		
	}
	
	public int deepestLeafNode(TreeNode root, int level) {
		if (root == null) {
			return level;
		}
		if (root.left == null && root.right == null) {
			return level;
		}
		return max(deepestLeafNode(root.left, level+1), deepestLeafNode(root.right, level+1));
	}
	
	public TreeNode mirrorTree(TreeNode root) {
		if (root == null) {
			return null;
		}
		TreeNode newRoot = new TreeNode(root.data);
		newRoot.left = mirrorTree(root.right);
		newRoot.right = mirrorTree(root.left);
		
		return newRoot;
	}
	
	public void mirrorTreeInPlace(TreeNode root) {
		if (root == null) {
			return;
		}
		mirrorTreeInPlace(root.left);
		mirrorTreeInPlace(root.right);
		TreeNode tmp = root.left;
		root.left = root.right;
		root.right = tmp;
	}
	
	/*
	 * Given a BST, find the kth largest node.
	 */
	public void kLargestInBST(TreeNode root, int k) {
		if (root == null || i>=k) {
			return;
		}
		kLargestInBST(root.right, k);
		i++;
		if (i == k) {
			System.out.println(k + "th largest node: " + root.data);
		}
		kLargestInBST(root.left, k);
	}
	
	private int findNodeInInOrder(int[] inorder, int data, int st, int end) {
		for (int i=st; i<=end; i++) {
			if (inorder[i] == data) {
				return i;
			}
		}
		return -1;
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
		
		public TreeNode() {
			
		}
	}
	
	private TreeNode prev;
	
	private static int cntNodes;
	
	private static int cntLeaf;
	
	private int lspan;
	
	private int rspan;

	private int maxPathSum;
	
	private TreeNode maxPathLeafNode;
	
	private int index;
	
	private int preIndex;
	
	private int i;
}
