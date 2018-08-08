class Vector {
	private int[] myElements;
	private int mySize;
	// might not need because of java .length
	private int myCapacity;

	public Vector() {
		myElements = new int[10];
		mySize = 0;
		myCapacity = 10;
	}
	
	public void add(int value) {
		myElements[mySize] = value;
		mySize++;
	}

	public void clear() {
		
	}
	
	// const
	public int get(int index) {
		
	}
	
	public void insert(int index, int value) {
		for(int i = mySize; i > index; i--) {
			myElements[i] = myElements[i - 1];
		}
		myElements[index] = value;
		mySize++;
	}
	
	// const
	public bool isEmpty() {
		
	}
	
	public void remove(int index) {
		
	}
	
	public void set(int index, int value) {
		
	}
	
	// const
	public int size() {
		
	}
	
	// const
	public void debug() {
		
	}
}
