import java.util.Arrays;

public class HeapIntPriorityQueue implements IntPriorityQueue {
	private int[] elements;
	private int size;
	
	// constructs a new empty priority queue
	public HeapIntPriorityQueue() {
		elements = new int[10];
		size = 0;
	}
	
	private int parent(int index) { return index/2; }
	private int leftChild(int index) { return index*2; }
	private int rightChild(int index) { return index*2 + 1; }
	private boolean hasParent(int index) { return index > 1; }
	private boolean hasLeftChild(int index) { return leftChild(index) <= size; }
	private boolean hasRightChild(int index) { return rightChild(index) <= size; }
	private void swap(int[] a, int index1, int index2) {
		int temp = a[index1];
		a[index1] = a[index2];
		a[index2] = temp;
	}
	
	public void add(int value) {
		if(size == elements.length - 1) {
			// resize to enlarge the heap if necessary
			elements = Arrays.copyOf(elements, 2 * elements.length);
		}
		
		elements[size + 1] = value;			// add as rightmost leaf
		
		// "bubble up" as necessary to fix ordering
		int index = size + 1;
		boolean found = false;
		while(!found && hasParent(index)) {
			int parent = parent(index);
			if(elements[index] < elements[parent]) {
				swap(elements, index, parent(index));
				index = parent(index);
			} else {
				found = true;		//found proper location; stop
			}
		}
		
		size++;
	}
	
	void clear() {
		elements = new int[10];
		size = 0;
	}
	
	boolean isEmpty() {
		return size == 0;
	}
	
	public int peek() {
		return elements[1];
	}
	
	// precondition: queue is not empty
	public int remove() {
		int res = elements[1];	
		elements[1] = elements[size];		// last leaf -> root
		size--;
		int index = 1;						
		boolean found = false;
		
		// "bubble down" to fix ordering
		while(!found && hasLeftChild(index)) {
			int left = leftChild(index);
			int right = rightChild(index);
			int child = left;
			if(hasRightChild(index) && elements[right] < elements[left]) {
				child = right;
			}
			if(elements[index] > elements[child]) {
				swap(elements, index, child);
				index = child;
			} else {
				found = true;			// found proper location; stop
			}
		}
		return res;
	}
	
	public int size() {
		return size;
	}
}
