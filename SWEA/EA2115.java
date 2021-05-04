import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class EA2115 {
	static int N, M, C, max;
	static int[] ans = new int[2];
	static Pos[] person = new Pos[3];
	static int[][] map;
	static boolean[] visited;
	
	static class Pos{
		int x, y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			max = 0;
			person[0] = new Pos(0, 0);
			// 어떤 벌통부터 채취할지 정하기
			chooseHive();
			
			
			sb.append("#"+tc+" "+'\n');
		}
		System.out.print(sb.toString());
	}

	private static void chooseHive() {
		for (int x1 = 0; x1 < N; x1++) {
			for (int y1 = 0; y1 < N - M + 1; y1++) {
				for (int x2 = x1; x2 < N; x2++) {
					for (int y2 = 0; y2 < N - M + 1; y2++) {
						if((x2 == x1 && y2 >= y1 + M) || x2 != x1) {
							visited = new boolean[M];
							extractHoney(x1, y1, 0, 0);
							visited = new boolean[M];
							extractHoney(x2, y2, 0, 1);
						}
					}
				}
			}
		}
	}

	private static void extractHoney(int x, int y, int cnt, int idx) {
		if(cnt == M) {
			int sum = 0;
			for (int i = 0; i < M; i++) {
				if(visited[i])
					sum += map[x][y + i];
			}
			
			if(sum > C) return;
			ans[idx] = Math.max(ans[idx], getCost(x , y));
			
			return;
		}
		extractHoney(x, y, cnt + 1, idx);
		visited[cnt] = true;
		extractHoney(x, y, cnt + 1, idx);
	}

	private static int getCost(int x, int y) {
		int cost = 0;
		for (int i = 0; i < M; i++) {
			if(visited[i])
				cost += map[x][y + i] * map[x][y + i];
		}
		
		return cost;
	}
}
