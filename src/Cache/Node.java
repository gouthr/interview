package Cache;

/**
 * Node data structure of the queue.
 * 
 * Java provides a default specifier which is used when no access modifier is present.
 * Any class, field, method or constructor that has no declared access modifier is accessible
 * only by classes in the same package. The default modifier is not used for fields and methods 
 * within an interface.
 * 
 * @author gouthr
 *
 */
class Node {
	
	public Node(int key, int value) {
		this.key = key;
		this.value = value;
		this.pre = this.next = null;
	}
	
	int key;
	
	int value;
	
	Node pre;
	
	Node next;
	
}
