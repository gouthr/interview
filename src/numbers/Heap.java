package numbers;

public class Heap {

	public static void main(String[] args) {
		Heap hp = new Heap();
		// kth smallest element in an array
		int[] arr = {7, 6, 2, 3, 13, 4};
		System.out.println("Kth smallest no. in the given array: " + hp.findKSmallest(arr, 4));
	}
	
	public int findKSmallest(int[] arr, int k) {
		int n = arr.length;
		minHeap(arr);
		for (int i=0; i<k-1; i++) {
			//int min = arr[0];
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

}
