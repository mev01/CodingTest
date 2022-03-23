import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class beakjoon2589 {
	static int N, M, ans = 0;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
	static char[][] map;
	static boolean[][] visited;
	
	static class Pos{
		int r, c, dis;
		
		public Pos(int r, int c, int dis) {
			this.r = r;
			this.c = c;
			this.dis = dis;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][];
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 'L') {
					visited = new boolean[N][M];
					
					visited[i][j] = true;
					BFS(i, j);
				}
			}
		}
		
		System.out.println(ans);
	}

	private static void BFS(int i, int j) {
		Queue<Pos> que = new LinkedList<Pos>();
		que.offer(new Pos(i, j, 0));
		
		while(!que.isEmpty()) {
			Pos pos = que.poll();
			
			if(ans < pos.dis) {
				ans = pos.dis;
			}
			
			for (int dir = 0; dir < 4; dir++) {
				int nr = pos.r + dr[dir];
				int nc = pos.c + dc[dir];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				if(visited[nr][nc]) continue;
				if(map[nr][nc] == 'W') continue;
				
				visited[nr][nc] = true;
				que.offer(new Pos(nr, nc, pos.dis + 1));
			}
		}
	}

}
