import java.util.Arrays;
import java.util.Random;

class SortingAlgorithms {
	private static final Random RAND = new Random(42);
	
	public static void bubbleSort(int[] a) {
		for(int i = 0; i < a.length; i++) {
			for(int j = 1; j < a.length - i; j++) {
				if(a[j - 1] > a[j]) {
					swap(a, j - 1, j);
				}
			}
		}
	}
	
	public static void bubbleSortOpt(int[] a) {
		for(int i = 0; i < a.length; i++) {
			boolean changed = false;
			for(int j = 1; j < a.length - i; j++) {
				if(a[j - 1] > a[j]) {
					swap(a, j - 1, j);
					changed = true;
				}
			}
			if(!changed) {
				break;
			}
		}
	}
	
	public void bogoSort(int[] a) {
		while(!isSorted(a)) {
			shuffle(a);
		}
	}
	
	public static void stoogeSort(int[] a) {
		stoogeSort(a, 0, a.length - 1);
	}
	
	private static void stoogeSort(int[] a, int min, int max) {
		if(min < max) {
			if(a[min] > a[max]) {
				swap(a, min, max);
			}
			int oneThird = (max - min + 1) / 3;
			if(oneThird >= 1) {
				stoogeSort(a, min, max - oneThird);
				stoogeSort(a, min + oneThird, max);
				stoogeSort(a, min, max - oneThird);
			}
		}
	}
	
	public static void selectionSort(int[] a) {
		for(int i = 0; i < a.length - 1; i++) {
			int min = i;
			for(int j = i + 1; j < a.length; j++) {
				if(a[j] < a[min]) {
					min = j;
				}
			}
			swap(a, i, min);
		}
	}
	
	public static void insertionSort(int[] a) {
		for(int i = 1; i < a.length; i++) {
			
			// slide elements right to make room for a[i]
			int tmp = a[i];
			int j = i;
			while(j >= 1 && a[j - 1] > tmp) {
				a[j] = a[j - 1];
				j--;
			}
			
			a[j] = tmp;
		}
	}
	
	public static void shellSort(int[] a) {
		for(int gap = a.length / 2; gap >= 1; gap /= 2) {
			for(int i = gap; i < a.length; i++) {
				
				// slide elements right by 'gap'
				// to make room for a[i]
				int tmp = a[i];
				int j = i;
				while(j >= gap && a[j - gap] > tmp) {
					a[j] = a[j - gap];
					j -= tmp;
				}
				a[j] = tmp;
			}
		}
	}
	
	public static void merge(int[] result, int[] left, int[] right) {
		int i1 = 0;
		int i2 = 0;
		
		for(int i = 0; i < result.length; i++) {
			if(i2 >= right.length || (i1 < left.length && left[i1] <= right[i2])) {
				result[i] = left[i1];
				i1++;
			} else {
				result[i] = right[i2];
				i2++;
			}
		}
	}
	
	public static void mergeSort(int[] a) {
		if(a.length >= 2) {
			// split array into two halves
			int[] left = Arrays.copyOfRange(a, 0, a.length/2);
			int[] right = Arrays.copyOfRange(a, a.length/2, a.length);
			
			// recursively sort the two halves
			mergeSort(left);
			mergeSort(right);
			
			// merge the sorted halves into a sorted whole
			merge(a, left, right);
		}
	}
	
	public static void quickSort(int[] a) {
		quickSort(a, 0, a.length - 1);
	}
	
	private static void quickSort(int[] a, int min, int max) {
		if(min >= max) {
			return;
		}
		
		// choose pivot; we'll use the first element (might be bad)
		int pivot = a[min];
		swap(a, min, max);		// move pivot to the end
		
		// partition the two sides of the array
		int middle = partition(a, min, max - 1, pivot);
		
		swap(a, middle, max);		// restore pivot to proper location
		
		// recursively sort the left and right partitions
		quickSort(a, min, middle - 1);
		quickSort(a, middle + 1, max);
	}
	
	/*
	public static void quickSortOpt(int[] a) {
		quickSortOpt(a, 0, a.length - 1);
	}
	
	private static void quickSortOpt(int[] a, int min, int max) {
		if(min >= max) {
			return;
		}
		
		// choose pivot; we'll use the median of a[min] a[mid] and a[max]
		int mid = (max+min)/2;
		int pivot;
		swap(a, min, max);		// move pivot to the end
		
		// partition the two sides of the array
		int middle = partition(a, min, max - 1, pivot);
		
		swap(a, middle, max);		// restore pivot to proper location
		
		// recursively sort the left and right partitions
		quickSort(a, min, middle - 1);
		quickSort(a, middle + 1, max);
	}*/
	
	// partitions a with elements < pivot on left and
	// elements > pivot on right;
	// returns index of element that should be swapped with pivot
	private static int partition(int[] a, int i, int j, int pivot) {
		while(i <= j) {
			// move index markers i,j toward center
			// until we find a pair of out-of-order elements
			while(i <= j && a[i] < pivot) { i++; }
			while(i <= j && a[j] > pivot) { j--; }
			
			if(i <= j) {
				swap(a, i, j);
				i++;
				j--;
			}
		}
		
		return i;
	}
	
