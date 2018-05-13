package trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Trie {
	private TrieNode root;
	
	public Trie() {
		root = new TrieNode();
	}
	
	public static void main(String[] args) {
		String[] dict = {"oath","pea","eat","rain"};
		char[][] wordMatrix = {{'o','a','a','n'},
							   {'e','t','a','e'}, 
							   {'i','h','k','r'},
		    				   {'i','f','l','v'}};
		Set<String> dictionary = new HashSet<>();
		for (String word : dict) {
			dictionary.add(word);
		}
		List<String> res = wordSearchInDict(wordMatrix, dictionary);
		System.out.println("Words present in the matrix: " + res);
	}

	public void insertWord(String str) {
		TrieNode cur = root;
		for (int i=0; i<str.length(); i++) {
			char ch = str.charAt(i);
			if (!cur.containsKey(ch)) {
				cur.putKey(ch, new TrieNode());
			}
			cur = cur.getKey(ch);
		}
		cur.setEnd();
	}
	
	// Returns if the word is in the trie.
	public boolean searchWord(String word) {
		TrieNode node = searchPrefix(word);
		return node!=null && node.isEnd();
	}
	
    // Returns if there is any word in the trie
    // that starts with the given prefix.
	public boolean startsWith(String prefix) {
		TrieNode node = searchPrefix(prefix);
		return node!=null;
	}
	
	// search a prefix or whole key in trie and
    // returns the node where search ends
	private TrieNode searchPrefix(String word) {
		TrieNode node =root;
		for (int i=0; i<word.length(); i++) {
			char ch = word.charAt(i);
			if (node.containsKey(ch)) {
				node = node.getKey(ch);
			} else {
				return null;
			}
		}
		return node;
	}
	
	/**
	 * Given a 2D board and a list of words from the dictionary, find all words
	 * in the board.
	 * 
	 * Each word must be constructed from letters of sequentially adjacent cell,
	 * where "adjacent" cells are those horizontally or vertically neighboring.
	 * The same letter cell may not be used more than once in a word.
	 * 
	 * Example:
	 * 
	 * Input: words = ["oath","pea","eat","rain"] and 
	 * board = [['o','a','a','n'],
	 * 			['e','t','a','e'], 
	 * 			['i','h','k','r'],
	 * 			['i','f','l','v'] ]
	 * 
	 * Output: ["eat","oath"]
	 * 
	 * @param arr
	 * @param dict
	 * @return
	 */
	public static List<String> wordSearchInDict(char[][] arr, Set<String> dict) {
		if (arr == null || arr.length == 0) {
			return Collections.emptyList();
		}
		Trie trie = new Trie();
		for (String word : dict) {
			trie.insertWord(word);
		}
		
		int m = arr.length;
		int n = arr[0].length;
		boolean[][] used = new boolean[m][n];
		Set<String> res = new HashSet<String>();
		for (int i=0; i<m; i++) {
			for (int j=0; j<n; j++) {
				dfsMarking(arr, i, j, m, n, used, "", 0, trie, res);
			}
		}
		return new ArrayList<String>(res);
	}
	
	private static void dfsMarking(char[][] arr, int i, int j, int m, int n, boolean[][] used, String str, int pos, Trie trie,
			Set<String> res) {
		if (i < 0 || i >= m || j < 0 || j >= n || used[i][j] == true) {
			return;
		}

		str += arr[i][j];

		if (!trie.startsWith(str)) {
			return;
		}

		if (trie.searchWord(str)) {
			res.add(str);
		}

		used[i][j] = true;
		dfsMarking(arr, i + 1, j, m, n, used, str, pos + 1, trie, res);
		dfsMarking(arr, i - 1, j, m, n, used, str, pos + 1, trie, res);
		dfsMarking(arr, i, j + 1, m, n, used, str, pos + 1, trie, res);
		dfsMarking(arr, i, j - 1, m, n, used, str, pos + 1, trie, res);
		used[i][j] = false;
	}
}
