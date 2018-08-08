public class LinkedListInts {
	public class Node {
		Node next;
		int data;
		public Node(int data) {
			this.data = data;
		}
	}

	Node front;
	
	public void append(int data) {
		if(front == null) {
			front = new Node(data);
			return;
		}
		Node current = front;
		while(current.next != null) {
			current = current.next;
		}
		current.next = new Node(data);
	}
	
	public void prepend(int data) {
		Node newFront = new Node(data);
		newFront.next = front;
		front = newFront;
	}
	
	public void deleteWithValue(int data) {
		if(front == null) {
			return;
		}
		if(front.data == data) {
			front = front.next;
			return;
		}
		Node current = front;
		while(current.next != null) {
			if(current.next.data == data) {
				current.next = current.next.next;
				return;
			}
			current = current.next;
		}
	}
	
	public int get(int index) {
		Node current = front;
		for(int i = 0; i < index; i++) {
			current = current.next;
		}
		return current.data;
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
	
	public void insert(int index, int value) {
		if(index == 0) {
			Node oldFront = front;
			front = new Node(value);
			front.next = oldFront;
			
		} else {
			Node current = front;
			for(int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			Node newNode = new Node(value);
			newNode.next = current.next;
			current.next = newNode;
		}
	}
	
	public void clear() {
		front = null;
	}
	
	public boolean isEmpty() {
		return front == null;
	}
	
	public int size() {
		int res = 0;
		Node current = front;
		while(current != null) {
			res++;
			current = current.next;
		}
		return res;
	}
	
	public String toString() {
		String res = "{";
		Node current = this.front;
		while(current != null) {
			if(current.next == null) {
				res += current.data;
			} else {
				res += current.data + " ";
			}
			current = current.next;
		}
		res += "}";
		return res;
	}
}
	

		/*private ListNode front;
		
		public LinkedListInts() {
			front = null;
		}
		
		// private inner class?
		public class ListNode {
			public int data;
			public ListNode next;
			
			public ListNode(int data) {
				this.data = data;
				this.next = next;
			}
			
//			public ListNode(int data, ListNode next) {
//				this.data = data;
//				this.next = next;
//			}
		}
		
		public void add(int value) {
			if(front == null) {
				front = new ListNode(value);
				return;
			}
			ListNode current = front;
			while(current.next != null) {
				current = current.next;
			}
			current.next = new ListNode(value);
			
			
//			if(front == null) {
//				front = new ListNode(value, front);
//			} else {
//				// walk to end
//				ListNode current = front;
//				while(current.next != null) {
//					current = current.next;
//				}
//				// attach new node
//				current.next = new ListNode(value, null);
//				
//			}
		}

		public void clear() {
			front = null;
		}
		
		public int get(int index) {
			ListNode current = front;
			for(int i = 0; i < index; i++) {
				current = current.next;
			}
			return current.data;
		}
		
		public void insert(int index, int value) {
			if(index == 0) {
				//ListNode oldFront = front;
				//front = new ListNode(value, null);
				front = new ListNode(value);
			} else {
				ListNode current = front;
				for(int i = 0; i < index - 1; i++) {
					current = current.next;
				}
				//ListNode newNode = new ListNode(value, current.next);
				ListNode newNode = new ListNode(value);
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
				ListNode current = front;
				for(int i = 0; i < index - 1; i++) {
					current = current.next;
				}
				current.next = current.next.next;
			}
		}
		
		public void set(int index, int value) {
			ListNode current = front;
			for(int i = 0; i < index; i++) {
				current = current.next;
			}
			current.data = value;
		}
		
		public int size() {
			int res = 0;
			ListNode current = front;
			while(current.next != null) {
				current = current.next;
				res++;
			}
			return res;
		}
		
		public String toString() {
			String res = "{";
			ListNode curr = front;
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
}*/
