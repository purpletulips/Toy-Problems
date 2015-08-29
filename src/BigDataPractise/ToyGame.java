package BigDataPractise;

import java.util.*;

public class ToyGame {
	
	public static void main(String args[]) {
		solve();
	}

	public static void solve() {
		Hashtable<String,Integer> table = new Hashtable<String,Integer>();
		HashMap map = new HashMap();
		table.put("first key", 1);
		//table.put("second key", 2);
		System.out.println("Hash code of the table is " + table.hashCode());

		map.put("second key", 2);
		System.out.println("Hash code of the map is " + map.hashCode());
		
		
	}
}
