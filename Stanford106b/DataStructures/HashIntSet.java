public class HashIntSet implements IntSet {
	private static final int REMOVED = -999;
	private int[] elements;
	private int size;

	public HashIntSet() {
		// initialize set with prime number to reduce collisions
		elements = new int[13];
		size = 0;
	}
	
	private int hash(int i) {
		return Math.abs(i) % elements.length;
	}
	
	private void rehash() {
		int[] old = elements;
		elements = new int[2 * old.length];
		size = 0;
		for(int value : old) {
			if(value != 0 && value != REMOVED) {
				add(value);
			}
		}
	}

	public void add(int value) {
		if((double) size / elements.length >= 0.75) {
			rehash();
		}
		
		int h = hash(value);
		while(elements[h] != 0 && elements[h] != value && elements[h] != REMOVED) {
			h = (h + 1) % elements.length;				// linear  probing for empty slot
		} 
		if(elements[h] != value) {
			elements[h] = value;						// avoid duplicates
			size++;
		}
	}

	public boolean contains(int value) {
		int h = hash(value);
		while(elements[h] != 0 && elements[h] != value) {
			h = (h + 1) % elements.length;
		}
		return elements[h] == value;
	}

	public void remove(int value) {
		int h = hash(value);
		while(elements[h] != 0 && elements[h] != value) {
			h = (h + 1) % elements.length;				// linear  probing for empty slot
		} 
		if(elements[h] == value) {
			elements[h] = REMOVED;						// removed flag value
			size--;
		}
	}
	
	public void clear() {
		elements = new int[13];
		size = 0;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}

	public String toString() {
		String res = "[";
		for(int i = 0; i < elements.length; i++) {
			if(elements[i] != 0 && elements[i] != REMOVED) {
				// res += " " + elements[i];
				res += " " + elements[i];
			}
		}
		res += " ]";
		return res;
	}
	
	public static void main(String[] args) {
		IntSet i = new HashIntSet();
		i.add(24);
		i.add(54);
		i.add(78);
		i.add(95);
		i.add(76);
		System.out.println(i);
		System.out.println("contains(24): " + i.contains(24));
		i.remove(24);
		System.out.println("contains(24): " + i.contains(24));
		System.out.println("isEmpty(): " + i.isEmpty());
		System.out.println(i);
		System.out.println("size(): " + i.size());
		i.clear();
		System.out.println("size(): " + i.size());
		System.out.println("isEmpty(): " + i.isEmpty());
	}
}
