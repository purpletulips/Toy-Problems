package BalancedSubstring;

import java.util.*;

public class BalancedSubstring {
	
		  public static void main(String[] args) {
		    int res = solve(new int[]{1,0,0,1,0,1,0,1,1,0,0,0,1,0});
		    System.out.println("max length is " + res);

		    
		  }
		  
		  public static int solve (int[] nums) {
		    int[] cnt = new int[nums.length+1];
		    Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		    cnt[0] = 0;
		    for (int i=0; i<nums.length; i++) {
		      if (nums[i]==0) {
		        cnt[i+1] = cnt[i] + 1;
		      } else {
		        cnt[i+1] = cnt[i] - 1;
		      }
		      if (!map.containsKey(cnt[i+1])) {
		        map.put(cnt[i+1], i);
		      }
		    }
		    
		    if (cnt[nums.length]==0) {
		      return nums.length;
		    }
		    
		    int maxL = 0; 
		    for (int i=nums.length-1; i>=0; i--) {
		        maxL = Math.max(maxL, i-map.get(cnt[i+1]));
		    }
		    
		    System.out.println(Arrays.toString(cnt));
		    System.out.println(map);
		    return maxL;

		  }

		  
}
