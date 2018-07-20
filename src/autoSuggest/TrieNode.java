package autoSuggest;

public class TrieNode {
	public TrieNode[] links;
	
	private static int R = 26;
	
	private boolean isEnd;
	
	public TrieNode() {
		links = new TrieNode[R];
	}
	
	public boolean containsKey(char c) {
		return links[c-'a'] != null;
	}
	
	public TrieNode getKey(char c) {
		return links[c-'a'];
	}
	
	public void putKey(char c, TrieNode node) {
		links[c-'a'] = node;
	}
	
	public boolean isEnd() {
		return isEnd;
	}
	
	public void setEnd() {
		isEnd = true;
	}
}
