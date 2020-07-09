package trie;

class WordDictionary {

    class TrieNode {
        TrieNode[] links = new TrieNode[26];
        boolean isWord = false;
    }
    
    private TrieNode root;
    
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode tmp = root;
        for (char ch : word.toCharArray()) {
            if (tmp.links[ch-'a'] == null) {
                tmp.links[ch-'a'] = new TrieNode();
            }
            tmp = tmp.links[ch-'a'];
        }
        tmp.isWord = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return match(word.toCharArray(), 0, root);
    }
    
    private boolean match(char[] word, int k, TrieNode tmp) {
        if (k == word.length) {
            return tmp.isWord;
        }
        
        if (word[k] == '.') {
            for (int i=0; i<tmp.links.length; i++) {
                if (tmp.links[i] != null && match(word, k+1, tmp.links[i])) {
                    return true;
                }
            }
        } else {
            if (tmp.links[word[k]-'a'] != null && match(word, k+1, tmp.links[word[k]-'a'])) {
                return true;
            }
        }
        return false;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
