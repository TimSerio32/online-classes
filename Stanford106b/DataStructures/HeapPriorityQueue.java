import java.util.*;

public class HeapPriorityQueue<E extends Comparable<E>> implements PriorityQueue<E> {
	private E[] elements;
	private int size;
	
	// constructs a new empty priority queue
	public HeapPriorityQueue() {
		elements = (E[]) new Comparable[10];
		size = 0;
	}
	
	public HeapPriorityQueue(Comparator<E> comp) {
		elements = (E[]) new Comparable[10];
		size = 0;
	}
	
	private int parent(int index) { return index/2; }
	private int leftChild(int index) { return index*2; }
	private int rightChild(int index) { return index*2 + 1; }
	private boolean hasParent(int index) { return index > 1; }
	private boolean hasLeftChild(int index) { return leftChild(index) <= size; }
	private boolean hasRightChild(int index) { return rightChild(index) <= size; }
	private void swap(E[] a, int index1, int index2) {
		E temp = a[index1];
		a[index1] = a[index2];
		a[index2] = temp;
	}
	
	public void add(E value) {
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
			if(elements[index].compareTo(elements[parent]) < 0) {
				swap(elements, index, parent(index));
				index = parent(index);
			} else {
				found = true;		//found proper location; stop
			}
		}
		
		size++;
	}
	
	public void clear() {
		elements = (E[]) new Comparable[10];
		size = 0;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public E peek() {
		return elements[1];
	}
	
	// precondition: queue is not empty
	public E remove() {
		E res = elements[1];	
		elements[1] = elements[size];		// last leaf -> root
		size--;
		int index = 1;						
		boolean found = false;
		
		// "bubble down" to fix ordering
		while(!found && hasLeftChild(index)) {
			int left = leftChild(index);
			int right = rightChild(index);
			int child = left;
			if(hasRightChild(index) && elements[right].compareTo(elements[left]) < 0) {
				child = right;
			}
			if(elements[index].compareTo(elements[child]) > 0) {
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
	
	public String toString() {
		String res = "]";
		for(int i = 1; i <= size; i++) {
			res += " " + elements[i];
		}
		res += " ]";
		return res;
	}
	
	public static void main(String[] args) {
		PriorityQueue<Integer> pq = new HeapPriorityQueue<Integer>();
		Integer one = 54;
		Integer two = 53;
		Integer three = 52;
		Integer four = 51;
		Integer five = 50;
		Integer six = 49;
		pq.add(one);
		pq.add(two);
		pq.add(three);
		pq.add(four);
		pq.add(five);
		pq.add(six);
		while (!pq.isEmpty()) {
			System.out.print(pq.remove() + " ");
		}
		System.out.println();
	}
}
