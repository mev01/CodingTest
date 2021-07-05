import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon9944 {
	static int N, M, ans;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
	static char[][] map;
	
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
		
		int testcase = 1;
		String input = null;
		while((input = br.readLine()) != null) {
			st = new StringTokenizer(input);
			if(!st.hasMoreTokens()) break;
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new char[N][M];
			ans = Integer.MAX_VALUE;
			
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j] == '.') {
						map[i][j] = '*';
						Turn(new Pos(i, j), 0);
						map[i][j] = '.';
					}
				}
			}
			
			System.out.println("Case " + testcase++ + ": " + (ans == Integer.MAX_VALUE ? -1 : ans));
		}
	}

	private static void Turn(Pos pos, int cnt) {
		boolean end = true;
		
		for (int dir = 0; dir < 4; dir++) {
			Pos npos = Straight(pos, dir);
			
			if(npos.r != pos.r || npos.c != pos.c) {
				end = false;
				Turn(npos, cnt + 1);
				
				Back(pos, npos, dir);
			}
		}
		
		if(end) {
			if(checkSuccess()) {
				ans = Math.min(ans, cnt);
			}
		}
	}

	private static void Back(Pos pos, Pos npos, int dir) {
		int nr = pos.r + dr[dir];
		int nc = pos.c + dc[dir];
		
		while(true) {
			map[nr][nc] = '.';
			
			if(nr == npos.r && nc == npos.c) break;
			
			nr += dr[dir];
			nc += dc[dir];
		}
	}

	private static boolean checkSuccess() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == '.') return false;
			}
		}
		return true;
	}

	private static Pos Straight(Pos pos, int dir) {
		int nr = pos.r, nc = pos.c;
		
		while(true) {
			if(isOutOfMap(nr + dr[dir], nc + dc[dir])) break;
			if(map[nr + dr[dir]][nc + dc[dir]] == '*') break;
			
			nr += dr[dir];
			nc += dc[dir];
			
			map[nr][nc] = '*';
		}
		
		return new Pos(nr, nc);
	}

	private static boolean isOutOfMap(int r, int c) {
		if(r < 0 || r >= N || c < 0 || c >= M) return true;
		return false;
	}
}
