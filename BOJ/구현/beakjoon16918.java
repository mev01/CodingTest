import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class beakjoon16918 {
	static int R, C, N;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		for(int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		int th = N % 4;
		if(th == 0 || th == 2){
			for(int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					map[i][j] = 'O';
				}
			}
		}
		else if(N != 1 && (th == 1 ||th == 3)) {
			Change();
			if(th == 1) Change();
		}
		
		for(int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sb.append(map[i][j]);
			}
			sb.append('\n');
		}
		System.out.println(sb.toString());
	}

	private static void Change() {
		for(int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j] == 'O') {
					Bomb(i, j);
				}
			}
		}
		for(int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j] == 'c') {
					map[i][j] = '.';
				}
				else if(map[i][j] == '.') {
					map[i][j] = 'O';
				}
			}
		}
	}

	private static void Bomb(int r, int c) {
		map[r][c] = 'c';
		for(int dir = 0; dir < 4; dir++) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			
			if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
			if(map[nr][nc] == 'O') continue;
			
			map[nr][nc] = 'c';
		}
	}

}
