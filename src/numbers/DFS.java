package numbers;

public class DFS {
	
	public static void main(String[] args) {
		int[][] arr =  {{1, 1, 0, 0, 1},
						{1, 1, 0, 1, 0},
						{0, 0, 1, 0, 0},
						{0, 0, 0, 1, 1}};
		System.out.println("Number of islands: " + countIslands(arr));
		
		char[][] arr1 = {{'A','B','C','E'}, 
						{'S','F','C','S'}, 
						{'A','D','E','E'}};
		String word = "ABCCED";
		System.out.println("Word (" + word + ") is present in the 2D char matrix: " + wordSearch(arr1, word));
	}
	/**
	 * Given a 2d grid map of '1's (land) and '0's (water), count the number of
	 * islands. An island is surrounded by water and is formed by connecting
	 * adjacent lands horizontally or vertically. You may assume all four edges
	 * of the grid are all surrounded by water.
	 * 
	 * Example 1:
	 * 
	 * Input: 11110 
	 * 		  11010
	 *        11000
	 *        00000
	 * 
	 * Output: 1 Example 2:
	 * 
	 * Input: 11000
	 *        11000
	 *        00100 
	 *        00011
	 * 
	 * Output: 3
	 * 
	 * @param arr
	 * @return
	 */
	public static int countIslands(int[][] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		
		int m = arr.length;
		int n = arr[0].length;
		int count = 0;

		for (int i=0; i<m; i++) {
			for (int j=0; j<n; j++) {
				if (arr[i][j] == 1) {
					dfsMarking(arr, i, j, m, n);
					count++;
				}
			}
		}		
		return count;
	}
	
	private static void dfsMarking(int[][] arr, int i, int j, int m, int n) {
		// Code for wrap around support
		if (i<0) {
			i = i+m;
		} else if (i >= m) {
			i = i-m;
		} else if (j < 0) {
			j = j+n;
		} else if (j >= n) {
			j = j-n;
		}
		
		// Code for NO wrap-around
		// if (i<0 || i>=m || j<0 || j>=n || arr[i][j]!=1) {
		//	return;
		// }
		
		if (arr[i][j]!=1) {
			return;
		}
		arr[i][j] = 0;
		dfsMarking(arr, i+1, j, m, n);
		dfsMarking(arr, i-1, j, m, n);
		dfsMarking(arr, i, j+1, m, n);
		dfsMarking(arr, i, j-1, m, n);
	}
	
	/**
	 * 
	 * Given a 2D board and a word, find if the word exists in the grid.
	 * 
	 * The word can be constructed from letters of sequentially adjacent cell,
	 * where "adjacent" cells are those horizontally or vertically neighboring.
	 * The same letter cell may not be used more than once.
	 * 
	 * Example:
	 * 
	 * board = [ ['A','B','C','E'], 
	 *           ['S','F','C','S'], 
	 *           ['A','D','E','E'] ]
	 * 
	 * Given word = "ABCCED", return true. Given word = "SEE", return true.
	 * Given word = "ABCB", return false.
	 * 
	 * @param arr
	 * @param word
	 * @return
	 */
	public static boolean wordSearch(char[][] arr, String word) {
		if (arr == null || arr.length == 0 || word == null || word.length() == 0) {
			return false;
		}
		
		int m = arr.length;
		int n = arr[0].length;
		boolean[][] used = new boolean[m][n];
		
		for (int i=0; i<m; i++) {
			for (int j=0; j<n; j++) {
				if (wordSearchUtil(arr, i, j, m, n, word, 0, used)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private static boolean wordSearchUtil(char[][] arr, int i, int j, int m, int n, String word, int pos, boolean[][] used) {
		if (pos == word.length()) {
			return true;
		}
		if (i<0 || i>=m || j<0 || j>=n || used[i][j] == true || word.charAt(pos) != arr[i][j]) {
			return false;
		}
		used[i][j] = true;
		boolean res1 = wordSearchUtil(arr, i+1, j, m, n, word, pos+1, used);
		boolean res2 = wordSearchUtil(arr, i-1, j, m, n, word, pos+1, used);
		boolean res3 = wordSearchUtil(arr, i, j+1, m, n, word, pos+1, used);
		boolean res4 = wordSearchUtil(arr, i, j-1, m, n, word, pos+1, used);
		used[i][j] = false;
		return (res1||res2||res3||res4);
	}

}
