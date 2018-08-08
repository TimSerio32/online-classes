class HeapSort {
	public static void heapSort(int[] a) {
		PriorityQueue<Integer> pq = new HeapPriorityQueue<Integer>();
		for(Integer n : a) {
			pq.add(n);
		}
		for(int i = 0; i < a.length; i++) {
			a[i] = pq.remove();
		}
	}
	
	
	private static int parent(int index) { return index/2; }
	private static int leftChild(int index) { return index*2; }
	private static int rightChild(int index) { return index*2 + 1; }
	private static boolean hasParent(int index) { return index > 1; }
	private static boolean hasLeftChild(int[] a, int index) { return leftChild(index) <= a.length; }
	private static boolean hasRightChild(int[] a, int index) { return rightChild(index) <= a.length; }
	private static void swap(int[] a, int index1, int index2) {
		int temp = a[index1 - 1];
		a[index1 - 1] = a[index2 - 1];
		a[index2 - 1] = temp;
	}

	private static void bubbleDown(int[] a, int index, int n) {						
		// "bubble down" to fix ordering
		while(2 * index <= n) {
			int j = 2 * index;
			if(j < n && a[j - 1] < a[j]) {
				j++;
			}
			if(a[index - 1] > a[j - 1]) {
				break;
			}
			swap(a, index, j);
			index = j;
		}
	}
	
	public static void heapSortInplace(int[] a) {
		// Treat a as a max-heap whose data starts at 0 index
		//	- "bubble down" each non-leaf node, starting from the last one (n/2)
		int n = a.length;
		for(int k = n/2; k >= 1; k--) {
			bubbleDown(a, k, n);
		}
		
		// Call remove until the heap is empty
		//	- when you remove an element, move it to the end of the array
		while(n > 1) {
			swap(a, 0, n--);
			bubbleDown(a, 0, n);
		}
	}

	public static void main(String[] args) {
		int[] a = { 5, 4, 3, 2, 1 };
		HeapSort.heapSortInplace(a);
		String res = "[";
		for(int i = 0; i < a.length; i++) {
			res += " " + a[i];	
		}
		res += " ]";
		System.out.println(res);	
	}	
}
