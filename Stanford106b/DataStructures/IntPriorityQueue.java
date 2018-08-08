public interface IntPriorityQueue {
	void add(int value);
	void clear();
	boolean isEmpty();
	int peek(); 		// return min element
	int remove(); 		// remove/return min element
	int size();
}
