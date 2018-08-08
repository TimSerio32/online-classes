public interface Queue<E> {
	void clear();
	boolean isEmpty();
	E peek();
	E remove(); 		// remove from back
	void add(E value); 	// add to front
	int size();
}
