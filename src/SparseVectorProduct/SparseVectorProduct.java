package SparseVectorProduct;
import java.util.*;

public class SparseVectorProduct {
	public static void main(String[] args){
		TEST(new int[][]{{1,4},{4,2},{5,3}}, new int[][]{{1,7},{2,6},{5,1},{7,2}},31);
		TEST(new int[][]{{}}, new int[][]{{1,7},{2,6},{5,1},{7,2}},0);
	}
	
	public static void TEST(int[][] v1, int[][] v2, int ans){
		int res = solve(v1, v2);
		if (res==ans) {
			System.out.println("PASS!");
		} else {
			System.out.println("FAIL with wront answer: " + res);
		}
	}
	
	public static int solve(int[][] v1, int[][] v2){
		int sum = 0;
		if (v1==null || v1.length==0 || v1[0].length==0) return sum;
		if (v2==null || v2.length==0 || v2[0].length==0) return sum;
		
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		for (int i=0; i<v1.length; i++){
			map.put(v1[i][0],v1[i][1]);
		}
		
		for (int i=0; i<v2.length; i++) {
			if (map.containsKey(v2[i][0])) {
				sum += map.get(v2[i][0])*v2[i][1];
			}
		}
		return sum;
		
	}

}
