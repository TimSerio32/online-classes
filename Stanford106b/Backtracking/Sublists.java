import java.util.*;

class Sublists {
	public static void sublists(ArrayList<String> a) {
		ArrayList<String> chosen = new ArrayList<String>();
		sublists(a, chosen);
	}
	
	private static void sublists((ArrayList<String> a, (ArrayList<String> chosen) {
		if(a.isEmpty()) {
			System.out.println(chosen);
		} else {
			// there are two choices to explore:
			// the subset with first element, and the one without it
			String first = a[0];
			a.remove(0);
			
			// choose/ explore (with)
			chosen.add(first);
			sublists(a, chosen);
			
			// choose/explore (without)
			chosen.remove(chosen.size() - 1);
			sublists(a, chosen);
			
			// unchoose
			a.add(0, first);
		}
	}

	public static void main(String[] args) {
		
	}
}
