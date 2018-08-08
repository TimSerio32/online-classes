class HashSet {
	private HashNode[] elements;
	private int capacity;
	private int mysize;
	
	public HashSet() {
		elements = new HashNode[10];
		capacity = 10;
		mysize = 0;
	}
	
	private int hashCode(int value) {
		return Math.abs(value) % capacity;
	}
	
	public void add(int value) {
		if(!contains(value)) {
			int bucket = hashCode(value);
			HashNode newNode = new HashNode(value);
			newNode.next = elements[bucket];
			elements[bucket] = newNode;
			mysize++;
		}
	}
	
	public void clear() {
		
	}
	
	public boolean contains(int value) {
		int bucket = hashCode(value);
		HashNode curr = elements[bucket];
		while(curr != null) {
			if(curr.data == value) {
				return true;
			}
			curr = curr.next;
		}
		return false;
	}
	
	public void printStructure() {
		for(int i = 0; i < capacity; i++) {
			System.out.println("[" + i + "]:");
			HashNode curr = elements[i];
			while(curr != null) {
				System.out.println("-> " + curr.data);
				curr = curr.next;
			}
			System.out.println(" /");
			System.out.println();
		}
		System.out.println("size = " + mysize);
	}
	
	public void remove(int value) {
		if(!contains(value)) {
			return;
		}
		int bucket = hashCode(value);
		if(elements[bucket].data == value) {
			// remove first element in chain
			elements[bucket] = elements[bucket].next;
			mysize--;
		} else {
			// remove non- first element in chain
			HashNode curr = elements[bucket];
			while(curr != null) {
				if(curr.next.data == value) {
					// remove current.next (James Bond)
					curr.next = curr.next.next;
				}
				curr = curr.next;
			}
			mysize--;
		}
	}
}
