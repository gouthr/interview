package trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

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
		//TreeNode root = tree.createTree();
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
		
		// Deepest leaf node level is one less than the height of the tree
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
		
		System.out.println("k(3) closest nodes to target (6): ");
		List<Integer> res = new ArrayList<Integer>();
		tree.kClosestNodes(root, 6, 3, res);
		System.out.println(res);
		
		tree.BTPaths(root);
	}
	
	public TreeNode createTree() {
		TreeNode root = new TreeNode(1);
		TreeNode a = new TreeNode(2);
		TreeNode b = new TreeNode(3);
		TreeNode c = new TreeNode(4);
		TreeNode d = new TreeNode(5);
		
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
	
        private TreeNode prev = null;
        public boolean isValidBST(TreeNode root) {
	    if (root != null)  {
	        if (!isValidBST(root.left)) {
		    return false;
	        }
	        if (prev != null && prev.data > root.data) {
		    return false;
	        }
	        prev = root;
	        return isValidBST(root.right);
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
			return 1 + Math.max(height(root.left), height(root.right));
		}
	}
	
	/**
	 * Given a binary tree, you need to compute the length of the diameter of the tree. 
	 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree. 
	 * This path may or may not pass through the root.

		Example:
		Given a binary tree
		          1
		         / \
		        2   3
		       / \     
		      4   5    
		Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
		
		Note: The length of path between two nodes is represented by the number of edges between them.
	 */
    int ans;
    public int diameterOfBinaryTree(TreeNode root) {
        ans = 1;
        depth(root);
        return ans - 1;
    }
    public int depth(TreeNode node) {
        if (node == null) return 0;
        int L = depth(node.left);
        int R = depth(node.right);
        ans = Math.max(ans, L+R+1);
        return Math.max(L, R) + 1;
    }
	
	public int countTreeNodes(TreeNode root) {
		if (root != null) {
			countTreeNodes(root.left);
			cntNodes++;
			countTreeNodes(root.right);
		}
		return cntNodes;
	}
	
	/**
	 * Diameter of an n-ary tree
	 * @param root
	 * @return
	 */
/*    private int max = 0;
    
    public int diameter(Node root) {
        getHeight(root);
        return max;
    }
    
    private int getHeight(Node root) {
        if (root == null) {
            return 0;
        }
        int max1 = 0;
        int max2 = 0;
        for (Node child : root.children) {
            int height = getHeight(child);
            if (height > max1) {
                max2 = max1;
                max1 = height;
            } else if (height > max2) {
                max2 = height;
            }
        }
        max = Math.max(max, max1+max2);
        return 1 + Math.max(max1, max2);
    }*/
	
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
	
    int getLeafCount(Node node)  
    { 
        if (node == null) 
            return 0; 
        if (node.left == null && node.right == null) 
            return 1; 
        else
            return getLeafCount(node.left) + getLeafCount(node.right); 
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
	
	    // Encodes a tree to a single string.
	    public String serialize(TreeNode root) {
		StringBuilder sb = new StringBuilder();
		serializeUtil(root, sb);
		return sb.toString();
	    }

	    private void serializeUtil(TreeNode root, StringBuilder sb) {
		if (root != null) {
		    sb.append(String.valueOf(root.val)).append(" ");
		    serializeUtil(root.left, sb);
		    serializeUtil(root.right, sb);
		} else {
		    sb.append("#").append(" ");
		}
	    }

	    // Decodes your encoded data to tree.
	    public TreeNode deserialize(String data) {
		String[] parts = data.split(" ");
		Queue<String> q = new LinkedList<>(Arrays.asList(parts));
		return deserializeUtil(q);
	    }

	    private TreeNode deserializeUtil(Queue<String> q) {
		String strVal = q.poll();
		if (strVal == null || strVal.equals("#")) {
		    return null;
		}
		TreeNode root = new TreeNode(Integer.parseInt(strVal));
		root.left = deserializeUtil(q);
		root.right = deserializeUtil(q);
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
	
	private int findNodeInInOrder(int[] inorder, int data, int st, int end) {
		for (int i=st; i<=end; i++) {
			if (inorder[i] == data) {
				return i;
			}
		}
		return -1;
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
		return Math.max(deepestLeafNode(root.left, level+1), deepestLeafNode(root.right, level+1));
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
	
	/**
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
	
	/**
	 * Given a target value, find k closest nodes.
	 * 
	 */
	public void kClosestNodes(TreeNode root, int target, int k, List<Integer> res) {
		if (root == null) {
			return;
		}
		kClosestNodes(root.left, target, k, res);
		if (res.size() < k) {
			res.add(root.data);
		} else {
			if (Math.abs(target - root.data) < Math.abs(target - res.get(0))) {
				res.remove(0);
				res.add(root.data);
			}
		}
		kClosestNodes(root.right, target, k, res);		
	}
	
	/**
	 * Convert a given binary tree to sum tree.
	 * 
	 * @param root
	 */
	public int convertToSumTree(TreeNode root) {
		if (root == null) {
			return 0;
		} 
		int oldVal = root.data;
		int left = convertToSumTree(root.left);
		int right = convertToSumTree(root.right);
		root.data = left + right;
		return root.data + oldVal;
	}
	
	/**
	 * Check if a given binary tree is a sum tree.
	 * 
	 * A binary tree is said to be a sum tree if: Every node is equal to the
	 * summation of its child nodes
	 * 
	 * @param root
	 * @return
	 */
	public int checkSumTree(TreeNode root) {
		if (root == null || (root.left == null && root.right == null)) {
			return 1;
		}
		int ln = sum(root.left);
		int rn = sum(root.right);
		if ((root.data == ln + rn) && checkSumTree(root.left) != 0 && checkSumTree(root.right) != 0) {
			return 1;
		}
		return 0;
	}

	/**
	 * Summation of child nodes of a given node.
	 * 
	 * @param root
	 * @return
	 */
	private int sum(TreeNode root) {
		if (root == null) {
			return 0;
		}
		
		return sum(root.left) + root.data + sum(root.right);
	}
	
	/**
	 * Boundary traversal of a given tree.
	 * 
	 * We break the problem in 3 parts: 1. Print the left boundary in top-down
	 * manner. 2. Print all leaf nodes from left to right, which can again be
	 * sub-divided into two sub-parts: …..2.1 Print all leaf nodes of left
	 * sub-tree from left to right. …..2.2 Print all leaf nodes of right subtree
	 * from left to right. 3. Print the right boundary in bottom-up manner.
	 * 
	 * We need to take care of one thing that nodes are not printed again. e.g.
	 * The left most node is also the leaf node of the tree.
	 * 
	 * @param root
	 */
	public void boundaryTraversal(TreeNode root) {
		if (root != null) {
			System.out.print(root.data + " ");
			leftBoundary(root.left);
			leafNodes(root.left);
			leafNodes(root.right);
			rightBoundary(root.right);
		}
	}
	
	/**
	 * Print left boundary nodes of a tree except the leaf node.
	 * 
	 * Print top-down
	 * 
	 * @param root
	 */
	private void leftBoundary(TreeNode root) {
		if (root != null) {
			if (root.left != null) {
				System.out.print(root.data + " ");
				leftBoundary(root.left);
			} else if (root.right != null) {
				System.out.print(root.data + " ");
				leftBoundary(root.right);
			}
		}
	}
	
	/** 
	 * Print right boundary nodes of a tree except the leaf node.
	 * 
	 * Print bottom-up
	 * 
	 * @param root
	 */
	private void rightBoundary(TreeNode root) {
		if (root != null) {
			if (root.right != null) {
				rightBoundary(root.right);
				System.out.print(root.data + " ");
			} else if (root.left != null) {
				rightBoundary(root.left);
				System.out.print(root.data + " ");
			}
		}
	}
	
	/** 
	 * Print leaf nodes of a tree.
	 * 
	 * @param root
	 */
	private void leafNodes(TreeNode root) {
		if (root != null) {
			leafNodes(root.left);
			if (root.left == null && root.right == null) {
				System.out.print(root.data + " ");
			}
			leafNodes(root.right);
		}
	}
	
	/**
	 * Size of a tree.
	 * 
	 * @param root
	 * @return
	 */
	public int size(TreeNode root) {
		if (root == null) {
			return 0;
		}
		
		return size(root.left) + 1 + size(root.right);
	}
	
	/**
	 * Largest BST in a given BT.
	 * Time Complexity: The worst case time complexity of this method will be O(n^2)
	 * 
	 * @param root
	 * @return
	 */
	public int largestBSTinBT(TreeNode root) {
		if (bstCheck(root)) {
			return size(root);
		}
		return Math.max(largestBSTinBT(root.left), largestBSTinBT(root.right));
	}
	
	/**
	 * 
	 * Given the root node of a binary search tree, return the sum of values of all nodes 
	 * with value between L and R (inclusive).
	 * The binary search tree is guaranteed to have unique values.
	 * @param root
	 * @param L
	 * @param R
	 * @return
	 */
    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }
        if (root.data < L) {
            return rangeSumBST(root.right, L, R);
        }
        if (root.data > R) {
            return rangeSumBST(root.left, L, R);
        }
        return root.data + rangeSumBST(root.left, L, R) + rangeSumBST(root.right, L, R);
    }
    
    /**
     * Given a non-empty binary tree, find the maximum path sum.

		For this problem, a path is defined as any sequence of nodes from some starting node to 
		any node in the tree along the parent-child connections.
 		The path must contain at least one node and does not need to go through the root.
 		
	 	Input: [1,2,3]
	
	       1
	      / \
	     2   3

		Output: 6
		Example 2:
		
		Input: [-10,9,20,null,null,15,7]
		
		   -10
		   / \
		  9  20
		    /  \
		   15   7
		
		Output: 42
     */
    private int maxSum;
    
    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        maxPathSumUtil(root);
        return maxSum;
    }
    
    private int maxPathSumUtil(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(0, maxPathSumUtil(root.left));
        int right = Math.max(0, maxPathSumUtil(root.right));
        maxSum = Math.max(maxSum, left+right+root.data);
        return Math.max(left, right) + root.data;
        /**Just want to add a explanation about the last two lines based on my comprehension.

        maxValue = Math.max(maxValue, left + right + node.val);
        return Math.max(left, right) + node.val;
        maxValue is the value which recording whether this current root is the final root, 
        so we use left + right + node.val. But to the upper layer(after return statement), 
        we cannot choose both left and right brunches, so we need to select the larger one, 
        so we use max(left, right) + node.val to prune the lower brunch.**/
    }
	
	/**
	 * Given a binary tree where all the right nodes are either leaf nodes with
	 * a sibling (a left node that shares the same parent node) or empty, flip
	 * it upside down and turn it into a tree where the original right nodes
	 * turned into left leaf nodes. Return the new root.
	 * 
	 * For example: Given a binary tree {1,2,3,4,5}, return the root of the
	 * binary tree [4,5,2,#,#,3,1].
	 * 
	 * newRoot is the new root.
	 * 
	 * @param root
	 * @param newRoot
	 * @return
	 */
	private TreeNode newRoot = null;
	public TreeNode upsideDownTree(TreeNode root) {
		upsideDownTreeUtil(root);
		return newRoot;
	}
	public TreeNode upsideDownTreeUtil(TreeNode root) {
		if (root == null) {
			return root;
		}
		if (root.left == null && root.right == null) {
			newRoot = root;
			return root;
		}
		TreeNode parent = upsideDownTreeUtil(root.left);
		parent.left = root.right;
		parent.right = root;
		root.left = root.right = null;
		return parent.right;
	}
	
	/**
	 * Given a imbalance binary tree, print the leaf nodes then remove those
	 * leaf node, print the new leaf nodes until only root node left.
	 *
	 * @param root
	 */
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Map<Integer, List<Integer>> map = new TreeMap<>();
        findLeavesUtil(root, map);
        for (int key : map.keySet()) {
            res.add(map.get(key));
        }
        return res;
    }
    
    private int findLeavesUtil(TreeNode root, Map<Integer, List<Integer>> map) {
        if (root == null) {
            return 0;
        }
        int lh = findLeavesUtil(root.left, map);
        int rh = findLeavesUtil(root.right, map);
        int h = 1 + Math.max(lh, rh);
        if (!map.containsKey(h)) {
            map.put(h, new ArrayList<Integer>());
        }
        List<Integer> list = map.get(h);
        list.add(root.data);
        map.put(h, list);
        root.left = root.right = null;
        return h;
    }
	
	/**
	 * Trim nodes of a tree outside the range min-max.
	 * 
	 * There are two possible cases for every node. 1) Node’s key is outside the
	 * given range. This case has two sub-cases. …….a) Node’s key is smaller
	 * than the min value. …….b) Node’s key is greater that the max value. 2)
	 * Node’s key is in range.
	 * 
	 * We don’t need to do anything for case 2. In case 1, we need to remove the
	 * node and change root of sub-tree rooted with this node. The idea is to
	 * fix the tree in Postorder fashion. When we visit a node, we make sure
	 * that its left and right sub-trees are already fixed. In case 1.a), we
	 * simply remove root and return right sub-tree as new root. In case 1.b),
	 * we remove root and return left sub-tree as new root.
	 * 
	 * @param root
	 * @param min
	 * @param max
	 */
	public TreeNode trimTree(TreeNode root, int min, int max) {
		if (root == null) {
			return null;
		}
		root.left = trimTree(root.left, min, max);
		root.right = trimTree(root.right, min, max);
		if (root.data < min) {
			return root.right;
		} 
		if (root.data > max) {
			return root.left;
		}
		
		return root;
	}
	
	/**
	 * Find LCA (Lowest Common Ancestor) of 2 given nodes - parent pointer
	 * provided.
	 * 
	 * Below are steps to find LCA.
	 * 
	 * Create an empty hash table. Insert n1 and all of its ancestors in hash
	 * table. Check if n2 or any of its ancestors exist in hash table, if yes
	 * return the first existing ancestor.
	 * 
	 * @param root
	 * @param node1
	 * @param node2
	 * @return
	 */
	public Node lcaWithParent(Node node1, Node node2) {
		Map<Node, Boolean> map = new HashMap<>();
		while(node1 != null) {
			map.put(node1, true);
			node1 = node1.parent;
		}
		
		while (node2 != null) {
			if (map.containsKey(node2)) {
				return node2;
			}
			node2 = node2.parent;
		}
		
		return null;
	}
	
	/**
	 * Given a binary tree, display all the paths from root to leaf.
	 * For example, given the following binary tree:
		
		   1
		 /   \
		2     3
		 \
		  5
		All root-to-leaf paths are:
		
		["1->2->5", "1->3"]
	 * @param root
	 */
	public void BTPaths(TreeNode root) {
		List<String> res = new ArrayList<>();
		if (root != null) {
			BTPathsUtil(root, "", res);
		}
		for(String str : res) {
			System.out.print(str + ", ");
		}
	}
	
	private void BTPathsUtil(TreeNode root, String path, List<String> res) {
		if (root.left == null && root.right == null) {
			res.add(path + root.data);
		}
		if (root.left != null) {
			BTPathsUtil(root.left, path+root.data+"->", res);
		}
		if (root.right != null) {
			BTPathsUtil(root.right, path+root.data+"->", res);
		}
	}
	
	/**
	 * Given an expression tree, evaluate its value.
	 * 
	 * @param root
	 * @return
	 */
	public Integer evalExpressionTree(TreeNodeCh root) {
		if (root != null) {		
			if (root.left == null && root.right == null) {
				return Integer.parseInt(root.data);
			}
			
			int left = evalExpressionTree(root.left);
			int right = evalExpressionTree(root.right);
			
			if (root.data.equals("+")) {
				return left + right;
			}
			if (root.data.equals("-")) {
				return left - right;
			}
			if (root.data.equals("*")) {
				return left * right;
			}
			if (root.data.equals("/")) {
				return left / right;
			}
		}
		
		return null;
	}
	
	public class TreeNodeCh {
		String data;
		TreeNodeCh left;
		TreeNodeCh right;
	}
	
	/**
	 * Connect all the adjacent nodes at the same level in a binary tree.
	 * 
	 * Input Tree
		       A
		      / \
		     B   C
		    / \   \
		   D   E   F
		
		
		Output Tree
		       A--->NULL
		      / \
		     B-->C-->NULL
		    / \   \
		   D-->E-->F-->NULL
	 */
	public void connectNodesAtSameLevelBT(NextRightNode root) {
		Queue<NextRightNode> q = new LinkedList<NextRightNode>();
		int cur = 0;
		int next = 0;
		
		q.add(root);
		cur++;

		while(!q.isEmpty()) {
			NextRightNode prev = q.peek();
			q.remove();
			cur--;
			if (prev.left != null) {
				q.add(prev.left);
				next++;
			}
			if (prev.right != null) {
				q.add(prev.right);
				next++;
			}
			if (cur == 0) {
				cur = next;
				next = 0;
				prev.nextRight = null;
			} else {
				prev.nextRight = q.peek();
			}
		}
	}
	
	private class NextRightNode {
		int data;
		NextRightNode left, right, nextRight;
		
		public NextRightNode(int data) {
			this.data = data;
			this.left = this.right = this.nextRight = null;
		}
	}
	
	/**
	 * Given a binary tree, return all duplicate subtrees. For each kind of
	 * duplicate subtrees, you only need to return the root node of any one of
	 * them.
	 * 
	 * Two trees are duplicate if they have the same structure with same node
	 * values.
	 * 
	 * @param root
	 * @return
	 */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new LinkedList<>();
        HashMap<String, Integer> map = new HashMap<>();
        preOrderTraversal(root, res, map);
        return res;
    }
    
    private String preOrderTraversal(TreeNode root, List<TreeNode> res, HashMap<String, Integer> map) {
    	if (root == null) {
    		return "#";
    	}
    	String serialize = root.data + "," + preOrderTraversal(root.left, res, map) + "," + preOrderTraversal(root.right, res, map);
    	if (map.getOrDefault(serialize, 0) == 1) {
    		res.add(root);
    	}
		map.put(serialize, map.getOrDefault(serialize, 0)+1);
		return serialize;
    }
    
	/**
	 * The thief has found himself a new place for his thievery again. There is
	 * only one entrance to this area, called the "root." Besides the root, each
	 * house has one and only one parent house. After a tour, the smart thief
	 * realized that "all houses in this place forms a binary tree". It will
	 * automatically contact the police if two directly-linked houses were
	 * broken into on the same night.
	 * 
	 * Determine the maximum amount of money the thief can rob tonight without
	 * alerting the police.
	 * 
	 * @param root
	 * @return
	 */
    public int rob(TreeNode root) {
        return robUtil(root, new HashMap<TreeNode, Integer>());
    }
    
    private int robUtil(TreeNode root, HashMap<TreeNode, Integer> map) {
        if (root == null) {
            return 0;
        }
        if (map.containsKey(root)) {
            return map.get(root);
        }

        int sum = 0;
        if (root.left != null) {
            sum = robUtil(root.left.left, map) + robUtil(root.left.right, map);
        }
        if (root.right != null) {
            sum += robUtil(root.right.left, map) + robUtil(root.right.right, map);
        }
        int children = robUtil(root.left, map) + robUtil(root.right, map);
        
        int res = Math.max(root.data + sum, children);
        map.put(root, res);
        return res;
    }
    
    // Definition for a Node.
    class GNode {
        public int val;
        public List<GNode> children;

        public GNode() {}

        public GNode(int _val,List<GNode> _children) {
            val = _val;
            children = _children;
        }
    };
    /**
     * Serialize and deserialize an n-ary tree.
     * 
     * @param root
     * @return
     */
    // Encodes a tree to a single string.
    public String serialize(GNode root) {
        StringBuilder sb = new StringBuilder();
        serializeUtil(root, sb);
        return sb.toString();
    }
    
    private void serializeUtil(GNode root, StringBuilder sb) {
        if (root != null) {
            sb.append(String.valueOf(root.val)).append("#");
            sb.append(String.valueOf(root.children.size())).append("#");
            for (GNode child : root.children) {
                serializeUtil(child, sb);
            }
        }
    }

    // Decodes your encoded data to tree.
    public GNode deserialize(String data) {
        if (data != null && !data.isEmpty()) {
            String[] parts = data.split("#");
            Queue<String> q = new LinkedList<>(Arrays.asList(parts));
            return deserializeUtil(q);
        }
        return null;
    }
    
    private GNode deserializeUtil(Queue<String> q) {
        int val = Integer.parseInt(q.remove());
        GNode root = new GNode(val, null);
        root.children = new ArrayList<GNode>();
        int size = Integer.parseInt(q.remove());
        for (int i=0; i<size; i++) {
            root.children.add(deserializeUtil(q));
        }
        return root;
    }
    
    /**
     * Find the sum of all left leaves in a given binary tree.

		Example:
		
		    3
		   / \
		  9  20
		    /  \
		   15   7
		
		There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        if (root.left != null) {
            if (root.left.left == null && root.left.right == null) {
                res += root.left.data;
            } else {
                res += sumOfLeftLeaves(root.left);
            }
        }
        res += sumOfLeftLeaves(root.right);
        
        return res;
    }
    
    /**
     * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

		An example is the root-to-leaf path 1->2->3 which represents the number 123.
		
		Find the total sum of all root-to-leaf numbers.
		
		Note: A leaf is a node with no children.
		
		Example:
		
		Input: [1,2,3]
		    1
		   / \
		  2   3
		Output: 25
		Explanation:
		The root-to-leaf path 1->2 represents the number 12.
		The root-to-leaf path 1->3 represents the number 13.
		Therefore, sum = 12 + 13 = 25.
     */
    private int totalSum = 0;
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        sumNumbersUtil(root, 0);
        return totalSum;
    }
    
    private void sumNumbersUtil(TreeNode root, int partialSum) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                totalSum += (partialSum*10) + root.data;
                return;
            }
            sumNumbersUtil(root.left, partialSum*10 + root.data);
            sumNumbersUtil(root.right, partialSum*10 + root.data);
        }
    }
	
	/**
	 * Tree node with parent pointer.
	 *
	 */
	private class Node 
	{
	    int key;
	    Node left, right, parent;
	 
	    Node(int key) 
	    {
	        this.key = key;
	        left = right = parent = null;
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
