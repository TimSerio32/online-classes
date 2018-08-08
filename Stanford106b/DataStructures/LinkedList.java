class LinkedList {
	private Node front;
	
	public LinkedList() {
		front = null;
	}
	
	// private inner class?
	private class Node {
		private int data;
		private Node next;
	}
	
	public void add(int value) {
		if(front == null) {
			Node oldfirst = front;
			front = new Node();
			front.data = value;
			front.next = oldfirst;
		} else {
			// walk to end
			Node current = front;
			while(current.next != null) {
				current = current.next;
			}
			// attach new node
			Node oldCurr = current.next;
			current.next = new Node();
			current.next.data = value;
			current.next.next = oldCurr;
			
		}
	}

	public void clear() {
		front = null;
	}
	
	public int get(int index) {
		Node current = front;
		for(int i = 0; i < index; i++) {
			current = current.next;
		}
		return current.data;
	}
	
	public void insert(int index, int value) {
		if(index == 0) {
			Node oldFront = front;
			front = new Node();
			front.data = value;
			front.next = oldFront;
		} else {
			Node current = front;
			for(int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			Node newNode = new Node();
			newNode.data = value;
			newNode.next = current.next;
			current.next = newNode;
		}
	}
	
	public boolean isEmpty() {
		return front == null;
	}
	
	public void remove(int index) {
		if(index == 0) {
			front = front.next;
		} else {
			Node current = front;
			for(int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			current.next = current.next.next;
		}
	}
	
	public void set(int index, int value) {
		Node current = front;
		for(int i = 0; i < index; i++) {
			current = current.next;
		}
		current.data = value;
	}
	
	public int size() {
		int res = 0;
		Node current = front;
		while(current != null) {
			res++;
		}
		return res;
	}
	
	public String toString() {
		String res = "{";
		Node curr = front;
		while(curr != null) {
			if(curr.next == null) {
				res += curr.data;
			} else {
				res += curr.data + ", ";
			}
		}
		res += "}";
		return res;
	}
}

