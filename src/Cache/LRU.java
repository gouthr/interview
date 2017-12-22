package Cache;

import java.util.HashMap;

/**
 * Referred from here - https://www.programcreek.com/2013/03/leetcode-lru-cache-java/
 *
 */
public class LRU {
	HashMap<Integer, Node> map;
	int capacity;
	Node head;
	Node end;
	
	public LRU(int capacity) {
		this.capacity = capacity;
		this.head = this.end = null;
		map = new HashMap<Integer, Node>();
	}
	
	/**
	 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache,
	 * otherwise return -1.
	 * 
	 * @param key
	 * @return
	 */
	public int getKey(int key) {
		if (map.containsKey(key)) {
			Node node = map.get(key);
			remove(node);
			setHead(node);
			return node.value;
		}
		
		return -1;
	}
	
	private void remove(Node node) {
		if (node.pre != null) {
			node.pre.next = node.next;
		} else {
			head = node.next;
		}
		
		if (node.next != null) {
			node.next.pre = node.pre;
		} else {
			end = node.pre;
		}
	}
	
	private void setHead(Node node) {
		node.next = head;
		node.pre = null;
		
		if (head != null) {
			head.pre = node;
		}
		
		head = node;
		
		if (end == null) {
			end = head;
		}
	}
	
	/**
	 * set(key, value) - Set or insert the value if the key is not already present.
	 * When the cache reached its capacity, it should invalidate the least recently
	 * used item before inserting a new item.
	 * 
	 * @param key
	 * @param value
	 */
	public void setKey(int key, int value) {
		if (map.containsKey(key)) {
			Node old = map.get(key);
			old.value = value;
			remove(old);
			setHead(old);
		} else {
			Node created = new Node(key, value);
			if (map.size() >= capacity) {
				map.remove(end.key);
				remove(end);
				setHead(created);
			} else {
				setHead(created);
			}
			map.put(key, created);
		}
	}
}
