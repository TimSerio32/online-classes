public interface Stack<E> {
	void clear();
	boolean isEmpty();
	E peek();
	E pop();
	void push(E value);
	int size();
}
