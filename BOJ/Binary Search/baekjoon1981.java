import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon1981 {
	static int N, ans;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
	static int[][] arr;
	static Queue<Pos> que;
	static boolean[][] visited;
	
	static class Pos{
		int r, c, max, min;

		public Pos(int r, int c, int max, int min) {
			super();
			this.r = r;
			this.c = c;
			this.max = max;
			this.min = min;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		
		int max = 0;
		int min = 200;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				
				arr[i][j] = tmp;
				if(tmp > max)  max = tmp;
				else if(tmp < min) min = tmp;
			}
		}
		
		bSearch(0, max - min);
		
		System.out.print(ans);
	}

	private static void bSearch(int s, int e) {
		if(s == e) {
			ans = s;
			return;
		}
		int mid = (s + e) / 2;
		
		if(movePossible(mid)) bSearch(s, mid);
		else bSearch(mid + 1, e);
	}

	private static boolean movePossible(int mid) {
		que = new LinkedList<Pos>();
		visited = new boolean[N][N];
		
		que.offer(new Pos(0, 0, arr[0][0], arr[0][0]));
		visited[0][0] = true;
		
		while(!que.isEmpty()) {
			Pos pos = que.poll();
			
			if(pos.r == N - 1 && pos.c == N - 1) return true;
			
			for (int i = 0; i < 4; i++) {
				int nr = pos.r + dr[i];
				int nc = pos.c + dc[i];
				
				if(isOutOfMap(nr, nc)) continue;
				if(visited[nr][nc]) continue;
				
				if(pos.max < arr[nr][nc]) {
					if(arr[nr][nc] - pos.min > mid) continue;
					
					que.offer(new Pos(nr, nc, arr[nr][nc], pos.min));
				}
				else if(pos.min > arr[nr][nc]) {
					if(pos.max - arr[nr][nc] > mid) continue;
					
					que.offer(new Pos(nr, nc, pos.max, arr[nr][nc]));
				}
				else {
					que.offer(new Pos(nr, nc, pos.max, pos.min));
				}
				
				visited[nr][nc] = true;
			}
		}
		
		return false;
	}

	private static boolean isOutOfMap(int r, int c) {
		if(r < 0 || r >= N || c < 0 || c >= N) return true;
		return false;
	}
}
