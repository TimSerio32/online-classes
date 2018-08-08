class LinkedListClient {
	public static void testAppend(LinkedListInts list) {
		System.out.println("append:");
		list.append(42);
		list.append(24);
		list.append(17);
		list.append(28);
		System.out.println(list.toString() + " size: " + list.size());
		System.out.println();
	}
	
	public static void testPrepend(LinkedListInts list) {
		System.out.println("prepend:");
		list.prepend(4);
		list.prepend(3);
		list.prepend(2);
		list.prepend(2);
		list.prepend(1);
		System.out.println(list.toString() + " size: " + list.size());
		System.out.println();
	}
	
	public static void testGet(LinkedListInts list) {
		System.out.println("get:");
		System.out.println(list.get(8));
		System.out.println(list.get(3));
		System.out.println(list.get(2));
		System.out.println(list.get(1));
		System.out.println(list.get(0));
		System.out.println(list.toString() + " size: " + list.size());
		System.out.println();
	}
	
	public static void testRemove(LinkedListInts list) {
		System.out.println("remove:");
		list.remove(8);
		System.out.println(list.toString() + " size: " + list.size());
		list.remove(7);
		System.out.println(list.toString() + " size: " + list.size());
		list.remove(0);
		System.out.println(list.toString() + " size: " + list.size());
		System.out.println();
	}
	
	public static void testSet(LinkedListInts list) {
		System.out.println("set:");
		list.set(0, 1);
		System.out.println(list.toString() + " size: " + list.size());
		list.set(4, 7);
		System.out.println(list.toString() + " size: " + list.size());
		list.set(5, 8);
		System.out.println(list.toString() + " size: " + list.size());
		System.out.println();
	}
	
	public static void testInsert(LinkedListInts list) {
		System.out.println("insert:");
		list.insert(4, 5);
		System.out.println(list.toString() + " size: " + list.size());
		list.insert(5, 6);
		System.out.println(list.toString() + " size: " + list.size());
		list.insert(0, 0);
		System.out.println(list.toString() + " size: " + list.size());
		list.insert(9, 9);
		System.out.println(list.toString() + " size: " + list.size());
		System.out.println();
	}
	
	public static void testDelete(LinkedListInts list) {
		System.out.println("delete:");
		list.deleteWithValue(0);
		System.out.println(list.toString() + " size: " + list.size());
		list.deleteWithValue(1);
		System.out.println(list.toString() + " size: " + list.size());
		list.deleteWithValue(2);
		System.out.println(list.toString() + " size: " + list.size());
		list.deleteWithValue(3);
		System.out.println(list.toString() + " size: " + list.size());
		list.deleteWithValue(8);
		System.out.println(list.toString() + " size: " + list.size());
		System.out.println();
	}
	
	public static void testClear(LinkedListInts list) {
		System.out.println(list.isEmpty());
		list.clear();
		System.out.println(list.toString() + " size: " + list.size());
		System.out.println(list.isEmpty());
	}
	
	public static void main(String[] args) {
		LinkedListInts list = new LinkedListInts();
		LinkedListInts l1 = new LinkedListInts();
		LinkedListClient.testAppend(list);
		LinkedListClient.testPrepend(list);
		LinkedListClient.testGet(list);
		LinkedListClient.testRemove(list);
		LinkedListClient.testSet(list);
		LinkedListClient.testInsert(list);
		LinkedListClient.testDelete(list);
		LinkedListClient.testClear(list);
	}
}
