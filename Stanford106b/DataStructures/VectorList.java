import java.util.Arrays;

class VectorList<T> {
	private T[] myElements;
	private int mySize;
	private int myCapacity;

	public VectorList() {
		myElements = (T[]) new Object[10];
		mySize = 0;
		myCapacity = 10;
	}
	
	public void add(T value) {
		if(mySize == myCapacity) {
			myCapacity = 2 * myElements.length;
			resize(myCapacity);
		}
		myElements[mySize] = value;
		mySize++;
	}

	public void clear() {
		myElements = (T[]) new Object[10];
		mySize = 0;
		myCapacity = 10;
	}
	
	public T get(int index) {
		return (T)myElements[index];
	}
	
	public void insert(int index, T value) {
		if(mySize == myCapacity) {
			myCapacity = 2 * myElements.length;
			resize(myCapacity);
		}
		// If not at end of list, insert value and shift all characters with indexes after it down by 1
		if(index != mySize) {
			for(int i = mySize + 1; i > index; i--) {
				myElements[i] = myElements[i - 1];
			}
			myElements[index] = value;
		} else {
			// End of list
			myElements[index] = value;
		}
		mySize++;
	}
	
	public boolean isEmpty() {
		return mySize == 0;
	}
	
	public void remove(int index) {
        myElements[index] = null;
        mySize--;
        // Arrays.copyOf(myElements, mySize);
        
        // 
        
        T[] arr = (T[]) new Object[mySize];
        int count = -1;
        for(T t: myElements) {
        	if(t != null) {
        		arr[++count] = t;
        	}
        }
        
        myElements = Arrays.copyOf(arr, myCapacity);
		
		/*for(int i = index; i < mySize; i++) {
			System.arraycopy(myElements, i + 1, myElements, i, myElements.length - 1 - i);
		}
		mySize--;*/
		if(mySize > 0 && mySize == myElements.length/4) {
			myCapacity = myElements.length/2;
			resize(myCapacity);
		}
	}
	
	public void set(int index, T value) {
		myElements[index] = value;
	}
	
	public int size() {
		return mySize;
	}
	
	public int capacity() {
		return myCapacity;
	}
	
	
	private void resize(int capacity) { 
		T[] bigger = (T[]) new Object[capacity];
		for(int i = 0; i < mySize; i++) {
			bigger[i] = myElements[i];
		}
		myElements = bigger;
	}
	
	public String toString() {
		String res = "{";
		for(int i = 0; i < this.size();  i++) {
			if(i == this.size() - 1) {
				res += this.get(i);
			} else {
				res += this.get(i) + ", ";
			}
		}
		res += "}";
		return res;
	}
}
