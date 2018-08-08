import java.util.*;

public class LinkedStack<E> implements Stack<E>, Iterable<E> {
	
	private Node<E> front;
	private int size = 0;
	
	public class Node<E> {
		public E data;
		public Node next;
		
		public Node(E data) {
			this.data = data;
		}
	}
	
	// Traverses the elements of the stack from top to bottom.
	/*private class LinkedStackIterator implements Iterator<E> {
		private Node position; // current position in list
		
		public LinkedStackIterator() {
			position = front;
		}
		
		public boolean hasNext() {
			return position != null;
		}
		
		public E next() {
			E result = position.data;
			position = position.next;
			return result;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}*/
	
	public void clear() {
		front = null;
		size = 0;
	}
	
	public boolean isEmpty() {
		return front == null;
	}
	
	public E peek() {
		if(size == 0) {
			throw new IllegalStateException("The stack is empty.");
		}
		E top = front.data;
		return top;
	}
	
	public E pop() {
		if(size == 0) {
			throw new IllegalStateException("The stack is empty.");
		}
		E top = front.data;
		front = front.next;
		size--;
		return top;
	}
	
	public void push(E value) {
		Node newNode = new Node(value);
		newNode.next = front;
		front = newNode;
		size++;
	}
	
	public int size() {
		return size;
	}
	
	public Iterator<E> iterator() {
		Iterator<E> it = Iterator<E>() {
			private Node position; // current position in list
			
			@Override
			public boolean hasNext() {
				return position != null;
			}
			
			@Override
			public E next() {
				E result = position.data;
				position = position.next;
				return result;
			}
			
			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
		return it;
	}
	
	public String toString() {
		String res = "[";
		Node tmp = front;
		while(tmp != null) {
			res += " " + tmp.data;
			tmp = tmp.next;
		}
		res += " ]";
		return res;
	}
	
	public static void main(String[] args) {
		Stack i = new LinkedStack();
		i.push(4);
		i.push(3);
		i.push(2);
		i.push(1);
		for(E e : i) {
			System.out.println(e);
		}
		System.out.println(i.toString() + " size: " + i.size());
		i.pop();
		System.out.println(i.toString() + " size: " + i.size());
		i.clear();
		System.out.println(i.toString() + " size: " + i.size());
		i.pop();
	}
}
