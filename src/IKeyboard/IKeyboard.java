package IKeyboard;
import java.util.*;

public class IKeyboard {
	public static void main(String[] args) {
		TEST(2,new int[]{2,3,1},7);
		TEST(4,new int[]{10,15,10,8,7,20,15},117);
	}
	
	public static void TEST(int K, int[] Freq, int ans){
		int res = solve(K, Freq);
		if (res==ans) {
			System.out.println("PASS!");
		} else {
			System.out.println("FAIL with wront answer: " + res);
		}
	}
	
	
	public static int solve(int K, int[] Freq){
		int L = Freq.length;
		int[][] cost = new int[L+1][L+1];
		int[][] price = new int[K+1][L+1];
		
		for (int i=0;i<=K;i++) Arrays.fill(price[i], 2000);
		price[0][0] = 0;
		
		for (int i=0; i<=L; i++) Arrays.fill(cost[i],0);
		
		for (int i=1;i<=L;i++) {
			for (int j=i; j<=L; j++) {
				cost[i][j] = cost[i][j-1] + (j-i+1)*Freq[j-1];
			}
		}
		
		//for (int j=1; j<=L; j++) price[1][j] = cost[1][j];
		//for (int i=1; i<=K; i++) price[i][1] = Freq[0];
		
		for (int i=1; i<=K; i++) {
			for (int j=i;j<=L; j++) {
				for (int k=1; k <= j-i+1; k++) {
					if (price[i-1][j-k] + cost[j-k+1][j] < price[i][j]) {
						price[i][j] = price[i-1][j-k] + cost[j-k+1][j];
					}
				}
			}
		}
		
		System.out.println("Cost Matrix ");
		for (int i=0; i<=L; i++) System.out.println(Arrays.toString(cost[i]));
		
		System.out.println("Price Matrix ");
		for (int i=0; i<=K; i++) System.out.println(Arrays.toString(price[i]));
		
		
		return price[K][L];
	}

}
