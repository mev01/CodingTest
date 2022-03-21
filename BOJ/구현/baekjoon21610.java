import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon21610 {
	static int N, M;
	static int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1}, dc = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	static int[] ar = {-1, -1, 1, 1}, ac = {-1, 1, 1, -1};
	static int[][] map;
	static boolean[][] visited;
	static Queue<Pos> que;
	
	static class Pos{
		int r, c;
		
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		que = new LinkedList<Pos>();
		que.offer(new Pos(N - 1, 0));
		que.offer(new Pos(N - 1, 1));
		que.offer(new Pos(N - 2, 0));
		que.offer(new Pos(N - 2, 1));
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			int num = que.size();
			while(num-- > 0) {
				Pos pos = que.poll();
				int nr = (pos.r + s * dr[d]) % N;
				int nc = (pos.c + s * dc[d]) % N;
				
				if(nr < 0) nr += N;
				if(nc < 0) nc += N;
				
				que.offer(new Pos(nr, nc));
			}
			
			visited = new boolean[N][N];
			for(Pos pos : que) {
				map[pos.r][pos.c] += 1;
				visited[pos.r][pos.c] = true;
			}
			for(Pos pos : que) {
				for(int dir = 0; dir < 4; dir++) {
					int nr = pos.r + ar[dir];
					int nc = pos.c + ac[dir];
					
					if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					if(map[nr][nc] >= 1) map[pos.r][pos.c] += 1; 
				}
			}
			
			que.clear();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] >= 2 && !visited[i][j]) {
						map[i][j] -= 2;
						que.offer(new Pos(i, j));
					}
				}
			}
		}
		
		int ans = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				ans += map[i][j];
			}
		}
		
		System.out.println(ans);
	}

}
