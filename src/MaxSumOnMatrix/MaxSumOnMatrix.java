package MaxSumOnMatrix;
import java.util.*;
public class MaxSumOnMatrix {
	
	
	public static void main(String[] args) {
		long stT = System.currentTimeMillis();
		int res = solve(new int[][]{{1,0,1,0,0,0,0,2},
									{0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,1},
									{0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,1}});
		
								
		/*int res = solve2(new int[][]{{1,0,2},
									{0,2,0},
									{2,0,0}});*/
		/*int res = solve2(new int[][]{{1,0,2,0,1},
									{0,2,0,1,0},
									{2,0,2,0,2},
									{2,0,0,0,2},
									{2,0,0,0,2}});
									*/
		
		/*int res = solve2(new int[][]{{1,0,2,0,1,0,0},
									{0,2,0,1,0,0,0},
									{2,0,2,0,2,0,0},
									{2,0,0,0,2,0,0},
									{2,0,0,0,2,0,0},
									{2,0,0,0,2,0,0},
									{0,0,0,0,0,0,0}});
									*/
									
									
		
		/*int res = solve(new int[][]{{1,0,2,0,1,0},
									{0,2,0,1,0,0},
									{2,0,2,0,2,0},
									{2,0,0,0,2,0},
									{2,0,0,0,2,0},
									{2,0,0,0,2,0}});
									*/
									
		
		long edT = System.currentTimeMillis();
		int runT = (int)(edT-stT)/1000;
		System.out.println("Run time is "+runT+"secs");
		System.out.println("max sum is:" + res);;
	}
	
	public static long calls;
	
	public static int solve(int[][] go) {
		calls=0;
		Set<Integer> set = new HashSet<Integer>();
		int s = go.length;
		for (int i=0; i<s*s; i++) { set.add(i);}
		List<Integer> res = new ArrayList<Integer>();
		res.add(0);
		helper(set, res, go, 0);
		System.out.println("total number of rec calls is " + calls);
		return res.get(0);
	}

	public static void helper(Set<Integer> set, List<Integer> res, int[][] go, int sum) {
		//System.out.println(""+set.toString());
		//System.out.println("cur res: " + res.get(0) + "; cur sum: " + sum);
		calls++;
		int s = go.length;
		
		if (res.get(0)<sum) { res.set(0,sum);	}
	
		if (set.isEmpty()) return;
		
		Iterator<Integer> it = set.iterator();
		int ind = (int) it.next();

		
		//System.out.println("-----");

		// case 1: include the element at ind
		//System.out.println("case 1: visiting element " + ind); 
		Set<Integer> copy1 = new HashSet<Integer>(set);
		copy1.remove(ind);
		removeNeighbors(ind, copy1, s);
		helper(copy1, res, go, sum + go[ind/s][ind%s]);
		
		
		//System.out.println("****");
		
		// case 2: not include the element at ind
		//System.out.println("case 2: visiting element " + ind); 
		Set<Integer> copy2 = new HashSet<Integer>(set);
		copy2.remove(ind);
		helper(copy2,res,go,sum);
		//set.add(ind);
	}

	public static void removeNeighbors(int ind, Set<Integer> set, int size) {
		//Set<Integer> removed = new HashSet<Integer>();
		if (set.isEmpty()) return;

		int i= ind/size; 
		int j=ind%size;
		
		if (i>0 && j>0 && set.contains(ind-size-1)) { set.remove(ind-size-1); }//removed.add(ind-9);}
		if (i>0 && set.contains(ind-size)) {set.remove(ind-size); }//removed.add(ind-8);}
		if (i>0 && j<size-1 && set.contains(ind-size+1)) {set.remove(ind-size+1); }//removed.add(ind-7);}


		if (j>0 && set.contains(ind-1)) {set.remove(ind-1); }//removed.add(ind-1);}
		if (j<size-1 && set.contains(ind+1)) {set.remove(ind+1); }//removed.add(ind+1);}


		if (i<size-1 && j>0 && set.contains(ind+size-1)) { set.remove(ind+size-1); }//removed.add(ind+7);}
		if (i<size-1 && set.contains(ind+size)) {set.remove(ind+size); }//removed.add(ind+8);}
		if (i<size-1 && j<size-1 && set.contains(ind+size+1)) {set.remove(ind+size+1); }//removed.add(ind+9);}
	
	}
	
	public static int solve2(int[][] go) {
		calls=0;
		List<Integer> res = new ArrayList<Integer>();
		res.add(0);
		boolean[][] used = new boolean[go.length][go.length];

		helper2(0, used, go, res, 0);
		
		return res.get(0);
		
	}
	
	public static void helper2(int ind, boolean[][] used,  int[][] go, List<Integer> res, int sum) {

		//System.out.println("-------");
		if (sum>res.get(0)) {
			res.set(0,sum);
		}

		//System.out.println("grid " + ind + ": res " + res.get(0) + ", sum " + sum);
		
		int n = go.length;
		if (ind>=n*n) {
			return;
		}
		
		// include the current 
		//System.out.println(" case 1");
		used[ind/n][ind%n] = true;
		int i=ind+1;
		while (i<n*n && !isValid(i,used)) {i++;}
		helper2(i, used, go, res, sum+go[ind/n][ind%n]);
		used[ind/n][ind%n] = false;
		

		//System.out.println("grid " + ind + " case 2");
		helper2(ind+1, used, go, res, sum);
	}
	
	public static boolean isValid(int ind, boolean[][] used) {
		//Set<Integer> removed = new HashSet<Integer>();
		int size = used.length;

		int i= ind/size; 
		int j=ind%size;
		
		if (i>0 && j>0 && used[i-1][j-1]) { return false; }
		if (i>0 && used[i-1][j]) {return false; }
		if (i>0 && j<size-1 && used[i-1][j+1]) {return false; }


		if (j>0 && used[i][j-1]) {return false; }
		if (j<size-1 && used[i][j+1]) {return false; }


		if (i<size-1 && j>0 && used[i+1][j-1]) { return false; }//removed.add(ind+7);}
		if (i<size-1 && used[i+1][j]) {return false; }//removed.add(ind+8);}
		if (i<size-1 && j<size-1 && used[i+1][j+1]) {return false; }//removed.add(ind+9);}
		
		return true;
	}
	
	

}
