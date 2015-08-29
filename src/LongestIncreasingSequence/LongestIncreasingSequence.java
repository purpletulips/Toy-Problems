package LongestIncreasingSequence;

import java.util.Arrays;

public class LongestIncreasingSequence {
	
	public static void main(String[] args) {
		TEST(new int[]{0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15},6);
		TEST(new int[]{2,5,3,7,1,8,9,13},6);
		
		TEST2(new int[]{2,5,3,7,1,8,9,13},new int[]{2,3,7,8,9,13});
		TEST2(new int[]{0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15},new int[]{0,2,6,9,11,15});
	}

	public static void TEST(int[] arr, int ans){
		int res = solve(arr);
		if (res==ans) {
			System.out.println("PASS!");
		} else {
			System.out.println("FAIL! with wrong answer: " + res);
		}
	}
	
	public static int solve(int[] arr) {
		if (arr==null) return 0;
		if (arr.length<=1) return arr.length;
		
		int[] tail = new int[arr.length];
		tail[0] = arr[0];
		int len = 1;
		
		for (int i=1; i<arr.length; i++) {
			if (arr[i]<=tail[0]) {
				tail[0] = arr[i];
			} else if (arr[i]>tail[len-1]) {
				tail[len++] = arr[i];
			} else {
				int pos = searchHelper(arr,0,len-1,arr[i]); 
				tail[pos] = arr[i];
			}
		}
				
		return len;
	}
	
	public static int searchHelper(int[] arr, int st, int ed, int target) {
		int l=st, r=ed;
		while (r-l>1) {
			int m = (r+l)/2;
			if (arr[m]>=target){
				r = m;
			} else {
				l = m;
			} 
		}
		if (arr[l]>=target) return l;
		return r;
	}
	
	public static void TEST2(int[] arr, int[] ans) {
		int[] res = new int[arr.length];
		int len = solve2(arr, res);
		if (len!=ans.length) {
			System.out.println("FAIL with wrong length of " + len);
		} else {
			for (int i=0; i<len; i++) {
				System.out.print("" + res[i] + ",");
				if (ans[i]!=res[i]) {
					System.out.println("FAIL with wrong array at index " + i);
					//return;
				} 
			}
			System.out.println("");
			System.out.println("PASS! ");
		}
		
	}
	
	
	
	public static int solve2(int[] arr, int[] res){
		int len = 0;
		
		int[] tailIndex = new int[arr.length];
		int[] prevIndex = new int[arr.length];
		Arrays.fill(prevIndex, -1);
		
		tailIndex[0] = 0;
		len = 1;
		
		for (int i=1; i<arr.length; i++) {
			
			if (arr[i]<=arr[tailIndex[0]]) {
				tailIndex[0] = i;
			} else if (arr[i]>arr[tailIndex[len-1]]) {
				prevIndex[i] = tailIndex[len-1];
				tailIndex[len++] = i;				
			} else {
				int pos = searchHelper2(arr,tailIndex,0,len-1,arr[i]);
				prevIndex[i] = tailIndex[pos-1];
				tailIndex[pos] = i;
				
			}
		}
		
		int i=tailIndex[len-1];
		int pos = len-1;
		while (pos>=0 && i>=0) {
			res[pos] = arr[i];
			i = prevIndex[i];
			pos--;
		}
		
		return len;
	}
	
	
	public static int searchHelper2(int[] arr, int[] index, int st, int ed, int target) {
		int l=st, r=ed;
		while (r-l>1) {
			int m = (r+l)/2;
			if (arr[index[m]]>=target){
				r = m;
			} else {
				l = m;
			} 
		}
		if (arr[index[l]]>=target) return l;
		return r;
	}
}
