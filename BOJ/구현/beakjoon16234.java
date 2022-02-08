import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class beakjoon16234 {
	static int N, L, R, cnt, sum;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
	static int[][] map, visited;
	static HashMap<Integer, Integer> populPerUnion;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = -1;
		boolean isChange = false;
		populPerUnion = new HashMap<>();
		
		do {
			ans++;
			visited = new int[N][N];
			isChange = false;
			int th = 0;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(visited[i][j] == 0 && hasUnion(i, j)) {
						cnt = 0;
						sum = 0;
						isChange = true;
						
						checkCountry(i, j, ++th);
						
						populPerUnion.put(th, sum / cnt);
					}
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(visited[i][j] != 0) {
						map[i][j] = populPerUnion.get(visited[i][j]);
					}
				}
			}
		} while (isChange);
		
		System.out.println(ans);
	}

	private static boolean hasUnion(int r, int c) {
		for(int dir = 0; dir < 4; dir++) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			
			if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
			if(visited[nr][nc] != 0) continue;
			
			int diff = (int)Math.abs(map[nr][nc] - map[r][c]);
			if(diff < L || diff > R) continue;
			
			return true;
		}
		return false;
	}

	private static void checkCountry(int r, int c, int th) {
		cnt++;
		sum += map[r][c];
		visited[r][c] = th;
		
		for(int dir = 0; dir < 4; dir++) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			
			if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
			if(visited[nr][nc] != 0) continue;
			
			int diff = (int)Math.abs(map[nr][nc] - map[r][c]);
			if(diff < L || diff > R) continue;
			
			checkCountry(nr, nc, th);
		}
	}
}
