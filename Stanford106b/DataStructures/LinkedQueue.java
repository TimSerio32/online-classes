public class LinkedQueue<E> implements Queue<E> {
	
	private Node<E> front;
	private Node<E> back;
	private int size = 0;
	
	public class Node<E> {
		public E data;
		public Node next;
		public Node prev;
		
		public Node() {
			
		}
		
		public Node(E data) {
			this.data = data;
		}
	}
	
	public LinkedQueue() {
		front = new Node();
		back = new Node();
		
	}
	
	public void clear() {
		front.next = null;
		back.prev = null;
		size = 0;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public E peek() {
		if(size == 0) {
			throw new IllegalStateException("The stack is empty.");
		}
		E top = front.data;
		return top;
	}
	
	public E remove() {
		if(size == 0) {
			throw new IllegalStateException("The stack is empty.");
		}
		E top = front.data;
		front.next = front.next.next;
		size--;
		return top;
	}
	
	public void add(E value) {
		Node newNode = new Node(value);
		if(front.next == null) {
			front.next = newNode;
			back.prev = newNode;
			newNode.next = back;
			newNode.prev = front;
		} else {
			Node oldNode = back.prev;
			back.prev = newNode;
			oldNode.next = newNode;
			newNode.next = back;
			newNode.prev = oldNode;
		}
		size++;
	}
	
	public int size() {
		return size;
	}
	
	public String toString() {
		String res = "[";
		Node tmp = front;
		while(tmp != null) {
			if(tmp.data != null) {
				res += " " + tmp.data;
			}
			tmp = tmp.next;
		}
		res += " ]";
		return res;
	}
	
	public static void main(String[] args) {
		Queue<Integer> i = new LinkedQueue<Integer>();
		Integer one = 4;
		Integer two = 3;
		Integer three = 2;
		Integer four = 1;
		i.add(one);
		i.add(two);
		i.add(three);
		i.add(four);
		System.out.println(i.toString() + " size: " + i.size());
		i.remove();
		System.out.println(i.toString() + " size: " + i.size());
		i.clear();
		System.out.println(i.toString() + " size: " + i.size());
		i.remove();
	}
}
