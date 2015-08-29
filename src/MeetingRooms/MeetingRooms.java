package MeetingRooms;
import java.util.*;

public class MeetingRooms {
	
	public static class Meeting{
		int id;
		int st; 
		int ed;
		public Meeting(int id, int stTime, int edTime) {
			this.id = id; 
			this.st = stTime; 
			this.ed = edTime;
		}
	}
	
	
	public static class Room{
		int id; 
		int avail;
		public Room(int roomId, int timeAvail){
			this.id = roomId; 
			this.avail = timeAvail;
		}
	}
	
	
	
	public static void main(String[] args) {
		List<List<Integer>> ans = new ArrayList<List<Integer>>();
		ans.add(new ArrayList<Integer>());
		ans.get(0).add(0);
		ans.get(0).add(3);
		ans.add(new ArrayList<Integer>());
		ans.get(1).add(1);
		ans.get(1).add(2);
		
		TEST(new int[][]{{4,7},{5,6},{6,8},{7,8}},ans);
		
		System.out.println("----------");
		
		ans.clear();
		ans.add(new ArrayList<Integer>());
		ans.get(0).add(2);
		ans.get(0).add(3);
		ans.add(new ArrayList<Integer>());
		ans.get(1).add(1);
		ans.get(1).add(0);
		
		TEST(new int[][]{{6,8},{5,6},{4,7},{7,8}},ans);
		
		System.out.println("------------");
		int res = MostNumberOfMeetings(new int[][]{{6,8},{5,6},{4,7},{7,8}});
		if (res==2) {
			System.out.println("PASS!");
		} else {
			System.out.println("FAIL with wrong answer: " + res);
		}
		
		res = MostNumberOfMeetings(new int[][]{{1,3},{2,3},{2,4},{3,6},{4,6}});
		if (res==3) {
			System.out.println("PASS!");
		} else {
			System.out.println("FAIL with wrong answer: " + res);
		}
	}
	
	public static void TEST(int[][] meetings, List<List<Integer>> ans){
		List<List<Integer>> res = solve(meetings);
		if (ans.size()!=res.size()) {
			System.out.println("FAIL!");
			System.out.println("Incorrect number of room " + res.size() + "(" + ans.size() + ")");
		} else {
			for (int i=0; i<ans.size(); i++) {
				if (res.get(i).size()!=ans.get(i).size() || !res.get(i).containsAll(ans.get(i))) {
					System.out.println("FAIL!");
					System.out.println("Expected meetings at room "+i+":"+ans.get(i).toString());
					System.out.println("Output meetings at room "+i+":"+res.get(i).toString());
					return;
				}
			}
			System.out.println("PASS!");
		}
		
	}
	
	public static List<List<Integer>> solve(int[][] meetings){
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		
		
		List<Meeting> meetingList = new ArrayList<Meeting>();
		for (int i=0; i<meetings.length; i++) {
			meetingList.add(new Meeting(i, meetings[i][0], meetings[i][1]));
		}
		
		Collections.sort(meetingList, new Comparator<Meeting>(){
			public int compare(Meeting m1, Meeting m2) {
				if (m1.st==m2.st) return Integer.compare(m1.ed, m2.ed);
				return Integer.compare(m1.st, m2.st);
			}
		});
		System.out.println("sorted meetings:");
		for (int i=0; i<meetingList.size(); i++) {
			Meeting m = meetingList.get(i);
			System.out.println("M" + m.id + ":" + m.st + "-" + m.ed);
		}
		
		PriorityQueue<Room> rooms = new PriorityQueue<Room>(10, new Comparator<Room>(){
			public int compare(Room r1, Room r2) {
				return Integer.compare(r1.avail, r2.avail);
			}
		});
		
		for (Meeting m : meetingList) {
			if (rooms.isEmpty() || rooms.peek().avail>m.st) {
				int id = res.size();
				res.add(new ArrayList<Integer>());
				res.get(id).add(m.id);
				rooms.offer(new Room(id, m.ed));
			} else {
				Room curR = rooms.poll();
				curR.avail = m.ed;
				rooms.offer(curR);
				res.get(curR.id).add(m.id);
			}
		}
		
		
		return res;
	}
	
	
	public static class Moment{
		int time;
		boolean isStart;
		public Moment(int time, boolean isStart) {
			this.time = time;
			this.isStart = isStart;
		}
	}
	
	
	
	public static int MostNumberOfMeetings(int[][] meetings) {
		int res = 0;
		
		List<Moment> moments = new ArrayList<Moment>();
		for (int i=0; i<meetings.length; i++) {
			moments.add(new Moment(meetings[i][0],true));
			moments.add(new Moment(meetings[i][1],false));
		}
		
		Collections.sort(moments, new Comparator<Moment>(){
			public int compare(Moment m1, Moment m2) {
				if (m1.time==m2.time) {
					if (m1.isStart) {
						return m2.isStart?0:1;
					} else {
						return m2.isStart?-1:0;
					}
				}
				return Integer.compare(m1.time, m2.time);
				
			}
		});
		
		int count = 0;
		for (int i=0; i<moments.size(); i++) {
			if (moments.get(i).isStart) {
				count ++;
				if (count>res) res = count;
			} else {
				count --;
			}
		}

		return res;
		
	}

}
