class VectorClient {
	public static void testAdd(VectorList<Integer> vector) {
		System.out.println("add:");
		Integer one = 42;
		Integer two = -5;
		Integer three = 17;
		Integer four = 28;
		vector.add(one);
		vector.add(two);
		vector.add(three);
		vector.add(four);
		System.out.println(vector + ", size " + vector.size() + ", capacity " + vector.capacity());
		System.out.println();
		
		System.out.println("insert:");
		Integer oneT = 111;
		Integer twoT = 444;
		Integer threeT = 0;
		Integer fourT = 7777;
		Integer fiveT = 8888;
		vector.insert(1, oneT);
		System.out.println(vector + ", size " + vector.size() + ", capacity " + vector.capacity());
		vector.insert(4, twoT);
		System.out.println(vector + ", size " + vector.size() + ", capacity " + vector.capacity());
		vector.insert(0, threeT);
		System.out.println(vector + ", size " + vector.size() + ", capacity " + vector.capacity());
		vector.insert(7, fourT);
		System.out.println(vector + ", size " + vector.size() + ", capacity " + vector.capacity());
		vector.insert(8, fiveT);
		System.out.println(vector + ", size " + vector.size() + ", capacity " + vector.capacity());
		System.out.println();
	}
	
	public static void testGrow(VectorList<Integer> vector) {
		System.out.println("grow:");
		Integer oneTh = 9999;
		Integer twoTh = 3333;
		vector.insert(9, oneTh);
		System.out.println(vector + ", size " + vector.size() + ", capacity " + vector.capacity());
		vector.insert(3, twoTh);
		System.out.println(vector + ", size " + vector.size() + ", capacity " + vector.capacity());
		System.out.println();
	}
	
	public static void testRemove(VectorList<Integer> vector) {
		System.out.println("remove:");
		vector.remove(9);
		System.out.println(vector + ", size " + vector.size());
		vector.remove(3);
		System.out.println(vector + ", size " + vector.size());
		vector.remove(5);
		System.out.println(vector + ", size " + vector.size());
		vector.remove(1);
		System.out.println(vector + ", size " + vector.size());
		System.out.println();
	}
	
	public static void testShrink(VectorList<Integer> vector) {
		System.out.println("shrink:");
		Integer oneF = 3333;
		Integer twoF = 0000;
		Integer threeF = 0101;
		Integer fourF = 1010;
		Integer fiveF = 4444;
		Integer sixF = 7777;
		Integer sevenF = 2323;
		vector.remove(0);
		System.out.println(vector + ", size " + vector.size() + ", capacity " + vector.capacity());
		vector.insert(3, oneF);
		System.out.println(vector + ", size " + vector.size() + ", capacity " + vector.capacity());
		vector.insert(0, twoF);
		System.out.println(vector + ", size " + vector.size() + ", capacity " + vector.capacity());
		vector.insert(1, threeF);
		System.out.println(vector + ", size " + vector.size() + ", capacity " + vector.capacity());
		vector.insert(2, fourF);
		System.out.println(vector + ", size " + vector.size() + ", capacity " + vector.capacity());
		vector.insert(4, fiveF);
		System.out.println(vector + ", size " + vector.size() + ", capacity " + vector.capacity());
		vector.insert(6, sixF);
		System.out.println(vector + ", size " + vector.size() + ", capacity " + vector.capacity());
		vector.insert(7, sevenF);
		System.out.println(vector + ", size " + vector.size() + ", capacity " + vector.capacity());
		vector.remove(0);
		System.out.println(vector + ", size " + vector.size() + ", capacity " + vector.capacity());
		vector.remove(0);
		System.out.println(vector + ", size " + vector.size() + ", capacity " + vector.capacity());
		vector.remove(0);
		System.out.println(vector + ", size " + vector.size() + ", capacity " + vector.capacity());
		vector.remove(0);
		System.out.println(vector + ", size " + vector.size() + ", capacity " + vector.capacity());
		vector.remove(0);
		System.out.println(vector + ", size " + vector.size() + ", capacity " + vector.capacity());
		vector.remove(0);
		System.out.println(vector + ", size " + vector.size() + ", capacity " + vector.capacity());
		vector.remove(0);
		System.out.println(vector + ", size " + vector.size() + ", capacity " + vector.capacity());
		vector.remove(0);
		System.out.println(vector + ", size " + vector.size() + ", capacity " + vector.capacity());
		vector.remove(0);
		System.out.println(vector + ", size " + vector.size() + ", capacity " + vector.capacity());
		System.out.println();
	}
	
	public static void testClear(VectorList<Integer> vector) {
		System.out.println("clear:");
		Integer one = 42;
		Integer two = -5;
		Integer three = 17;
		Integer four = 28;
		vector.add(one);
		vector.add(two);
		vector.add(three);
		vector.add(four);
		System.out.println(vector + ", size " + vector.size() + ", capacity " + vector.capacity());
		vector.clear();
		System.out.println(vector + ", size " + vector.size() + ", capacity " + vector.capacity());
		System.out.println();
	}
	
	public static void main(String[] args) {
		VectorList<Integer> vector = new VectorList<Integer>();
		VectorList<Integer> v1 = new VectorList<Integer>();
		VectorClient.testAdd(vector);
		VectorClient.testGrow(vector);
		VectorClient.testRemove(vector);
		VectorClient.testShrink(vector);
		VectorClient.testClear(v1);
	}
}
