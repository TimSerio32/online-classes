class TreeSetClient {
	public static void main(String[] args) {
		TreeSet<Integer> tree = new TreeSet<Integer>();
		Integer i = 55;
		Integer i1 = 29;
		Integer i2 = 87;
		Integer i3 = -3;
		Integer i4 = 42;
		Integer i5 = 60;
		Integer i6 = 91;
		Integer i7 = 17;
		Integer i8 = 6;
		Integer i9 = -1;
		tree.add(i);
		tree.add(i1);
		tree.add(i2);
		tree.add(i3);
		tree.add(i4);
		tree.add(i5);
		tree.add(i6);
		
		System.out.println("print:");
		tree.print();
		System.out.println();
		System.out.println("height: " + tree.height());
		System.out.println("size: " + tree.size());
		
		System.out.println("printSideways:");
		tree.printSideways();
		System.out.println();
		
		System.out.println("contains 87:" + tree.contains(i2));
		System.out.println("contains 42:" + tree.contains(i4));
		System.out.println("contains 55:" + tree.contains(i));
		System.out.println("contains -3:" + tree.contains(i3));
		System.out.println("contains 17:" + tree.contains(i7));
		System.out.println("contains 6:" + tree.contains(i8));
		System.out.println("contains -1:" + tree.contains(i9));
		System.out.println();
		
		System.out.println("remove:");
		tree.remove(i6);
		tree.remove(i1);
		tree.remove(i);
		
		System.out.println("after removing things:");
		tree.print();
		tree.printSideways();
		System.out.println("height: " + tree.height());
		System.out.println("size: " + tree.size());
	}
}
