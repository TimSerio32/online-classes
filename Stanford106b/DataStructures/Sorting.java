import java.util.*;

class Sorting {
	
	private static void swap(ArrayList<Integer> a, int i, int j) {
		int tmp = a.get(i);
		a.set(i, a.get(j));
		a.set(j, tmp);
	}
	
	public static void selectionSort(ArrayList<Integer> a) {
		for(int i = 0; i < a.size(); i++) {
			int min = i;
			// find the right element to go at index i
			for(int j = i; j < a.size(); j++) {
				if(a.get(j) < a.get(min)) {
					min = j;
				}
			}
			swap(a, i, min);
		}
	}
	
	public static void insertionSort(ArrayList<Integer> a) {
		for(int i = 1; i < a.size(); i++) {
			int temp = a.get(i);
			int j = i;
			while(j >= 1 && a.get(j - 1) > temp) {
				a.set(j, a.get(j - 1));
				j--;
			}
			a.set(j, temp);
		}
	}
	
	public static void mergeSort(ArrayList<Integer> a) {
		// base case
		if(a.size() <= 1) {
			return;
		}
		// recursive case
		// - split ArrayList in half
		ArrayList<Integer> left = new ArrayList<Integer>();
		ArrayList<Integer> right = new ArrayList<Integer>();
		
		for(int i = 0; i < a.size() / 2; i++) {
			left.add(i, a.get(i));
		}
		
		int index = 0;
		for(int j = a.size() / 2; j < a.size(); j++) {
			right.add(index, a.get(j));
			index++;
		}
		
		// - sort halves
		mergeSort(left);
		mergeSort(right);
		
		// - merge halves
		int i1 = 0;		// index into left
		int i2 = 0;		// index into right
		
		for(int i = 0; i < a.size(); i++) {
			// take from left if:
			//		- nothing remaining on the right
			//		- thing on left is smaller
			if(i2 >= right.size() || (i1 < left.size() && left.get(i1) < right.get(i2))) {
				a.set(i, left.get(i1));
				i1++;
			} else {
				a.set(i, right.get(i2));
				i2++;
			}
		}
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr.add(5);
		arr.add(4);
		arr.add(3);
		arr.add(2);
		arr.add(1);
		
		System.out.println("selection:");
		
		System.out.println(arr.toString());
		
		Sorting.selectionSort(arr);
		
		System.out.println(arr.toString());
		
		System.out.println();
		
		ArrayList<Integer> arr1 = new ArrayList<Integer>();
		arr1.add(5);
		arr1.add(4);
		arr1.add(3);
		arr1.add(2);
		arr1.add(1);
		
		System.out.println("insertion:");
		
		System.out.println(arr1.toString());
		
		Sorting.selectionSort(arr1);
		
		System.out.println(arr1.toString());
		
		System.out.println();
		
		ArrayList<Integer> arr2 = new ArrayList<Integer>();
		arr2.add(5);
		arr2.add(4);
		arr2.add(3);
		arr2.add(2);
		arr2.add(1);
		
		System.out.println("merge:");
		
		System.out.println(arr2.toString());
		
		Sorting.mergeSort(arr2);
		
		System.out.println(arr2.toString());
		
		System.out.println();
	}
}
