import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon16929 {
	static int N, M;
	static int[] dirR = {-1, 0, 1, 0}, dirC = {0, 1, 0, -1};
	static int[][] cycleDis;
	static char[][] map;
	static Queue<Pos> que;
	
	static class Pos{
		int r, c, val;

		public Pos(int r, int c, int val) {
			super();
			this.r = r;
			this.c = c;
			this.val = val;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		cycleDis = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			map[i] = str.toCharArray();
		}
		
		// 0,0 부터 탐색
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(cycleDis[i][j] == 0) {
					if(calcDis(i, j)) {
						System.out.print("Yes");
						return;
					}
				}
			}
		}
		
		System.out.print("No");
	}

	private static boolean calcDis(int i, int j) {
		que = new LinkedList<Pos>();
		que.offer(new Pos(i, j, 1));
		
		// bfs로 같은 문자끼리 탐색하면서 거리 표시
		while(!que.isEmpty()) {
			Pos pPos = que.poll();
			if(cycleDis[pPos.r][pPos.c] == pPos.val)
				return true;
			cycleDis[pPos.r][pPos.c] = pPos.val;
			
			for (int dir = 0; dir < 4; dir++) {
				int nr = pPos.r + dirR[dir];
				int nc = pPos.c + dirC[dir];
				
				// 범위 검사
				if(isRange(nr, nc)) {
					// 같은 문자
					if(map[pPos.r][pPos.c] == map[nr][nc]) {
						// 숫자가 0이라면
						if(cycleDis[nr][nc] == 0) {
							que.offer(new Pos(nr, nc, pPos.val + 1));
						}
					}
				}
			}
		}
		
		return false;
	}

	private static boolean isRange(int nr, int nc) {
		if(nr >= 0 && nr < N && nc >= 0 && nc < M) return true;
		return false;
	}
}
