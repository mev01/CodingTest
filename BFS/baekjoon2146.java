import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon2146 {
	static int N, ans = Integer.MAX_VALUE;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
	static int[][] map;
	static ArrayList<Island> IslandList;
	static Queue<Land> que;
	
	static class Island{
		int num;
		ArrayList<Land> landList;
		
		public Island(int num) {
			this.num = num;
			landList = new ArrayList<>();
		}
	}
	
	static class Land{
		int r, c, num, cnt;

		public Land(int r, int c, int num, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.num = num;
			this.cnt = cnt;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		IslandList = new ArrayList<>();
		
		findLand();
		
		// °¢ ¼¶À» BFS·Î Å½»ö
		for (int i = 0; i < IslandList.size(); i++) {
			findNearestIsland(i);
		}
		
		System.out.print(ans);
	}

	private static void findNearestIsland(int idx) {
		que = new LinkedList<Land>();
		for (int i = 0; i < IslandList.get(idx).landList.size(); i++) {
			que.offer(IslandList.get(idx).landList.get(i));
		}
		
		while(!que.isEmpty()){
			Land land = que.poll();
			
			for (int dir = 0; dir < dc.length; dir++) {
				Land nLand = new Land(land.r + dr[dir], land.c + dc[dir], land.num, land.cnt + 1);
				
				if(isOutOfMap(nLand.r, nLand.c)) continue;
				if(map[nLand.r][nLand.c] == nLand.num || map[nLand.r][nLand.c] == nLand.cnt) continue;
				if(map[nLand.r][nLand.c] != nLand.num && map[nLand.r][nLand.c] < 0){
					ans = Math.min(ans, land.cnt);
					return;
				}
				if(map[nLand.r][nLand.c] < nLand.cnt && map[nLand.r][nLand.c] != 0) continue;
				
				map[nLand.r][nLand.c] = nLand.cnt;
				que.offer(nLand);
			}
		}
	}

	private static void findLand() {
		int cnt = 1;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] > 0){
					IslandList.add(new Island(~cnt));
					numberingLand(i, j, ~cnt++);
				}
			}
		}
	}

	private static void numberingLand(int r, int c, int num) {
		map[r][c] = num;
		IslandList.get(IslandList.size() - 1).landList.add(new Land(r, c, num, 0));
		
		for (int dir = 0; dir < 4; dir++) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			
			if(!isOutOfMap(nr, nc)){
				if(map[nr][nc] > 0){
					numberingLand(nr, nc, num);
				}
			}
		}
	}

	private static boolean isOutOfMap(int r, int c) {
		if(r < 0 || r >= N || c < 0 || c >= N) return true;
		return false;
	}
}
