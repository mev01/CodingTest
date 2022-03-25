import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon17836 {
	
	static class Pos{
		int r, c, t;
		boolean hasSword;
		
		public Pos(int r, int c, int t, boolean hasSword) {
			this.r = r;
			this.c = c;
			this.t = t;
			this.hasSword = hasSword;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = -1;
		int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
		boolean[][][] visited = new boolean[2][N][M];
		Queue<Pos> que = new LinkedList<Pos>();
		
		que.offer(new Pos(0, 0, 0, false));
		visited[0][0][0] = true;
		
		while(!que.isEmpty()) {
			Pos pos = que.poll();
			
			if(pos.r == N - 1 && pos.c == M - 1) {
				ans = pos.t;
				break;
			}
			if(pos.t > T) {
				break;
			}
			
			for (int dir = 0; dir < 4; dir++) {
				int nr = pos.r + dr[dir];
				int nc = pos.c + dc[dir];
				
				if(nr < 0 || nr >= N || nc < 0 ||  nc >= M) continue;
				if(pos.hasSword && visited[1][nr][nc]) continue;
				else if(!pos.hasSword && visited[0][nr][nc]) continue;
				
				if(!pos.hasSword && map[nr][nc] == 1) continue;
				
				if(pos.hasSword || map[nr][nc] == 2) {
					visited[1][nr][nc] = true;
					que.offer(new Pos(nr, nc, pos.t + 1, true));
				}
				else {
					visited[0][nr][nc] = true;
					que.offer(new Pos(nr, nc, pos.t + 1, false));
				}
			}
		}
		
		System.out.println(ans == -1 ? "Fail" : ans);
	}

}
