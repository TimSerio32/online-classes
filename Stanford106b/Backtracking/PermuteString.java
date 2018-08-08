import java.util.*;

class PermuteString {
	public static void permuteString(String s) {
		Set<String> alreadyPrinted = new HashSet<String>();
		permuteString(s, "", alreadyPrinted);	
	}
	
	private static void indent(int n) {
		for(int i = 0; i < n; i++) {
			System.out.print("    ");
		}
	}
	
	private static void permuteString(String s, String chosen, Set<String> alreadyPrinted) {
		indent(chosen.length());
		System.out.println("permuteString(\"" + s + "\", \"" + chosen + "\")");
		if(s.isEmpty()) {
			if(!alreadyPrinted.contains(chosen)) {
				System.out.println(chosen);
				alreadyPrinted.add(chosen);
			}
		} else {
			for(int i = 0; i < s.length(); i++) {
				// choose
				char c = s.charAt(i);
				chosen += c;
				s = new StringBuilder(s).deleteCharAt(i).toString();
				
				// explore
				permuteString(s, chosen, alreadyPrinted);
				
				// unchoose
				s = new StringBuilder(s).insert(i, c).toString();
				chosen = new StringBuilder(chosen).deleteCharAt(chosen.length() - 1).toString();
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("Permutations of a C Major 13: ");
		PermuteString.permuteString("CBEA");
		System.out.println();
		System.out.println("Permutations of TIM: ");
		PermuteString.permuteString("TIM");
		System.out.println();
		System.out.println("Permutations of GOO: ");
		PermuteString.permuteString("GOO");
	}
}
