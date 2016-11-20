package genericTree;

import java.util.ArrayList;
import java.util.List;

public class GenericTreeNode<T> {
	private T data;
	private List<GenericTreeNode<T>> children;
	
	public GenericTreeNode(T data) {
		this.data = data;
		this.children = new ArrayList<GenericTreeNode<T>>();
	}
	
	public void addChild(GenericTreeNode<T> child) {
		children.add(child);
	}
	
	public void addChildAtIndex(int index, GenericTreeNode<T> child) {
		children.add(index, child);
	}
	
	public void removeChild(GenericTreeNode<T> removeChild) {
		children.remove(removeChild);
	}
	
	public void removeChildAtIndex(int index) {
		children.remove(index);
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	public T getData() {
		return data;
	}
	
	public void setChildren(List<GenericTreeNode<T>> children) {
		this.children = children;
	}
	
	public List<GenericTreeNode<T>> getChildren() {
		return children;
	}
}
