import java.util.*;

public class ArrayStack<E> implements Stack<E> {
	private E[] elements;
	private int size;
	
	public ArrayStack() {
		elements = (E[]) new Object[10];
		size = 0;
	}
	
	// Traverses the elements of the stack from top to bottom.
	private class ArrayStackIterator implements Iterator<E> {
		private int index;
			
		public ArrayStackIterator() {
			index = size - 1;
		}
			
		public boolean hasNext() {
			return index >= 0;
		}
			
		public E next() {
			E result = elements[index];
			index--;
			return result;
		}
			
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	public void push(E value) {
		if(size == elements.length) {
			elements = Arrays.copyOf(elements, 2 * size);
		}
		elements[size] = value;
		size++;
	}
	
	public E pop() {
		if(size == 0) {
			throw new IllegalStateException("The stack is empty.");
		}
		
		// Save value and delete value
		E res = elements[size - 1];
		elements[size - 1] = null;
		size--;
        
		return res;
	}
	
	public E peek() {
		if(size == 0) {
			throw new IllegalStateException("The stack is empty.");
		}
		E res = elements[size - 1];
		return res;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		elements = (E[]) new Object[10];
		size = 0;
	}
	
	public String toString() {
		String res = "[";
		for(int i = 0; i < size; i++) {
			res += " " + elements[i];
		}
		res += " ]";
		return res;
	}
	
	public static void main(String[] args) {
		Stack i = new ArrayStack();
		i.push(4);
		i.push(3);
		i.push(2);
		i.push(1);
		System.out.println(i.toString() + " size: " + i.size());
		i.pop();
		System.out.println(i.toString() + " size: " + i.size());
		i.clear();
		System.out.println(i.toString() + " size: " + i.size());
		i.pop();
	}
}
