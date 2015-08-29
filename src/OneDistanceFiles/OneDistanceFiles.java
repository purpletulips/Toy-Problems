package OneDistanceFiles;
import java.util.*;
import java.lang.*;

public class OneDistanceFiles {

	public static void TEST(String fileA, String fileB, boolean answer) {
		boolean res = false;
		if (fileA.length()==0) {
			res = fileB.length()<=1;
		} else if (fileB.length()==0) {
			res= fileA.length()<=1;
		} else {
		
			
			String[] A = fileA.split(",");
			String[] B = fileB.split(",");
			
			List<Integer> listA= new LinkedList<Integer>();
			for (int i=0; i<A.length; i++) {
				listA.add(Integer.parseInt(A[i]));
			}
			
			List<Integer> listB = new LinkedList<Integer>();
			for (int i=0; i<B.length; i++) {
				listB.add(Integer.parseInt(B[i]));
			}
			ListIterator<Integer> a = (ListIterator<Integer>) listA.iterator();
			ListIterator<Integer> b = (ListIterator<Integer>) listB.iterator();
			res = solve(a,b);
		}
		if (res==answer) {
			System.out.println("PASS");
		} else {
			System.out.println("FAIL");
		}
		System.out.println("--------");
		
	}
	
	public static void main(String[] args) {
	
		TEST("1,2,3,4,5,6,7,8","1,2,3,4,6,7,8",true);
		TEST("1,2,3","2,3", true);
		
		TEST("1,2", "3,1", false); // 0
		TEST("1,2", "2,3", false); // 0
		TEST("1,2,1", "2,1", true);
		TEST("1,2", "2,1,2", true);
		TEST("1,2,1,2,4", "2,1,2,4", true);
		TEST("1,2,3", "1,2,4", true); // 1
		TEST("1", "2,3", false); // 0
		TEST("1", "2,1", true); // 1
		TEST("2,3", "1", false); // 0
		TEST("1", "1,2,3,4,5", false); // 0
		TEST("1", "", true); // 1
		TEST("", "1", true); // 1
		TEST("1,2,3", "2,3", true); // 1
		TEST("1,3,4", "2,3,4", true); // 1
		TEST("1,2,3", "2,4", false); // 0
		TEST("1,2,3", "2", false); // 0
		TEST("3,4", "5", false); // 0
		TEST("3,4", "5,6", false); // 0
		TEST("1,2,3", "1,2,3", true); // 1
		TEST("", "", true); // 1
		TEST("1", "2", true); // 1
		TEST("1,3,4,5,6,7", "1,2", false); // 0
		TEST("1,3,4,5,6,7", "1,4,5,6,7", true); // 1
		TEST("2,3,4,5,6,7", "3,4,5,6,7", true); // 1
	}
	
	
	public static boolean solve(ListIterator<Integer> a, ListIterator<Integer> b){
		boolean rep = false, insa = false, insb=false, diff=false;
		int prea=0, preb=0;
		while (a.hasNext() && b.hasNext()) {
			int cura = (int) a.next();
			int curb = (int) b.next();
			if (!rep && !insa && !insb && !diff) {
				if (cura!=curb) {
					rep = insa = insb = diff = true;
				}
			} else {
				if (insa && cura != preb) insa = false;
				if (insb && curb != prea) insb = false;
				if (rep && cura != curb) rep = false;
				if (!rep && !insa && !insb) return false;
			}
			prea = cura;
			preb = curb;
		}
		
				
		if (!a.hasNext() && !b.hasNext()) {
			return rep || !diff;
		} else if (a.hasNext()){
			int cura = (int) a.next();
			return !a.hasNext() && ((insa && cura==preb) || !diff); 
		} else {
			int curb = (int) b.next();
			return !b.hasNext() && ((insb && curb==prea) || !diff);
		}
		
	}

}
