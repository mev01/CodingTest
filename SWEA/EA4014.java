import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class EA4014 {
	static int N, L, ans = 0;
	static int[] dirx = { -1, 0, 1, 0 }, diry = { 0, 1, 0, -1 };
	static int[][] map;
	static boolean[] check;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			ans = 0;

			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			boolean isPoss = true;
			// 각 행을 돌면서 다른 높이가 있는지 확인
			for (int i = 0; i < N; i++) {
				isPoss = true;
				check = new boolean[N];

				for (int j = 1; j < N; j++) {
					if (map[i][j] != map[i][j - 1]) {
						if(!isSetRamp(i, j - 1, i, j)){
							isPoss = false;
							break;
						}
					}
				}

				if (isPoss)
					ans++;
			}

			// 각 열을 돌면서 다른 높이가 있는지 확인
			for (int j = 0; j < N; j++) {
				isPoss = true;
				check = new boolean[N];
				
				for (int i = 1; i < N; i++) {
					if (map[i][j] != map[i - 1][j]) {
						if(!isSetRamp(i - 1, j, i, j)){
							isPoss = false;
							break;
						}
					}
				}
				
				if (isPoss)
					ans++;
			}
			
			sb.append("#"+tc+" "+ans+'\n');
		}
		System.out.print(sb.toString());
	}

	private static boolean isSetRamp(int pr, int pc, int r, int c) {
		// 높이가 1이 달라졌을 경우만
		if (Math.abs(map[pr][pc] - map[r][c]) == 1) {
			// 높이가 높아졌으면 이전꺼 확인해서
			if (map[pr][pc] < map[r][c]) {
				if (pr != r) {
					return isSameHeight(pr, pc, 0);
				}
				else{
					return isSameHeight(pr, pc, 3);
				}
			}
			// 낮아지면 다음 꺼 확인
			else {
				if (pc != c) {
					return isSameHeight(r, c, 1);
				}
				else{
					return isSameHeight(r, c, 2);
				}
			}
		}

		return false;
	}

	/**
	 * @param x 시작하는 행
	 * @param y 시작하는 열
	 * @param dir 진행하는 방항
	 * @return dir 방향으로 진행하면서 모든 칸의 높이가 같은지
	 */
	private static boolean isSameHeight(int x, int y, int dir) {
		// L의 길이가 높이가 같은지 확인
		int r = x;
		int c = y;
		int height = map[r][c];
		
		for (int i = 0; i < L; i++, r += dirx[dir], c += diry[dir]) {
			// 범위확인
			if(r >= 0 && r < N && c >= 0 && c < N){
				if(dir % 2 == 0){
					if(map[r][c] != height || check[r]) return false;
				}
				else{
					if(map[r][c] != height || check[c]) return false;
				}
			}
			else{
				return false;
			}
		}
		
		// 경사로 세우면 체크
		setRamp(x, y, dir);
		
		return true;
	}

	private static void setRamp(int x, int y, int dir) {
		if(dir % 2 == 0){
			for (int i = 0; i < L; i++, x += dirx[dir]) {
				check[x] = true;
			}
		}
		else{
			for (int i = 0; i < L; i++, y += diry[dir]) {
				check[y] = true;
			}
		}
		
	}
}
