package graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 * 
 * Some courses may have prerequisites, for example to take course 0 you have to
 * first take course 1, which is expressed as a pair: [0,1]
 * 
 * Given the total number of courses and a list of prerequisite pairs, is it
 * possible for you to finish all courses?
 * 
 * Example 1:
 * 
 * Input: 2, [[1,0]] Output: true Explanation: There are a total of 2 courses to
 * take. To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 * 
 * Input: 2, [[1,0],[0,1]] Output: false Explanation: There are a total of 2
 * courses to take. To take course 1 you should have finished course 0, and to
 * take course 0 you should also have finished course 1. So it is impossible.
 * Note:
 * 
 * The input prerequisites is a graph represented by a list of edges, not
 * adjacency matrices. Read more about how a graph is represented. You may
 * assume that there are no duplicate edges in the input prerequisites. 
 * 
 * Hints:
 * This problem is equivalent to finding if a cycle exists in a directed graph.
 * If a cycle exists, no topological ordering exists and therefore it will be
 * impossible to take all courses.
 *
 */
public class CourseSchedule {
	
	public static void main(String[] args) {
		CourseSchedule course = new CourseSchedule();
		int[][] prereq1 = {{1,0}};
		System.out.println("Course completion status: " + course.courseCompletion(2, prereq1));
		int[][] prereq2 = {{1,0},{0,1}};
		System.out.println("Course completion status: " + course.courseCompletion(2, prereq2));
	}
	
	public boolean courseCompletion(int numCourses, int[][] courseDependency) {
		ArrayList[] arr = new ArrayList[numCourses];
		
		for (int i=0; i<numCourses; i++) {
			arr[i] = new ArrayList<Integer>();
		}
		for (int i=0; i<courseDependency.length; i++) {
			arr[courseDependency[i][1]].add(courseDependency[i][0]);
		}
		
		boolean[] visited = new boolean[numCourses];
		
		for (int i=0; i<numCourses; i++) {
			if (!dfs(i, visited, arr)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean dfs(int src, boolean[] visited, ArrayList[] arr) {
		if (visited[src] == true) {
			return false;
		}
		
		visited[src] = true;
		for (int i=0; i<arr[src].size(); i++) {
			if (!dfs((Integer)arr[src].get(i), visited, arr)) {
				return false;
			}
		}
		visited[src] = false;
		return true;
	}

}
