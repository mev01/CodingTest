import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, fuelSize, startR, startC, targetPeople, answer = -1;
	static int[] dr = {-1, 0, 0, 1}, dc = {0, -1, 1, 0};
	static int[][] map, peopleArr, peopleMap;
	static boolean[][] visited;
	static Queue<Pos> que;
	static PriorityQueue<Pos> pQue;
	
	static class Pos implements Comparable<Pos>{
		int r, c, size;

		public Pos(int r, int c, int size) {
			super();
			this.r = r;
			this.c = c;
			this.size = size;
		}

		@Override
		public int compareTo(Pos o) {
			if(this.r == o.r) return this.c - o.c;
			return this.r - o.r;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuelSize = Integer.parseInt(st.nextToken());
		
		map = new int[N + 1][N + 1];
		peopleMap =new int[N + 1][N + 1];
		peopleArr = new int[M][4];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		startR = Integer.parseInt(st.nextToken());
		startC = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			peopleArr[i][0] = Integer.parseInt(st.nextToken());
			peopleArr[i][1] = Integer.parseInt(st.nextToken());
			peopleArr[i][2] = Integer.parseInt(st.nextToken());
			peopleArr[i][3] = Integer.parseInt(st.nextToken());
			
			peopleMap[peopleArr[i][0]][peopleArr[i][1]] = i + 2;
		}
		
		int r = startR, c = startC;
		
		while(M-- > 0) {
			int cost;
			cost = getFuelIfDriveNearestPeople(r, c);
			if(cost == -1 || fuelSize - cost < 0) {
				System.out.println(-1);
				return;
			}
			targetPeople -= 2;
			fuelSize -= cost;
			
			peopleMap[peopleArr[targetPeople][0]][peopleArr[targetPeople][1]] = 0;
			cost = getFuelIfDrive(peopleArr[targetPeople][0], peopleArr[targetPeople][1], peopleArr[targetPeople][2], peopleArr[targetPeople][3]);
			if(cost == -1 || fuelSize - cost < 0) {
				System.out.println(-1);
				return;
			}
			fuelSize += cost;
			
			r = peopleArr[targetPeople][2];
			c = peopleArr[targetPeople][3];
		}
		
		System.out.println(fuelSize);
	}
	
	private static int getFuelIfDriveNearestPeople(int r, int c) {
		visited = new boolean[N + 1][N + 1];
		que = new LinkedList<Pos>();
		pQue = new PriorityQueue<>();
		
		visited[r][c] = true;
		que.offer(new Pos(r, c, 0));
		while(!que.isEmpty()) {
			int size = que.size();
			if(pQue.size() != 0) {
				targetPeople = peopleMap[pQue.peek().r][pQue.peek().c];
				return pQue.peek().size;
			}
			while(size-- > 0) {
				Pos pos = que.poll();
				
				if(peopleMap[pos.r][pos.c] >= 2) {
					pQue.offer(new Pos(pos.r, pos.c, pos.size));
				}
				
				for (int dir = 0; dir < 4; dir++) {
					int nr = pos.r + dr[dir];
					int nc = pos.c + dc[dir];
					
					if(isOutOfMap(nr, nc)) continue;
					if(visited[nr][nc]) continue;
					if(map[nr][nc] == 1) continue;
					
					visited[nr][nc] = true;
					que.offer(new Pos(nr, nc, pos.size + 1));
				}
			}
		}
		
		if(pQue.size() != 0) {
			targetPeople = peopleMap[pQue.peek().r][pQue.peek().c];
			return pQue.peek().size;
		}
		return -1;
	}

	private static int getFuelIfDrive(int r1, int c1, int r2, int c2) {
		visited = new boolean[N + 1][N + 1];
		que = new LinkedList<Pos>();
		
		visited[r1][c1] = true;
		que.offer(new Pos(r1, c1, 0));
		while(!que.isEmpty()) {
			Pos pos = que.poll();
			
			if(pos.r == r2 && pos.c == c2) return pos.size;
			
			for (int dir = 0; dir < 4; dir++) {
				int nr = pos.r + dr[dir];
				int nc = pos.c + dc[dir];
				
				if(isOutOfMap(nr, nc)) continue;
				if(visited[nr][nc]) continue;
				if(map[nr][nc] != 0) continue;
				
				visited[nr][nc] = true;
				que.offer(new Pos(nr, nc, pos.size + 1));
			}
		}
		
		return -1;
	}

	private static boolean isOutOfMap(int nr, int nc) {
		if(nr <= 0 || nr > N || nc <= 0 || nc > N) return true;
		return false;
	}
}
