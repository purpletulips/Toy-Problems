package SumToTarget;
import java.util.*;

public class SumToTarget {
	public static void main(String[] args){
		TEST(new int[]{1,4,8,-2,1,0}, 10, true);
		TEST(new int[]{1,4,8,-2,1,0}, 11, true);
		TEST(new int[]{1,4,8,-2,1,0}, 14, false);
		TEST(new int[]{-1,-1,-1,2,-1}, -4, false);
		TEST(new int[]{-1,-1,-1,2,-1}, 1, true);
	}
	
	public static void TEST(int[] nums, int target, boolean ans) {
		if (solve(nums,target)==ans) {
			System.out.println("PASS");
		} else {
			System.out.println("FAIL");
		}
	}
	
	public static boolean solve (int[] nums, int target){
		if (nums==null || nums.length==0) return false;
		Set<Integer> sums = new HashSet<Integer>();
		int sum = 0;
		for (int i=0; i<nums.length; i++) {
			sum += nums[i];
			if (sums.contains(sum-target)) {
				return true;
			} else {
				sums.add(sum);
			}
		}
		
		return false;
		
		
	}

}
