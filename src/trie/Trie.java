package trie;

public class Trie {
	private TrieNode root;
	
	public Trie() {
		root = new TrieNode();
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
}
