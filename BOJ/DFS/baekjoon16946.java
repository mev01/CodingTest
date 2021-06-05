import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon16946 {
	static int N, M, num = 1;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
	static int[] cntRoom;
	static int[][] map, movableMap;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		movableMap = new int[N][M];
		cntRoom = new int[N * M + 1];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		// map을 돌면서  1인 칸이 이동할 수 있는 칸에 0이 있다면
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 0) {
					sb.append(0);
				}
				else {
					sb.append((checkFourDirections(i, j) + 1) % 10);
				}
			}
			sb.append('\n');
		}
		
		System.out.print(sb.toString());
	}

	private static int checkFourDirections(int r, int c) {
		int total = 0;
		int[] checkNum = new int[4];
		
		// 이동할 수 있는 모든 0을 검사하면서 표시
		loop:
		for (int dir = 0; dir < 4; dir++) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			
			if(isOutOfMap(nr, nc)) continue;
			if(map[nr][nc] == 1) continue;
			
			if(movableMap[nr][nc] == 0) {
				searchMap(nr, nc);
				++num;
			}
			// 이미 표시된 0이라면 더하고 끝
			else {
				for (int i = 0; i < dir; i++) {
					if(checkNum[i] == movableMap[nr][nc]) continue loop;
				}
			}
			
			checkNum[dir] = movableMap[nr][nc];
			total += cntRoom[movableMap[nr][nc]];
		}
		
		return total;
	}

	private static boolean isOutOfMap(int r, int c) {
		if(r < 0 || r >= N || c < 0 || c >= M) return true;
		return false;
	}
	
	private static void searchMap(int r, int c) {
		movableMap[r][c] = num;
		cntRoom[num]++;
		
		for (int dir = 0; dir < 4; dir++) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			
			if(isOutOfMap(nr, nc)) continue;
			if(map[nr][nc] == 0 && movableMap[nr][nc] == 0) {
				searchMap(nr, nc);
			}
		}
	}
}
