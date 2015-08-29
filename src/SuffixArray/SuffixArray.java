package SuffixArray;
import java.util.*;
import java.lang.*;

public class SuffixArray {
	
	public static void main(String args[]){
		TEST("banana", new int[]{5,3,1,0,4,2});
		TEST("cccccc", new int[]{5,4,3,2,1,0});
		TEST("", null);
		
	}
	
	public static void TEST(String s, int[] ans){
		List<Integer> res = solve(s);

		boolean correct = true;
		for (int i=0; i<res.size(); i++) {
			if (res.get(i)!=ans[i]) {
				correct = false;
				break;
			}
		}
		if (correct) {System.out.println("PASS");}
		else { System.out.println("FAIL");}
	}
	
	public static class Suffix {
		int index;
		String s;
		Suffix(int ind, String s) { index = ind; this.s=s;}
	}

	public static List<Integer> solve(String s){
		List<Integer> res = new ArrayList<Integer>();
		if (s==null || s.length()==0) return res;
		
		List<Suffix> suffixes = new ArrayList<Suffix>();
		for (int i=0; i<s.length(); i++) {
			suffixes.add(new Suffix(i,s.substring(i)));
		}
		
		Collections.sort(suffixes, new Comparator<Suffix>(){
			public int compare(Suffix o1, Suffix o2) {
				return o1.s.compareTo(o2.s);
			}
		}
		);
		
		for (int i=0; i<suffixes.size(); i++) {
			res.add(suffixes.get(i).index);
		}
		
		return res;
	}
	
	
}
