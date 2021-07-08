import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon1981 {
	static int N, ans, MAX, MIN;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
	static int[][] arr;
	static Queue<Pos> que;
	static boolean[][] visited;
	
	static class Pos{
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		
		MAX = 0;
		MIN = 200;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				
				arr[i][j] = tmp;
				if(tmp > MAX)  MAX = tmp;
				if(tmp < MIN) MIN = tmp;
			}
		}
		
		binarySearch(0, MAX - MIN);
		
		System.out.print(ans);
	}

	private static void binarySearch(int s, int e) {
		if(s >= e) {
			ans = s;
			return;
		}
		int mid = (s + e) / 2;
		
		if(findMinMax(mid)) binarySearch(s, mid);
		else binarySearch(mid + 1, e);
	}
	
	private static boolean findMinMax(int mid) {
		for (int i = MIN; i + mid <= MAX; i++) {
			if(movePossible(i, i + mid)) return true;
		}
		
		return false;
	}

	private static boolean movePossible(int min, int max) {
		que = new LinkedList<Pos>();
		visited = new boolean[N][N];
		
		if(min > arr[0][0] || max < arr[0][0]) return false;
		que.offer(new Pos(0, 0));
		visited[0][0] = true;
		
		while(!que.isEmpty()) {
			Pos pos = que.poll();
			
			if(pos.r == N - 1 && pos.c == N - 1) {
				return true;
			}
			
			for (int i = 0; i < 4; i++) {
				int nr = pos.r + dr[i];
				int nc = pos.c + dc[i];
				
				if(isOutOfMap(nr, nc)) continue;
				if(visited[nr][nc]) continue;
				if(min > arr[nr][nc] || max < arr[nr][nc]) continue;
				
				visited[nr][nc] = true;
				que.offer(new Pos(nr, nc));
			}
		}
		
		return false;
	}

	private static boolean isOutOfMap(int r, int c) {
		if(r < 0 || r >= N || c < 0 || c >= N) return true;
		return false;
	}
}
