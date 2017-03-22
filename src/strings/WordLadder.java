package strings;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start to end, such that only one letter can be changed at a time and each intermediate word must exist in the dictionary. For example, given:
 * start = "hit"
 * end = "cog"
 * dict = ["hit", "hot","dot","dog","lot","log", "cog"]
 * 
 * @author gouthr
 *
 */

public class WordLadder {
	
	public class Word {
		String word;
		int dist;
		
		public Word(String word, int dist) {
			this.word = word;
			this.dist = dist;
		}
	}
	
	public static int wordDistance(WordLadder wl, String stWord, String endWord, Set<String> dict) {
		LinkedList<Word> q = new LinkedList<Word>();
		q.add(wl.new Word(stWord, 1));
		
		while(!q.isEmpty()) {
			Word topWord = q.remove();
			String word = topWord.word;
			int dist = topWord.dist;
			
			if (word.equals(endWord)) {
				return dist;
			}
			
			char[] wordArr = word.toCharArray();
			for(int i=0; i<wordArr.length; i++) {
				for(char c='a'; c<='z'; c++) {
					char tmp = wordArr[i];
					if (wordArr[i] != c) {
						wordArr[i] = c;
					}
					String newWord = new String(wordArr);
					if (dict.contains(newWord)) {
						q.add(wl.new Word(newWord, dist+1));
						dict.remove(newWord);
					}
					wordArr[i] = tmp;
				}
			}
		}
		
		return 0;
	}
	

	public static void main(String[] args) {
		WordLadder wl = new WordLadder();
		Set<String> dict = new HashSet<String>();
		dict.add("hot");
		dict.add("dot");
		dict.add("dog");
		dict.add("lot");
		dict.add("log");
		dict.add("cog");
		dict.add("hit");
		int res = wordDistance(wl, "hit", "cog", dict);
		System.out.println(res);

	}

}
