import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon14503 {
	static int N, M, ans = 0;
	static int[] dirx = {0, 1, 0, -1}, diry = {-1, 0, 1, 0};
	static int[][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = 3 - Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		loop:
		while(true) {
			// 현재 위치 1로 변환
			if(isCleaning(r, c)) {
				visit(r,c);
				ans++;
			}
			// 0.
			for (int dir = 0; dir < 4; dir++) {
				// 1. 왼쪽으로 회전
				d = (d + 1) % 4;
				
				if(isNotWall(r + dirx[d], c + diry[d]) && isCleaning(r + dirx[d], c + diry[d])) {
					// 2. 해당 방향의 앞에 청소할수 있는 칸이 있으면 전진 -> 0.
					r += dirx[d];
					c += diry[d];
					continue loop;
				}
				// 3. 청소 할수 없으면 1번으로
			}
			// 4방향 모두 불가능하면 방향에서 후진하고 0.
			if(isNotWall(r + dirx[(d + 2) % 4], c + diry[(d + 2) % 4])) {
				r += dirx[(d + 2) % 4];
				c += diry[(d + 2) % 4];
			}
			// 후진도 불가능 하면 종료
			else {
				break;
			}
		}
		
		System.out.print(ans);
	}

	private static boolean isCleaning(int nr, int nc) {
		if(map[nr][nc] != -1) return true;
		return false;
	}

	private static boolean isNotWall(int nr, int nc) {
		if(nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] != 1) return true;
		return false;
	}

	private static void visit(int r, int c) {
		map[r][c] = -1;
	}
}
