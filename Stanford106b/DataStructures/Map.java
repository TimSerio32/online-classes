public interface Map<K, V> {
	void clear();
	boolean containsKey(K key);
	V get(K key);
	boolean isEmpty();
	void put(K key, V value);
	void remove(int value);
	int size();
}
