package PrimeProduct;
import java.util.*;

public class PrimeProduct {
	public static void main(String[] agrs) {
		TEST(new int[]{2,3,5},new int[]{2,3,5,6,10,15,30});
		TEST(new int[]{3,5,11},new int[]{3, 5, 11, 15, 33, 55, 165});
		TEST(new int[]{2,2,2,11},new int[]{2,4,8,11,22,44,88});
	}
	
	public static void TEST(int[] nums, int[] ans){
		List<Integer> res = solve2(nums);
		//System.out.println(res);
		if (res.size()!=ans.length) {
			System.out.println("FAIL");
			return;
		}
		Collections.sort(res);
		for (int i=0; i<ans.length; i++) {
			if (ans[i]!=res.get(i)) {
				System.out.println("FAIL");
				return;
			}
		}

		System.out.println("PASS");
	}
	
	public static List<Integer> solve(int[] nums){
		Set<Integer> res = new HashSet<Integer>();
		if (nums==null || nums.length<1) return new ArrayList<Integer>(res);
		List<Integer> remain = new ArrayList<Integer>();
		for (int i=0; i<nums.length; i++){
			remain.add(nums[i]);
		}
		helper(remain,res,1);
		
		return new ArrayList<Integer>(res);
	}
	
	private static void helper(List<Integer> remain, Set<Integer> res,int prod) {
		if (remain.isEmpty()) {return;}
		
		for (int i=0; i<remain.size();i++) {
			int cur = remain.get(i);
			res.add(prod*cur);
			remain.remove(i);
			helper(remain,res,prod*cur);
			remain.add(i,cur);
		}
		
		
	}
	
	public static List<Integer> solve2(int[] nums){
		Set<Integer> res = new HashSet<Integer>();
		if (nums==null || nums.length<1) return new ArrayList<Integer>(res);
		
		helper2(nums,res,1,0);
		
		return new ArrayList<Integer>(res);
	}
	
	private static void helper2(int[] nums, Set<Integer> res, int prod, int st) {
		for (int i=st; i<nums.length; i++) {
			res.add(prod*nums[i]);
			helper2(nums, res, prod*nums[i],i+1);
		}
		
	}
	
	

}
