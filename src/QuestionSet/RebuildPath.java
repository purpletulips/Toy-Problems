package QuestionSet;



import java.util.*;
import java.lang.String;

public class RebuildPath {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args.length<3) {
			return;
		}

		String[] edges = args[2].split(",");
		for (int i=0; i<edges.length; i++) {
			System.out.println(edges[i]);
		}
		
		RebuildPath object  = new RebuildPath(args[0],args[1],args[2].split(","));
		String res = object.solve();
	
		System.out.println(res);
	}
	
	private String start;
	private String end;
	private Map<String,List<String>> map;

	public RebuildPath(String start, String end, String[] edges){
		this.setStart(start);
		this.setEnd(end);
		map = new HashMap<String,List<String>>();
		
		for (int i=0; i<edges.length; i++) {
			String[] split = edges[i].split("->");
			System.out.println("size of edge:" + split.length);
			if (!map.containsKey(split[0])) {
				map.put(split[0], new ArrayList<String>());
			}
			map.get(split[0]).add(split[1]);
		}
	}
	
	public String solve(){
		List<String> res = new ArrayList<String>();
		List<String> path = new ArrayList<String>();
		path.add(this.getStart());
		helperDFS(this.getStart(),this.map, path, res);
		
		if (res.size()==0) {
			return "";
		}
		
		return res.get(0);
	}
	
	private void helperDFS(String cur, Map<String,List<String>> map, List<String> path, List<String> res) {
		System.out.println("current path: "+ path);
		System.out.println("current map: "+map);
		System.out.println("-----");
		if (cur.equals(this.getEnd()) && map.isEmpty()) {
			// found the result
			StringBuilder sb = new StringBuilder();
			sb.append("");
			sb.append(path.get(0));
			for (int i=1; i<path.size(); i++) {
				sb.append("->" + path.get(i));
			}
			res.add(sb.toString());
			System.out.println("find one solution: " + sb.toString());
			
			return;
		}
		//String key = path.get(path.size()-1);
		if (map.containsKey(cur)==false) {
			return;
		}
		Map<String,List<String>> copyMap = new HashMap<String,List<String>>(map);
		if (map.get(cur).size()==1) {
			String next = copyMap.get(cur).get(0);
			copyMap.remove(cur);
			path.add(next);
			helperDFS(next,copyMap,path,res);
			path.remove(path.size()-1);
		} else {
			for (int i=0; i<map.get(cur).size(); i++) {
				String next = copyMap.get(cur).get(i);
				copyMap.get(cur).remove(i);
				path.add(next);
				helperDFS(next,copyMap,path,res);
				path.remove(path.size()-1);
				copyMap.get(cur).add(i,next);
			}
		}
		

		System.out.println("*******");
		return;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

}
