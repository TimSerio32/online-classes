class HashSetClient {
	public static void main(String[] args) {
		int[] elements = { 41, 3, 24, 35, 63, 24, 15, 41, 3, 24};
		
		System.out.println("add:");
		HashSet set = new HashSet();
		for(int n: elements) {
			set.add(n);
		}
		
		set.printStructure();
		
		System.out.println("remove:");
		
		set.remove(3);
		set.remove(15);
		
		set.printStructure();
	}
}
