package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class Graph {

	public static void main(String[] args) {
	    // Create a graph given in the above diagram
        Graph g = new Graph(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);
 
        System.out.println("Following is a Topological " +
                           "sort of the given graph");
        g.topologicalSort();
        
        System.out.println("DFS of the given graph:");
        g.dfs();
        
        Graph graph = new Graph(4); 
        graph.addEdge(0, 1); 
        graph.addEdge(0, 2); 
        graph.addEdge(1, 2); 
        graph.addEdge(2, 0); 
        graph.addEdge(2, 3); 
        graph.addEdge(3, 3); 
          
        if(graph.isCycle()) 
            System.out.println("Graph contains cycle"); 
        else
            System.out.println("Graph doesn't "
                                    + "contain cycle"); 

	}	
	
	private int v; //No. of vertices
	private LinkedList<Integer> adj[];
	
	public Graph(int v) {
		this.v = v;
		this.adj = new LinkedList[v];
		for(int i=0; i<this.v; i++) {
			adj[i] = new LinkedList<Integer>();
		}
	}
	
	public void topologicalSort() {
		Stack<Integer> stack = new Stack<Integer>();
		boolean[] visited = new boolean[v];
		for(int i=0; i<v; i++) {
			visited[i] = false;
		}
		for(int i=0; i<v; i++) {
			if (!visited[i]) {
				topologicalSortUtil(i, visited, stack);
			}
		}
		while(!stack.isEmpty()) {
			System.out.print(stack.pop() + " ");
		}
		System.out.println();
	}
	
	public void addEdge(int v, int w) {
		adj[v].add(w);
	}
	
	public void dfs() {
		boolean[] visited = new boolean[v];
		Queue<Integer> q = new LinkedList<Integer>();
		for(int i=0; i<v; i++) {
			visited[i] = false;
		}
		
		for (int i=v-1; i>=0; i--) {
			if (!visited[i]) {
				dfsUtil(i, visited, q);
			}
		}
		
		while(!q.isEmpty()) {
			System.out.print(q.remove() + " ");
		}
	}
	
	private void dfsUtil(int vertex, boolean[] visited, Queue<Integer> q) {
		visited[vertex] = true;
		q.add(vertex);
		Iterator<Integer> it = adj[vertex].iterator();
		while(it.hasNext()) {
			Integer nv = it.next();
			if (!visited[nv]) {
				dfsUtil(nv, visited, q);
			}
		}
	}
	
	private void topologicalSortUtil(int vertex, boolean[] visited, Stack<Integer> stack) {
		visited[vertex] = true;
		
		Iterator<Integer> it = adj[vertex].iterator();
		while(it.hasNext()) {
			Integer nv = it.next();
			if (!visited[nv]) {
				topologicalSortUtil(nv, visited, stack);
			}
		}
		stack.push(vertex);
	}
	
	/**
	 * Returns true is a cycle exists in the graph.
	 * @return
	 */
	public boolean isCycle() {
		boolean[] visited = new boolean[v]; // visited vertices
		boolean[] recurStack = new boolean[v]; // visited vertices in the recursion stack
		for (int i=0; i<v; i++) {
			if (isCycleUtil(i, visited, recurStack)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isCycleUtil(int src, boolean[] visited, boolean[] recurSt) {
		if (recurSt[src]) {
			return true;
		}
		
		// So as to not visit the visited vertices again
		if (visited[src]) {
			return false;
		}
		
		visited[src] = true;
		
		recurSt[src] = true;		
		for (int i=0; i<adj[src].size(); i++) {
			if (isCycleUtil(adj[src].get(i), visited, recurSt)) {
				return true;
			}
		}
		recurSt[src] = false;
		
		return false;
	}
	
	/** 
	 * Clone an undirected graph.
	 * 
	 * @param node
	 * @return
	 */
	public UndirectedGraphNode clone(UndirectedGraphNode node) {
		if (node == null) {
			return null;
		}
		
		Map<Integer, UndirectedGraphNode> map = new HashMap<>();
		if (map.containsKey(node.label)) {
			return map.get(node.label);
		}
		
		UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
		map.put(clone.label, clone);
		for (UndirectedGraphNode neighbor : node.neighbors) {
			clone.neighbors.add(clone(neighbor));
		}
		return clone;
	}
	
	class UndirectedGraphNode {
		int label;
		List<UndirectedGraphNode> neighbors;
		
		public UndirectedGraphNode(int label) {
			this.label = label;
			this.neighbors = new ArrayList<>();
		}
	}
}
