import java.util.*;

class DiceSum {
	public static void diceSum(int dice, int desiredSum) {
		ArrayList<Integer> chosen = new ArrayList<Integer>();
		diceSum(dice, desiredSum, 0, chosen);
	}

	private static void diceSum(int dice, int desiredSum, int sumSoFar, ArrayList<Integer> chosen) {
		// When doing backtracking be careful not to include any paths that don't lead to anything useful
		if(dice == 0) {
			// base case
			System.out.println(chosen);
		} else {
			// some dice left to roll:
			// handle one die
			// for each value that die could have:
			for(int i = 1; i <= 6; i++) {
				// if the minimum that I can get up to with the remaining dice is less than the desiredSum
				// if the maximum that I can get up to with the remaining dice is greater than the desiredSum
				if(sumSoFar + i + 1 * (dice - 1) <= desiredSum && sumSoFar + i + 6 * (dice - 1) >= desiredSum) {
					// Marty Stepp's formula for Backtracking Algorithms:
					// choose
					chosen.add(i);
					
					// explore
					diceSum(dice - 1, desiredSum, sumSoFar + i, chosen);
					
					// unchoose
					chosen.remove(chosen.size() - 1);
				}
			}
		}
	}

	public static void main(String[] args) {
		DiceSum.diceSum(3, 7);
	}
}
