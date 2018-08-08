class BinarySearch {
	public static int binarySearch(int[] a, int value) {
		return binarySearch(a, value, 0, a.length - 1);
	}
	
	private static int binarySearch(int[] a, int value, int start, int end) {
		int mid = (end - start) / 2;
		if(start <= end) {
			if(value == a[mid]) {
				return mid;
			} else if(value < a[mid]) {
				return binarySearch(a, value, start, mid - 1);
			} else {
				return binarySearch(a, value, mid + 1, end);
			}
		} else {
			return -1;
		}
	}
	
	public static void main(String[] args) {
		int[] a = { 1, 2, 3, 4, 5, 6, 7 };
		int[] b = { 10, 20, 30, 40, 50, 60, 70 };
		System.out.println(BinarySearch.binarySearch(a, 4));
	}
}
