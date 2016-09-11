package numbers;

public class Heap {

	public static void main(String[] args) {
		Heap hp = new Heap();
		// kth smallest element in an array
		int[] arr = {7, 6, 2, 3, 13, 4};
		System.out.println("Kth smallest no. in the given array: " + hp.findKSmallest(arr, 4));
		
		// Merge k sorted arrays
	    int arr1[][] =  {{2, 6, 12, 34}, {1, 9, 20, 1000}, {23, 34, 90, 2000}};
	    System.out.println("Merge k sorted arrays:");
	    int[] res = hp.mergeKSortedArrays(arr1);
	    for (int ele : res) {
	    	System.out.print(ele + " ");
	    }
	    System.out.println();
		
	}
	
	public int findKSmallest(int[] arr, int k) {
		int n = arr.length;
		minHeap(arr);
		for (int i=0; i<k-1; i++) {
			arr[0] = arr[n-1];
			n--;
			heapify(arr, 0, n);
		}
		
		return arr[0];
	}
	
	private void minHeap(int[] arr) {
		int n = arr.length;
		int i = (n-1)/2;
		
		while(i>=0) {
			heapify(arr, i, n);
			i--;
		}
	}
	
	private void heapify(int[] arr, int i, int n) {
		int smallest = i;
		int left = (2*i)+1;
		int right = (2*i)+2;
		
		if (left < n && arr[left]<arr[smallest]) {
			smallest = left;
		}
		if (right < n && arr[right]<arr[smallest]) {
			smallest = right;
		}
		if (smallest != i) {
			int tmp = arr[i];
			arr[i] = arr[smallest];
			arr[smallest] = tmp;
			heapify(arr, smallest, n);
		}
	}
	
	public int[] mergeKSortedArrays(int[][] arr) {
		int k = arr.length;
		int n = arr[0].length;
		int[] output = new int[n*k];
		
		HeapNode[] hn = new HeapNode[k];
		
		for (int i=0; i<k; i++) {
			hn[i] = new HeapNode();
			hn[i].element = arr[i][0];
			hn[i].i = i;
			hn[i].j = 1;
		}
		
		minHeapNode(hn);
		
		for (int i=0; i<n*k; i++) {
			HeapNode root = hn[0];
			output[i] = root.element;
			if (root.j < n) {
				hn[0].element = arr[root.i][root.j];
				hn[0].j = root.j+1;
			} else {
				hn[0].element = Integer.MAX_VALUE;
			}
			heapifyHeapNode(hn, 0, k);
		}
		return output;
	}

	
	private void minHeapNode(HeapNode[] hn) {
		int n = hn.length;
		int i = (n-1)/2;
		
		while(i>=0) {
			heapifyHeapNode(hn, i, n);
			i--;
		}
	}
	
	private void heapifyHeapNode(HeapNode[] hn, int i, int n) {
		int smallest = i;
		int l = (2*i)+1;
		int r = (2*i)+2;
		
		if (l<n && hn[l].element < hn[smallest].element) {
			smallest = l;
		}
		if (r<n && hn[r].element < hn[smallest].element) {
			smallest = r;
		}
		if (smallest != i) {
			HeapNode tmp = hn[smallest];
			hn[smallest] = hn[i];
			hn[i] = tmp;
			heapifyHeapNode(hn, smallest, n);
		}
	}

	 class HeapNode {
		int element;
		int i;
		int j;
	}
}
