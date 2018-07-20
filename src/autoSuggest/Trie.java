package autoSuggest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Trie {
	private TrieNode root;
	
	public Trie() {
		root = new TrieNode();
	}
	
	public static void main(String[] args) {
		String[] dict = {"art","zone","zip","arts", "date", "z", "day", "articles", "data", "catch", "zoom", "article", "articulate", "articulation"};
		List<String> dictionary = new LinkedList<>();
		Map<String, Integer> map = new HashMap<>();
		int cnt = 1;
		for (String word : dict) {
			dictionary.add(word);
			map.put(word, cnt++);
		}
		
		String[] input = {"catc", "art", "da", "z"};
		
		// insert dictionary words into a trie
		Trie trie = new Trie();
		for (String word : dictionary) {
			trie.insertWord(word);
		}
		
		for (String inputWord : input) {
			if (trie.startsWith(trie.root, inputWord)) {
				List<String> res = new ArrayList<>();
				TrieNode node = trie.searchPrefix(trie.root, inputWord);
				trie.autoComplete(node, inputWord, res);
				Collections.sort(res, new Comparator<String>() {
					public int compare(String s1, String s2) {
						return map.get(s1) - map.get(s2);
					}
				});
				if (res.size() > 5) {
					res = res.subList(0, 5);
				}
				System.out.println(inputWord + ":");
				for(String eachRes : res) {
					System.out.println(eachRes + " (" + map.get(eachRes) + ")");
				}
				System.out.println();
			}
		}
	}
	
	public void autoComplete(TrieNode stNode, String prefix, List<String> res) {
		if (stNode.isEnd()) {
			res.add(prefix);
		}
		for (char ch = 'a'; ch <= 'z'; ch++) {
			if (stNode.containsKey(ch)) {
				autoComplete(stNode.getKey(ch), prefix+ch, res);
			}
		}
	}
 
	// insert word into a trie
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
	public boolean searchWord(TrieNode stNode, String word) {
		TrieNode node = searchPrefix(stNode, word);
		return node!=null && node.isEnd();
	}
	
    // Returns if there is any word in the trie
    // that starts with the given prefix.
	public boolean startsWith(TrieNode stNode, String prefix) {
		TrieNode node = searchPrefix(stNode, prefix);
		return node!=null;
	}
	
	// search a prefix or whole key in trie and
    // returns the node where search ends
	private TrieNode searchPrefix(TrieNode stNode, String word) {
		TrieNode node = stNode;
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
}
