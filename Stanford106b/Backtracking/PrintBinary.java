class PrintBinary {
	public static void printBinary(int digits) {
		printBinary(digits, "");
	}
	
	private static void printBinary(int digits, String prefix) {
		// System.out.println("printBinary(" + digits + ", " + prefix + ")");
		if(digits == 0) {
			// base case
			System.out.println(prefix);
		} else {
			// recursive case
			printBinary(digits - 1, prefix + "0");
			printBinary(digits - 1, prefix + "1");
		}
	}
	
	// printBinary(3, "")
		// printBinary(2, "0")
			// printBinary(1, "00")
				// printBinary(0, "000")
				// printBinary(0, "001")
			// printBinary(1, "01")
		// printBinary(2, "1")
	
	public static void main(String[] args) {
		PrintBinary.printBinary(3);
	}
}
