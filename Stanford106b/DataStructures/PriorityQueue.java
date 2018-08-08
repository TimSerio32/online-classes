public interface PriorityQueue<E> {
	void add(E value);
	void clear();
	boolean isEmpty();
	E peek(); 		// return min element
	E remove(); 		// remove/return min element
	int size();
}
