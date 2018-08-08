public class ChainingHashSet<E> implements Set<E> {
	// array of linked lists;
		// elements[i] = front of list #i (null if empty)
		private Node[] elements;
		private int size;
		
		// constructs new empty set
		public ChainingHashSet() {
			elements = (Node[]) new ChainingHashSet.Node[10];
			size = 0;
		}
		
		public class Node {
			public E data;
			public Node next;
			
			public Node(E value) {
				this.data = value;
			}
		}
		
		// hash function maps values to indexes
		private int hash(E e) {
			return Math.abs(e.hashCode()) % elements.length;
		}
		
		public void add(E value) {
			if(!contains(value)) {
				int h = hash(value);
				Node newNode = new ChainingHashSet.Node(value);
				newNode.next = elements[h];
				elements[h] = newNode;
				size++;
			}
		}
		
		public boolean contains(E value) {
			Node current = elements[hash(value)];
			while(current != null) {
				if(current.data.equals(value)) {
					return true;
				}
				current = current.next;
			}
			return false;
		}
		
		public void clear() {
			elements = new ChainingHashSet.Node[10];
			size = 0;
		}
		
		public boolean isEmpty() {
			return size == 0;
		}
		
		public void remove(E value) {
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
		
		public static void main(String[] args) {
			Set<Integer> s = new ChainingHashSet<Integer>();
			Integer one = 45;
			Integer two = 68;
			Integer three = 56;
			Integer four = 23;
			Integer five = 45;
			Integer six = 55;
			s.add(one);
			s.add(two);
			s.add(three);
			s.add(four);
			s.add(five);
			s.add(six);
			System.out.println(s.size());
			s.remove(four);
			System.out.println(s.size());
			System.out.println(s.contains(two));
			s.clear();
			System.out.println(s.size());
		}
}
