package ShiftNozeroElement;

public class ShiftNonzeroElement {
	
	public static void TEST(int[] nums, int[] answer){
		solve(nums);
		for (int i=0; i<nums.length; i++) {
			if (nums[i]!=answer[i]) {
				System.out.println("FAIL");
				return;
			}
		}
		System.out.println("PASS");
		
	}
	
	public static void solve(int[] nums) {
		if (nums==null || nums.length<=1) return;
		int l=0,r=0;
		while (r<nums.length && nums[r]!=0) {r++; l++;}
		while (r<nums.length) {
			while (r<nums.length && nums[r]==0) {r++;}
			if (r<nums.length) {
				swap(nums,l,r);
				l++;
			}
		}
		
	}
	
	private static void swap(int[] nums, int i, int j){
		int temp = nums[i];
		nums[i]=nums[j];
		nums[j]=temp;
	}
	
	public static void main(String[] agrs){
		TEST(new int[]{5,0,1,0,4,2},new int[]{5,1,4,2,0,0});
		TEST(new int[]{0,0,1,0,4,2},new int[]{1,4,2,0,0,0});
		TEST(new int[]{0},new int[]{0});
		TEST(new int[]{0,0,0,0,0,1},new int[]{1,0,0,0,0,0});
	}

}
