public class ChainingHashIntSet implements IntSet {
	// array of linked lists;
	// elements[i] = front of list #i (null if empty)
	private Node[] elements;
	private int size;
	
	// constructs new empty set
	public ChainingHashIntSet() {
		elements = new Node[10];
		size = 0;
	}
	
	public class Node {
		public int data;
		public Node next;
	}
	
	// hash function maps values to indexes
	private int hash(int value) {
		return Math.abs(value) % elements.length;
	}
	
	public void add(int value) {
		if(!contains(value)) {
			int h = hash(value);
			Node newNode = new Node(value);
			newNode.next = elements[h];
			elements[h] = newNode;
			size++;
		}
	}
	
	public boolean contains(int value) {
		Node current = elements[hash(value)];
		while(current != null) {
			if(current.data == value) {
				return true;
			}
			current = current.next;
		}
		return false;
	}
	
	public void clear() {
		elements = new Node[10];
		size = 0;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void remove(int value) {
		int h = hash(value);
		if(elements[h] != null && elements[h].data == value) {
			elements[h] = elements[h].next;		// front case
			size--;
		} else {
			Node current = elements[h];
			while(current != null && current.next != null) {
				if(current.next.data == value) {
					current.next = current.next.next;
					size--;
					return;
				}
				current = current.next;
			}
		}
	}
	
	public int size() {
		return size;
	}
}
