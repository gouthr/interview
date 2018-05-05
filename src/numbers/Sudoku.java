package numbers;

public class Sudoku {
	
	public static void main(String[] args) {
		Sudoku sod = new Sudoku();
		int arr[][] = new int[][]{{3, 0, 6, 5, 0, 8, 4, 0, 0},
								  {5, 2, 0, 0, 0, 0, 0, 0, 0},
								  {0, 8, 7, 0, 0, 0, 0, 3, 1},
								  {0, 0, 3, 0, 1, 0, 0, 8, 0},
								  {9, 0, 0, 8, 6, 3, 0, 0, 5},
								  {0, 5, 0, 0, 9, 0, 6, 0, 0},
								  {1, 3, 0, 0, 0, 0, 2, 5, 0},
								  {0, 0, 0, 0, 0, 0, 0, 7, 4},
								  {0, 0, 5, 2, 0, 6, 3, 0, 0}};
								  
		System.out.println("Given input is a valid sudoku input: " + sod.validSudoku(arr));
		
		if (sod.solveSudoku(arr)) {
			for (int i=0; i<9; i++) {
				for (int j=0; j<9; j++) {
					System.out.print(arr[i][j] + " ");
				}
				System.out.println();
			}
		} else {
			System.out.println("Sudoku solution does not exist.");
		}
	}
	
	/**
	 * Check for valid sudoku.
	 * 
	 * @param arr
	 * @return
	 */
	public boolean validSudoku(int[][] arr) {
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				if (arr[i][j] != 0) {
					if (!checkValidRowColBox(arr, arr[i][j], i, j)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	private boolean checkValidRowColBox(int[][] arr, int val, int row, int col) {
		if (!checkRowPresent(arr, row, val, col) && !checkColPresent(arr, col, val, row) && !checkBoxPresent(arr, row-(row%3), col-(col%3), val, row, col)) {
			return true;
		}
		return false;
	}
	
	private boolean checkRowPresent(int[][] arr, int row, int num, int col) {
		for(int i=0; i<9; i++) {
			if (i != col && arr[row][i] == num) {
				return true;
			}
		}
		return false;
	}
	
	private boolean checkColPresent(int[][] arr, int col, int num, int row) {
		for(int i=0; i<9; i++) {
			if (i != row && arr[i][col] == num) {
				return true;
			}
		}
		return false;
	}
	
	private boolean checkBoxPresent(int[][] arr, int startRow, int startCol, int num, int row, int col) {
		for(int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				if ((startRow+i)==row && (startCol+j)==col) {
					continue;
				}
				if (arr[startRow+i][startCol+j] == num) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean solveSudoku(int[][] arr) {
		Grid g = findUnassignedBox(arr) ;
		if (g == null) {
			return true;
		}
		for(int num=1; num<=9; num++) {
			if (checkFitForBox(arr, g.row, g.col, num)) {
				arr[g.row][g.col] = num;
				if (solveSudoku(arr)) {
					return true;
				}
				arr[g.row][g.col] = 0; // backtrack
			}
		}
		return false;
	}
	
	private Grid findUnassignedBox(int[][] arr) {
		Grid g = null;
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				if (arr[i][j] == 0) {
					return new Grid(i, j);
				}
			}
		}
		return g;
	}
	
	private boolean checkFitForBox(int[][]arr, int row, int col, int num) {
		if (!checkRowPresent(arr, row, num) && !checkColPresent(arr, col, num) && !checkBoxPresent(arr, row-(row%3), col-(col%3), num)) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean checkRowPresent(int[][] arr, int row, int num) {
		for(int i=0; i<9; i++) {
			if (arr[row][i] == num) {
				return true;
			}
		}
		return false;
	}
	
	private boolean checkColPresent(int[][] arr, int col, int num) {
		for(int i=0; i<9; i++) {
			if (arr[i][col] == num) {
				return true;
			}
		}
		return false;
	}
	
	private boolean checkBoxPresent(int[][] arr, int startRow, int startCol, int num) {
		for(int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				if (arr[startRow+i][startCol+j] == num) {
					return true;
				}
			}
		}
		return false;
	}
	
	private class Grid {
		public Grid(int row, int col) {
			this.row = row;
			this.col = col;
		}
		
		private int row;
		private int col;
	}

}
