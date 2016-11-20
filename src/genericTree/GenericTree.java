package genericTree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GenericTree<T> {
	GenericTreeNode<T> root;
	
	GenericTree(GenericTreeNode<T> root) {
		this.root = root;
	}
	
	GenericTreeNode<T> getRoot() {
		return root;
	}
	
	void displayTree(GenericTreeNode<T> root) {
		int curLevel = 0;
		int nxtLevel = 0;
		Queue<GenericTreeNode<T>> displayQueue = new LinkedList<GenericTreeNode<T>>();
		displayQueue.add(root);
		curLevel++;
		
		while(!displayQueue.isEmpty()) {
			GenericTreeNode<T> tmp = displayQueue.remove();
			curLevel--;
			System.out.print(tmp.getData() + " ");
			for( GenericTreeNode<T> child : tmp.getChildren()) {
				displayQueue.add(child);
				nxtLevel++;
			}
			if (curLevel == 0) {
				System.out.println();
				curLevel = nxtLevel;
				nxtLevel = 0;
			}
		}
	}
	
	public static void main(String[] args) {
		GenericTreeNode<String> root = new GenericTreeNode<String>("root");
		
		GenericTreeNode<String> childA = new GenericTreeNode<String>("A");
		GenericTreeNode<String> childB = new GenericTreeNode<String>("B");
		GenericTreeNode<String> childC = new GenericTreeNode<String>("C");
		GenericTreeNode<String> childD = new GenericTreeNode<String>("D");
		GenericTreeNode<String> childE = new GenericTreeNode<String>("E");
		
		root.addChild(childA);
		root.addChild(childB);
		root.addChild(childC);
		
		childA.addChild(childD);
		childC.addChild(childE);
		
		GenericTree tree = new GenericTree(root);
		
		tree.displayTree(root);
		
	}
}
