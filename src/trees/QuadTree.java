package trees;

/**
 * We want to use quad trees to store an N x N boolean grid. Each cell in the
 * grid can only be true or false. The root node represents the whole grid. For
 * each node, it will be subdivided into four children nodes until the values in
 * the region it represents are all the same.
 * 
 * Each node has another two boolean attributes : isLeaf and val. isLeaf is true
 * if and only if the node is a leaf node. The val attribute for a leaf node
 * contains the value of the region it represents.
 * 
 * Your task is to use a quad tree to represent a given grid. The following
 * example may help you understand the problem better: 
 *
 */
class QuadTree {
	//Definition for a QuadTree node.
	class Node {
	 public boolean val;
	 public boolean isLeaf;
	 public Node topLeft;
	 public Node topRight;
	 public Node bottomLeft;
	 public Node bottomRight;

	 public Node() {}

	 public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {
	     val = _val;
	     isLeaf = _isLeaf;
	     topLeft = _topLeft;
	     topRight = _topRight;
	     bottomLeft = _bottomLeft;
	     bottomRight = _bottomRight;
	 }
	}
	
	 public Node construct(int[][] grid) {
	     if (grid == null || grid.length == 0 || grid[0].length == 0) {
	         return null;
	     }
	     
	     int m = grid.length;
	     int n = grid[0].length;
	     int r1 = 0;
	     int r2 = m-1;
	     int c1 = 0;
	     int c2 = n-1;
	     return build(grid, r1, c1, r2, c2);
	 }
	 
	 private Node build(int[][] grid, int r1, int c1, int r2, int c2) {
	     if (r1 > r2 || c1 > c2) {
	         return null;
	     }
	     int val = grid[r1][c1];
	     boolean isLeaf = true;
	     
	     for (int i=r1; i<=r2; i++) {
	         for (int j=c1; j<=c2; j++) {
	             if (grid[i][j] != val) {
	                 isLeaf = false;
	                 break;
	             }
	         }
	     }
	     
	     if (isLeaf) {
	         return new Node(val==1, isLeaf, null, null, null, null);
	     }
	     int rowMid = (r1 + r2)/2;
	     int colMid = (c1 + c2)/2;
	     
	     return new Node(false, false, 
	                    build(grid, r1, c1, rowMid, colMid),
	                    build(grid, r1, colMid+1, rowMid, c2),
	                    build(grid, rowMid+1, c1, r2, colMid),
	                    build(grid, rowMid+1, colMid+1, r2, c2));
	 }
}