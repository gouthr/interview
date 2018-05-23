package numbers;

import java.util.HashMap;
import java.util.Map;

/*
1 2 3 4 5 6 7 8 9 0
Q W E R T Y U I O P
A S D F G H J K L ;
Z X C V B N M , . /

The above is a 10 x 4 keyboard. We are going to use this keyboard to encrypt input text. there are 3 types of transfermation to encrypt:
1. horizontal flip, which flips the input horizontally. e.g. hFlip(1) = 0;  hFlip(L) = S;
2. vertical flip, which flips input vertically. vFlip(1) = Z; vFlip(K) = I;
3. shift, which shifts input to a specific position. e.g. shift(Q,5) = Y; shift(A, 22) = 3; shift(Q, -20) = Z;

Input text will be transferred by a chain of transfermation. e.g. V,H,H,5,10,-43,H,V,6,V 
Please write a program which encrypts input text by a chain of transfermations.
Print out the encrypted text.
*/

public class Apple {
	  static String originalText = "Lorem ipsum dolor sit er elit lamet, consectetaur cillium adipisicing pecu, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
	  static String chain = "V,H,H,5,10,-43,H,V,6,V";
	  
	  public static void main(String[] args) {
	    System.out.println(originalText);
	    System.out.print(chain);
	    originalText.toUpperCase();
	    int strLen = originalText.length();
	    int chainLen = chain.length();
	    
	  char[][] arr= {{'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'}, 
			      	{'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'}, 
			      	{'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', ';'}, 
			      	{'Z', 'X', 'C', 'V', 'B', 'N', 'M', ',', '.', '/'}};
		  
	    String[] chainArr = chain.split(",");
	    Map<Character, Character> vShiftMap = verticalShift(arr);
	    Map<Character, Character> hShiftMap = horShift(arr);
	    Map<Character, Integer> shift = shift(arr);
	    StringBuilder res = new StringBuilder();
	    for (int i=0; i<chainLen; i++) {
	    	for (int j=0; j<strLen; j++) {
	    		if (chainArr[i].equals("H")) {
	    			res.append(hShiftMap.get(originalText.charAt(j)));
	    		} else if (chainArr[i].equals("V")) {
	    			res.append(vShiftMap.get(originalText.charAt(j)));
	    		} else {
	    			int shiftVal = Integer.parseInt(chainArr[i]);
	    			int pos = shift.get(originalText.charAt(j));
	    			int finalPos = pos + shiftVal;
	    			if (finalPos > 0) {
		    			while (finalPos > 39) {
		    				finalPos = finalPos-39;
		    			}
		    			finalPos--;
	    			} else {
	    				while (finalPos < 0) {
	    					finalPos = 39 + finalPos;
	    				}
	    				finalPos++;
	    			}
	    			res.append(shift.get(finalPos));
	    		}
	    	}
	    }
	    System.out.println("Result string:" + res.toString());
	  }
	  
	  public static Map<Character, Character> verticalShift(char[][] arr) {
		  int row = arr.length;
		  int col = arr[0].length;
		  Map<Character, Character> vShiftMap = new HashMap<>();
		  for (int i=0; i<row; i++) {
			  for (int j=0; j<col; j++) {
				  vShiftMap.put(arr[i][j], arr[row-1-i][j]);
			  }
		  }
		  return vShiftMap;
	  }
	  
	  public static Map<Character, Character> horShift(char[][] arr) {
		  int row = arr.length;
		  int col = arr[0].length;
		  Map<Character, Character> hShiftMap = new HashMap<>();
		  for (int i=0; i<row; i++) {
			  for (int j=0; j<col; j++) {
				  hShiftMap.put(arr[i][j], arr[i][col-1-j]);
			  }
		  }
		  return hShiftMap;
	  }
	  
	  public static Map<Character, Integer> shift(char[][] arr) {
		  int row = arr.length;
		  int col = arr[0].length;
		  Map<Character, Integer> shift = new HashMap<>();	  
		  int count = 0;
		  for (int i=0; i<row; i++) {
			  for (int j=0; j<col; j++) {
				  shift.put(arr[i][j], count);
				  count++;
			  }
		  }
		  return shift;
	  }
}
