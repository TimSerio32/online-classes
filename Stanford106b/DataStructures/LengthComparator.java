import java.util.*;

public class LengthComparator implements Comparator<String> {
	public int compare(String s1, String s2) {
		if(s1.length() != s2.length()) {
			// if lengths are unequal, compare by length
			return s1.length() - s2.length();
		} else {
			// break ties by ABC order
			return s1.compareTo(s2);
		}
	}			
}

