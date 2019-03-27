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
        
        // Create a graph given in the above diagram 
        Graph g1 = new Graph(5); 
        g1.addEdge(1, 0);
        g1.addEdge(0, 1);
        g1.addEdge(0, 2);
        g1.addEdge(2, 0);
        g1.addEdge(2, 1);
        g1.addEdge(1, 2);
        g1.addEdge(0, 3);
        g1.addEdge(3, 0);
        g1.addEdge(3, 4);
        g1.addEdge(4, 3);
        if (g1.isCycleUndirected()) 
            System.out.println("Undirected Graph contains cycle"); 
        else
            System.out.println("Undirected Graph doesn't contain cycle"); 
  
        Graph g2 = new Graph(4); 
        g2.addEdge(0, 1);
        g2.addEdge(1, 0);
        g2.addEdge(1, 2);
        g2.addEdge(2, 1);
        g2.addEdge(3, 1);
        g2.addEdge(1, 3);
        if (g2.isCycleUndirected()) 
            System.out.println("Undirected Graph contains cycle"); 
        else
            System.out.println("Undirected Graph doesn't contain cycle"); 

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
	
	public void addEdge(int v, int w) {
		adj[v].add(w);
		// adj[w].add(v); // For an undirected graph both directions need to be populated
	}
	
	public void topologicalSort() {
		Stack<Integer> stack = new Stack<Integer>();
		boolean[] visited = new boolean[v];

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
	
	public void dfs() {
		boolean[] visited = new boolean[v];
		Queue<Integer> q = new LinkedList<Integer>();
		
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
	
	/**
	 * Returns true is a cycle exists in a directed graph.
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
	 * Returns true if a cycle exists in a undirected graph.
	 * @return
	 */
	public boolean isCycleUndirected() {
		boolean[] visited = new boolean[v];
		
		for (int i=0; i<v; i++) {
			if (!visited[i]) {
				if (isCycleUndirectedUtil(i, visited, -1)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean isCycleUndirectedUtil(int src, boolean[] visited, int parent) {
		visited[src] = true;
		
		for (int i=0; i<adj[src].size(); i++) {
            // If an adjacent is not visited, then recur for that 
            // adjacent
			if (!visited[adj[src].get(i)]) {
				if (isCycleUndirectedUtil(adj[src].get(i), visited, src)) {
					return true;
				}
			} else if (visited[adj[src].get(i)] && adj[src].get(i) != parent) {
	            // If an adjacent is visited and not parent of current 
	            // vertex, then there is a cycle
				return true;
			}
		}
		return false;
	}
	
	/** 
	 * Clone an undirected graph.
	 * 
	 * @param node
	 * @return
	 */
	Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
	public UndirectedGraphNode clone(UndirectedGraphNode node) {
		if (node == null) {
			return null;
		}
		
		if (map.containsKey(node)) {
			return map.get(node);
		}
		
		UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
		map.put(node, clone);
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
	
	/**
	 * Test for a valid tree. Note: A tree is acyclic and all the nodes form one
	 * connected component. Given n nodes labeled from 0 to n-1 and a list of
	 * undirected edges (each edge is a pair of nodes), write a function to
	 * check whether these edges make up a valid tree.
	 * 
	 * @param n
	 * @param edges
	 * @return
	 */
    public boolean validTree(int n, int[][] edges) {
        List[] adj = new ArrayList[n];
        for (int i=0; i<n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i=0; i<edges.length; i++) {
            adj[edges[i][0]].add(edges[i][1]);
            adj[edges[i][1]].add(edges[i][0]);
        }
        
        boolean[] visited = new boolean[n];
        int cnt = 0;
        for (int i=0; i<n; i++) {
            if (!visited[i]) {
                if (isCycle(adj, i, visited, -1)) {
                    return false;
                }
                cnt++;
            }
        }
        
        for (int i=0; i<n; i++) {
            if (!visited[i] || cnt!=1) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isCycle(List[] adj, int src, boolean[] visited, int parent) {
        visited[src] = true;
        
        for (Integer vertex : (List<Integer>)adj[src]) {
            if (!visited[vertex]) {
                if (isCycle(adj, vertex, visited, src)) {
                    return true;
                }
            } else {
                if (vertex != parent) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Given a directed, acyclic graph of N nodes.  Find all possible paths from node 0 to node N-1, and return them in any order.

		The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.  graph[i] is a list of all nodes j for which the edge (i, j) exists.
		
		Example:
		Input: [[1,2], [3], [3], []] 
		Output: [[0,1,3],[0,2,3]] 
		Explanation: The graph looks like this:
		0--->1
		|    |
		v    v
		2--->3
		There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
     * @param graph
     * @return
     */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        int n = graph.length;
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        boolean[] visited = new boolean[n];
        allPaths(graph, 0, n-1, path, res, visited);
        return res;
    }
    
    private void allPaths(int[][] grid,  int src, int dest, List<Integer> path, List<List<Integer>> res, boolean[] visited) {
        visited[src] = true;
        path.add(src);
        if (src == dest) {
            res.add(new ArrayList<Integer>(path));
            visited[src] = false;
            return;
        }
        
        for (Integer node : grid[src]) {
            if (!visited[node]) {
                allPaths(grid, node, dest, path, res, visited);
                path.remove(path.size()-1);
            }
        }
        visited[src] = false;
    }
}
