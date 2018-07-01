package lyft;

/**
 * Node data structure of the queue.
 *
 */
class Node {
	
	public Node(Object key, Object value, double size) {
		this.key = key;
		this.value = value;
		this.size = size;
		this.pre = this.next = null;
	}
	
	Object key;
	
	Object value;
	
	double size;
	
	Node pre;
	
	Node next;
	
}
