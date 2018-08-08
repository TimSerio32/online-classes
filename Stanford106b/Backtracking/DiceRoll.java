import java.util.*;

class DiceRoll {
	public static void diceRoll(int dice) {
		ArrayList<Integer> chosen = new ArrayList<Integer>();
		diceRoll(dice, chosen);
	}
	
	private static void diceRoll(int dice, ArrayList<Integer> chosen) {
		//System.out.println("diceRoll " + dice + ", " + chosen);
		if(dice == 0) {
			// base case
			System.out.println(chosen);
		} else {
			// some dice left to roll:
			// handle one die
			// for each value that die could have:
			for(int i = 1; i <= 6; i++) {
				// Marty Stepp's formula for Backtracking Algorithms:
				// choose
				chosen.add(i);
				
				// explore
				diceRoll(dice - 1, chosen);
				
				// unchoose
				chosen.remove(chosen.size() - 1);
			}
		}
	}

	public static void main(String[] args) {
		DiceRoll.diceRoll(3);
	}
}
