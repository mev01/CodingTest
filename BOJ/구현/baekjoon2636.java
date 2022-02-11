import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon2636 {
	static int N, M;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
	static int[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		int remainingCheese = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int cheese = Integer.parseInt(st.nextToken());
				map[i][j] = cheese;
				if(cheese == 1) remainingCheese++;
			}
		}
		
		map[0][0] = 2;
		int time = 0;
		while(++time > 0) {
			visited = new boolean[N][M];
			fill(0, 0);
			int meltCheese = getCheese();
			if(remainingCheese - meltCheese > 0) remainingCheese -= meltCheese;
			else break;
		}
		
		System.out.println(time);
		System.out.println(remainingCheese);
	}

	private static void fill(int r, int c) {
		visited[r][c] = true;
		map[r][c] = 2;
		
		for (int dir = 0; dir < 4; dir++) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			
			if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
			if(visited[nr][nc]) continue;
			
			if(map[nr][nc] == 0 || map[nr][nc] == 2) fill(nr, nc);
			else if(map[nr][nc] == 1) map[nr][nc] = 3;
		}
	}

	private static int getCheese() {
		int cnt = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 3) {
					map[i][j] = 2;
					cnt++;
				}
			}
		}
		
		return cnt;
	}

}
