class PrintDecimal {
	public static void printDecimal(int digits) {
		printDecimal(digits, "");
	}
	
	private static void printDecimal(int digits, String prefix) {
		// System.out.println("printDecimal(" + digits + ", " + prefix + ")");
		if(digits == 0) {
			// base case
			System.out.println(prefix);
		} else {
			// recursive case
			for(int i = 0; i <= 9; i++) {
				printDecimal(digits - 1, prefix + i);
			}
		}
	}
	
	public static void main(String[] args) {
		PrintDecimal.printDecimal(2);
	}
}