	// Space Complexity: O(N)
	public static void heapSort(int[] a) {
		Queue<Integer> pq = new PriorityQueue<Integer>();
		for(int k : a) {
			pq.add(k);
		}
		
		int i = 0;
		while(!pq.isEmpty()) {
			a[i] = pq.remove();
			i++;
		}
	}
	
	public static void heapSortInplace(int[] a) {
		// turn a into a max heap
		for(int i = a.length / 2; i >= 0; i--) {
			bubbleDown(a, i, a.length - 1);
		}
		for(int i = a.length - 1; i > 0; i--) {
			swap(a, 0, i);				// remove max, move to end
			bubbleDown(a, 0, i - 1);
		}
	}
	
	// swaps a[hole] down with its larger child until in place
	private static void bubbleDown(int[] a, int hole, int max) {
		int tmp = a[hole];
		while(hole * 2 <= max) {
			// pick larger child
			int child = hole * 2;
			if(child != max && a[child + 1] > a[child]) {
				child++;
			}
			if(a[child] > tmp) {
				a[hole] = a[child];
			} else {
				break;
			}
			hole = child;
		}
		a[hole] = tmp;
	}
	
	// Precondition: all elements in a are in range 0 .. 999999
	public static void bucketSort(int[] a) {
		int[] counters = new int[1000000];
		for(int k : a) {
			counters[k]++;	// tally the counters
		}
		
		// put the counter information back into a
		int i = 0;
		for(int j = 0; j < counters.length; j++) {
			for(int k = 0; k < counters[j]; k++) {
				a[i] = j;
				i++;
			}
		}
	}
	
	// Works for any range of integers in a.
	public static void bucketSortOpt(int[] a) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for(int k : a) {
			max = Math.max(max, k);		// find range of values
			min = Math.min(min, k);		// stored in a
		}
		int[] counters = new int[max - min + 1];
		for(int k : a) {
			counters[k - min]++;
		}
		int i = 0;
		for(int j = 0; j < counters.length; j++) {
			for(int k = 0; k < counters[j]; k++) {
				a[i] = j + min;
				i++;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void radixSort(int[] a) {
		//initialize array of 10 queues for digit value 0-9
		Queue<Integer>[] buckets = (Queue<Integer>[]) new Queue[10];
		for(int i = 0; i < buckets.length; i++) {
			buckets[i] = new ArrayDeque<Integer>();
		}
		
		//copy to/from buckets repeatedly until sorted
		int digit = 1;
		while(toBuckets(a, digit, buckets)) {
			fromBuckets(a, buckets);
			digit++;
		}
	}
	
	// Organizes the integers in the array into the given array
	// of queues based on their digit at the given place.
	// For example, if digit == 2, uses the tens digit, so array
	// {343, 219, 841, 295} would be put in queues #4, 1, 4, 9.
	// Returns true if any elements have a non-zero digit value.
	private static boolean toBuckets(int[] a, int digit, Queue<Integer>[] buckets) {
		boolean nonZero = false;
		for(int element : a) {
			int which = kthDigit(element, digit);
			buckets[which].add(element);
			if(which != 0) {
				nonZero = true;
			}
		}
		return nonZero;
	}
	
	// Moves the data in the given array of queues back into the
	// given array, in ascending order by bucket.
	private static void fromBuckets(int[] a, Queue<Integer>[] buckets) {
		int i = 0;
		for(int j = 0; j < buckets.length; j++) {
			while(!buckets[j].isEmpty()) {
				a[i] = buckets[j].remove();
				i++;
			}
		}
	}
	
	// Returns the k'th least significant digit from the integer.
	// For example, kthDigit(9814728, 3) returns 7.
	private static final int kthDigit(int element, int k) {
		for(int i = 1; i <= k - 1; i++) {
			element = element / 10;
		}
		return element % 10;
	}
	
	private static void swap(int[] a, int i, int j) {
		if(i != j) {
			int tmp = a[i];
			a[i] = a[j];
			a[j] = tmp;
		}
	}
	
	private static void shuffle(int[] a) {
		for(int i = 0; i < a.length; i++) {
			// move element i to a random index in [i .. length - 1]
			int randomIndex = (int) (Math.random() * a.length - i);
			swap(a, i, i + randomIndex);
		}
	}
	
	private static boolean isSorted(int[] a) {
		for(int i = 0; i < a.length; i++) {
			if(a[i] > a[i + 1]) {
				return false;
			}
		}
		return true;
	}
	
	public static int[] createRandomArray(int length) {
		int[] a = new int[length];
		for(int i = 0; i < a.length; i++) {
			a[i] = RAND.nextInt(1000000000);
		}
		return a;
	}
	
	public static int[] createAscendingArray(int length) {
		int[] a = new int[length];
		for(int i = 0; i < a.length; i++) {
			a[i] = i;
		}
		return a;
	}
	
	public static int[] createDescendingArray(int length) {
		int[] a = new int[length];
		for(int i = 0; i < a.length; i++) {
			a[i] = a.length - 1 - i;
		}
		return a;
	}
}	
