package TaskTimeSchedule;

import java.util.Arrays;

public class TaskTimeSchedule {
	public static void main(String[] args) {
		TEST(new int[]{0,0,1,0,2,1}, new int[]{20,15,10},90);
	}
	
	public static void TEST(int[] tasks, int[] buffer, int ans) {
		int res = solve(tasks,buffer);
		if (res==ans) {
			System.out.println("PASS");
		} else {
			System.out.println("FAIL");
		}
	}
	
	public static int solve(int[] tasks, int[] buffer) {
		if (tasks==null || tasks.length==0) return 0;
		int res = 0; 
		int[] last = new int[buffer.length];
		Arrays.fill(last,-1);
		for (int i=0; i<tasks.length; i++) {
			if (last[tasks[i]]<0 || last[tasks[i]]+buffer[tasks[i]]<=res) {
				res += 10;
			} else {
				res = (last[tasks[i]]+buffer[tasks[i]]) + 10;
			}

			last[tasks[i]] = res;
		}
		return res;
	}
}
