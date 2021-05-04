import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class EA2383 {
	static int N, arrived, ans;
	static int[][] map;
	static Stair[] stair = new Stair[3];
	static ArrayList<Person> personList;
	
	static class Pos{
		int x, y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static class Person{
		int status, numStair, timeToStair;
		Pos position;
		
		public Person(Pos pos) {
			this.position = pos;
		}
	}
	
	static class Stair{
		int len, cnt;
		Queue<Integer> waiting;
		Pos position;
		
		public Stair(int len, Pos pos) {
			waiting = new LinkedList<Integer>();
			this.len = len;
			this.position = pos;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			int cnt = 1;
			arrived = 0;
			ans = Integer.MAX_VALUE;
			personList = new ArrayList<>();
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					
					if(map[i][j] == 1) {
						personList.add(new Person(new Pos(i, j)));
					}
					else if(map[i][j] >= 2) {
						stair[cnt++] = new Stair(map[i][j], new Pos(i, j));
					}
				}
			}
			
			setStair(personList.size(), 0);
			
			
			sb.append("#"+tc+" "+ans+'\n');
		}
		System.out.print(sb.toString());
	}

	private static void setStair(int size, int idx) {
		if(size == idx) {
			calcDistance();
			TimeStart(1);
			init();
			
			return;
		}
		
		personList.get(idx).numStair = 1;
		setStair(size, idx + 1);
		
		personList.get(idx).numStair = 2;
		setStair(size, idx + 1);
	}

	private static void TimeStart(int time) {
		if(time >= ans) return;
		
		for (int i = 0; i < personList.size(); i++) {
			Person person = personList.get(i);
			
			if(person.status == 0) {
				if(time == person.timeToStair) person.status++;
			}
			else if(person.status == 1) {
				stair[person.numStair].waiting.offer(i);
				person.status++;
			}
			else if(person.status >= 3) {
				person.status++;
				if(person.status == 3 + stair[person.numStair].len) {
					arrived++;
					stair[person.numStair].cnt--;
				}
			}
		}
		
		goStair();
		
		if(arrived == personList.size()) {
			ans = time;
			return;
		}
		else TimeStart(time + 1);
	}

	private static void init() {
		arrived = 0;
		for (int i = 1; i <= 2; i++) {
			stair[i].cnt = 0;
			stair[i].waiting.clear();
		}
		for (int i = 0; i < personList.size(); i++) {
			Person person = personList.get(i);
			
			person.status = 0;
		}
	}

	private static void goStair() {
		for (int i = 1; i <= 2; i++) {
			while(stair[i].cnt < 3 && !stair[i].waiting.isEmpty()) {
				int idx = stair[i].waiting.poll();
				
				personList.get(idx).status = 3;
				stair[i].cnt++;
			}
		}
	}

	private static void calcDistance() {
		for (int i = 0; i < personList.size(); i++) {
			Person person = personList.get(i);
			person.timeToStair = Math.abs(person.position.x - stair[person.numStair].position.x) + Math.abs(person.position.y - stair[person.numStair].position.y);
		}
	}
}
