package ContactList;

import java.util.*;

public class ContactList {
	public static class UnionFind {
		public int[] parents;
		public int[] ranks;
		
		public UnionFind(int n) {
			parents = new int[n];
			ranks = new int[n];
			for (int i=0; i<n; i++) {
				parents[i] = i;
				ranks[i] = 0;
			}
		}
		
		public int Find(int x) {
			if (parents[x] == x) return x;
			return Find(parents[x]);
		}
		
		public void Union(int x, int y) {
			if (x==y) return;
			int px = Find(x);
			int py = Find(y);
			if (px==py) return;
			
			if (ranks[px]<ranks[py]) {
				parents[px] = py;
			} else {
				parents[py] = px;
				if (ranks[px]==ranks[py]) {
					ranks[px] ++;
				}
			}
		}
	}
	
	public static List<List<String>> solve(List<String> names,List<List<String>> emails){
		Map<String,List<Integer>> emailMap = new HashMap<String,List<Integer>>();
		for (int i=0; i<names.size(); i++) {
			for (int j=0; j<emails.get(i).size(); j++) {
				String email = emails.get(i).get(j);
				if (emailMap.containsKey(email) == false) {
					emailMap.put(email, new ArrayList<Integer>());
				}
				emailMap.get(email).add(i);
			}
		}
		
		UnionFind uf = new UnionFind(names.size());
		for (String email:emailMap.keySet()) {
			List<Integer> indexList = emailMap.get(email);
			for (int i=0; i<indexList.size()-1; i++) {
				uf.Union(indexList.get(i), indexList.get(i+1));
			}
		}
		
		Map<Integer,List<String>> res = new HashMap<Integer,List<String>>();
		for (int i=0; i<names.size(); i++) {
			int root = uf.Find(i);
			if (res.containsKey(root) == false) {
				res.put(root,new ArrayList<String>());
			}
			res.get(root).add(names.get(i));
		
		}
		
		return new ArrayList<List<String>>(res.values());
		
		
	}
	
	public static void main(String[] args) {
		List<String> names = new ArrayList<String>();
		names.add("John");
		names.add("Mary");
		names.add("Johns");
		names.add("John");
		names.add("Bob");
		
		List<List<String>> emails = new ArrayList<List<String>>();
		emails.add(new ArrayList<>());
		emails.add(new ArrayList<>());
		emails.add(new ArrayList<>());
		emails.add(new ArrayList<>());
		emails.add(new ArrayList<>());
		emails.get(0).add("john@gmail.com");
		emails.get(1).add("mary@gmail.com");
		emails.get(2).add("john@yahoo.com");
		emails.get(3).add("john@gmail.com");
		emails.get(3).add("john@yahoo.com");
		emails.get(3).add("john@hotmail.com");
		emails.get(4).add("bob@gmail.com");
		
		List<List<String>> groupedNames = solve(names,emails);
		for(int i=0; i<groupedNames.size(); i++) {
			System.out.println(""+i+":"+groupedNames.get(i).toString());
		}
	}
	
	

}
